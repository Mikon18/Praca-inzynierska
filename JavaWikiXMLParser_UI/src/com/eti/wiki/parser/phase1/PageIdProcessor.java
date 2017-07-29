package com.eti.wiki.parser.phase1;

import com.eti.wiki.Configuration;
import java.util.Observable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.model.Page;
import com.eti.wiki.model.WikiPage;
import com.eti.wiki.parser.AbstractProcessor;
import com.eti.wiki.ui.IParsingProgressListener;

/**
 * Faza pierwsza, wyci¹ganie z rekordów id i tytu³ów i umieszczanie ich w bazie
 * danych.
 *
 */
public class PageIdProcessor extends AbstractProcessor {

    private IParsingProgressListener listener;
    private int counter;

    public PageIdProcessor(IParsingProgressListener listener) {
        this.listener = listener;
        this.counter = 0;
    }

    @Override
    public void process(WikiPage page) {
        listener.progressChanged(counter++, Configuration.PAGES_ALL);
        if (counter % 1000 == 0) {
            listener.currentlyProcessedPageChanged(page.getTitle());
        }
        savePageIntoDatabase(convertToSimpleHibernatePage(page));
    }

    private void savePageIntoDatabase(Page pageToSave) {
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

    private Page convertToSimpleHibernatePage(WikiPage page) {
        Page p = new Page();
        p.setId(page.getId());
        p.setTitle(page.getTitle());
        return p;
    }
}
