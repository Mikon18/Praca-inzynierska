package com.eti.wiki.parser.phase3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

import com.eti.wiki.Configuration;
import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.model.Page;
import com.eti.wiki.model.Reference;
import com.eti.wiki.model.ReferenceKeyword;
import com.eti.wiki.model.WikiOccurences;
import com.eti.wiki.model.collection.ReferenceConnection;
import com.eti.wiki.ui.IParsingProgressListener;

public class OccurencesFinder {
    // map title to occurence

    private Map<String, WikiOccurences> occurences;
    private Map<String, Page> pages;
    private LinkedList<ReferenceConnection> connections;
    private IParsingProgressListener listener;
    private int found_occurences;

    public OccurencesFinder(IParsingProgressListener listener) {
        this.listener = listener;
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

        listener.progressChanged(0, occurences.size());
        int counter = 0;

        for (Entry<String, WikiOccurences> occurence : occurences.entrySet()) {
            Set<String> references = parseReferences(occurence.getValue().getContent());

            if (counter % 1000 == 0) {
                listener.currentlyProcessedPageChanged(occurence.getValue().getTitle());
            }

            for (String reference : references) {
                if (occurences.containsKey(reference)) {
                    Page parent = pages.get(occurence.getValue().getTitle());
                    Page referencePage = pages.get(occurences.get(reference).getTitle());
                    if (parent != null && referencePage != null) {
                        long parentId = parent.getId();
                        long referenceId = referencePage.getId();
                        putIntoDatabase(parentId, referenceId, occurence.getValue().getKeywords());
                    } else {
                        LoggerFactory.getLogger(getClass()).error("Page not found: " + reference + " " + occurence.getKey());
                    }
                }
            }
            listener.progressChanged(counter++, occurences.size());
        }
        listener.progressChanged(occurences.size(), occurences.size());
    }

    private void putIntoDatabase(long parentId, long referenceId, String keyword) {
        // LoggerFactory.getLogger(getClass()).info("Wstawiam rekord");
        Session session = DatabaseSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (parentId != -1 && referenceId != -1) {
                session.save(new ReferenceKeyword(parentId, referenceId, keyword));
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

    private Set<String> parseReferences(String pageContent) {
        Set<String> referenceSet = new HashSet<String>();
        String substring = pageContent;
        String referenceSubstring = null, referenceTitle = null;

        int referenceStart = substring.indexOf("[["), referenceEnd = -1;
        while (referenceStart > 0) {
            referenceEnd = substring.indexOf("]]");
            if (referenceEnd > 0) {
                try {
                    while (referenceStart > referenceEnd) {
                        substring = substring.substring(referenceEnd + 2);

                        referenceEnd = substring.indexOf("]]");
                        if (substring.isEmpty()) {
                            break;
                        }
                    }
                    if (!substring.isEmpty()) {
                        referenceSubstring = substring.substring(referenceStart, referenceEnd);
                    }
                } catch (StringIndexOutOfBoundsException aioobe) {
                    LoggerFactory.getLogger(getClass()).info("aioobe");
                }
                if (referenceSubstring != null) {
                    int pipeIndex = referenceSubstring.indexOf("|");
                    if (pipeIndex > 0) {
                        try {
                            referenceTitle = referenceSubstring.substring(2, pipeIndex);
                        } catch (StringIndexOutOfBoundsException aioobe) {

                        }
                    } else {
                        if (!referenceSubstring.isEmpty()) {
                            try {
                                referenceTitle = referenceSubstring.substring(2);
                            } catch (StringIndexOutOfBoundsException aioobe) {
//								LoggerFactory.getLogger(getClass()).info("aioobe");
                            }
                        }
                    }

                    referenceSet.add(referenceTitle);
                }

            } else {
                break;
            }
            try {
                substring = substring.substring(referenceEnd + 2);
            } catch (Exception e) {
                break;
            }
            referenceStart = substring.indexOf("[[");
        }
        return referenceSet;
    }

    private void loadOccurences(String keywords) {
        Session session = DatabaseSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            LoggerFactory.getLogger(getClass()).info("Fetching");
            Criteria c = session.createCriteria(WikiOccurences.class);
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
            LoggerFactory.getLogger(getClass()).info("Fetching page");
            Criteria c = session.createCriteria(Page.class);
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
}
