package com.eti.wiki.parser.phase4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.eti.wiki.Configuration;
import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.model.Page;
import com.eti.wiki.model.Reference;
import com.eti.wiki.model.ReferenceKeyword;
import com.eti.wiki.model.ReferenceKeywordAll;
import com.eti.wiki.model.WikiOccurences;
import com.eti.wiki.model.WikiPage;
import com.eti.wiki.model.collection.ReferenceConnection;
import com.eti.wiki.parser.AbstractProcessor;
import com.eti.wiki.parser.ContentReferenceParser;
import com.eti.wiki.ui.IParsingProgressListener;

public class OccurencesFinderAll extends AbstractProcessor {

    private Map<String, WikiOccurences> occurences;
    private Map<String, Page> pages;
    private LinkedList<ReferenceConnection> connections;

    private BufferedReader fileReader;
    private String fileName;
    private Set<String> references;
    private String keyword;
    private IParsingProgressListener listener;
    private int counter;

    public OccurencesFinderAll(String name, IParsingProgressListener listener) {
        this.listener = listener;
        this.counter = 0;
        fileName = name;

        occurences = new HashMap<String, WikiOccurences>();
        connections = new LinkedList<>();
        pages = new HashMap<>();
    }

    public void findReferences() {
        StringBuilder keywordsCombined = new StringBuilder();
        for (String keyword : Configuration.KEYWORDS) {
            keywordsCombined.append(keyword).append(";");
        }

        loadOccurences(keywordsCombined.toString());
        selectPagesDatabase();

        references = occurences.keySet();
        keyword = keywordsCombined.toString();
    }

    private void putIntoDatabase(long parentId, long referenceId, String keyword) {

        Session session = DatabaseSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (parentId != -1 && referenceId != -1) {
                session.save(new ReferenceKeywordAll(parentId, referenceId, keyword));
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("Error saving reference into database.");
            e.printStackTrace();
        } finally {
            transaction.commit();
            if (session != null) {
                session.close();
            }
        }
    }

    private void loadOccurences(String keywords) {
        Session session = DatabaseSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(WikiOccurences.class);
            LoggerFactory.getLogger(getClass()).info("Fetching");
            List<WikiOccurences> resultPages = (List<WikiOccurences>) c.add(Restrictions.eq("keywords", keywords))
                    .list();
            LoggerFactory.getLogger(getClass()).info("Done");

            for (WikiOccurences occurence : resultPages) {
                occurences.put(occurence.getTitle(), occurence);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            transaction.commit();
            if (session != null) {
                session.close();
            }
        }
    }

    private void selectPagesDatabase() {
        List<String> titles = new LinkedList<String>();
        for (WikiOccurences occurence : occurences.values()) {
            titles.add(occurence.getTitle());
        }

        Session session = DatabaseSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(Page.class);
            LoggerFactory.getLogger(getClass()).info("Fetching");
            List<Page> resultPages = (List<Page>) c.add(Restrictions.in("title", titles)).list();
            LoggerFactory.getLogger(getClass()).info("Done");

            for (Page page : resultPages) {
                pages.put(page.getTitle(), page);
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            transaction.commit();
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void process(WikiPage page) {
        listener.currentlyProcessedPageChanged(page.getTitle());
        if (page != null) {
            listener.progressChanged(counter++, Configuration.PAGES_ALL);
            Set<String> subReferences = ContentReferenceParser.parseReferences(page.getContent());
            for (String subreference : subReferences) {
                if (references.contains(subreference)) {
                    if (pages.get(subreference) != null) {
                        putIntoDatabase(page.getId(), pages.get(subreference).getId(), keyword);
                    } else {
                        LoggerFactory.getLogger(getClass()).error("Couldn't find: " + subreference);
                    }
                }
            }
        }
    }
}
