package com.eti.wiki.parser.phase2;

import java.util.Observable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import com.eti.wiki.Configuration;
import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.model.WikiOccurences;
import com.eti.wiki.model.WikiPage;
import com.eti.wiki.model.collection.PageContent;
import com.eti.wiki.model.collection.PagesCollection;
import com.eti.wiki.parser.AbstractProcessor;

public class OccurencesCounterProcessor extends AbstractProcessor {

	private PagesCollection collection;
	
	public OccurencesCounterProcessor() {
		collection = new PagesCollection();
	}
	
	public PagesCollection getQueue() {
		return collection;
	}
	
	@Override
	public void process(WikiPage page) {
		PageContent pageContent = new PageContent(page);
		collection.add(pageContent);
	}
	
	// wstawia do bazy tylko te rekordy które by³y powy¿ej threshholda i s¹ w kolekcji
	public void finishJob(){
		insertIntoDatabase();
	}
	
	private void insertIntoDatabase() {
		StringBuilder keywordsCombined = new StringBuilder();
		for (String keyword : Configuration.KEYWORDS) {
			keywordsCombined.append(keyword).append(";");
		}

		for (PageContent contentPage : getQueue().elements()) {
			WikiOccurences occurences = new WikiOccurences(contentPage.getPage().getId(), contentPage.getPage().getContent(),
					contentPage.getPage().getTitle(), contentPage.getKeywordCount(), keywordsCombined.toString());
			saveOccurencesIntoDatabase(occurences);
		}
	}
	
	private void saveOccurencesIntoDatabase(WikiOccurences pageToSave) {
		Session session = DatabaseSession.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(pageToSave);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error("Error saving page into database.");
			e.printStackTrace();
		} finally {
			transaction.commit();
			if (session != null) {
				session.close();
			}
		}
	}
}
