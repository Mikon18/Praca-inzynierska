//package com.eti.wiki;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//
//import org.slf4j.LoggerFactory;
//
//import com.eti.wiki.io.WikiXMLReader;
//import com.eti.wiki.parser.WikiXMLPagesParser;
//import com.eti.wiki.parser.phase1.PageIdProcessor;
//import com.eti.wiki.parser.phase2.OccurencesCounterProcessor;
//import com.eti.wiki.parser.phase3.OccurencesFinder;
//import com.eti.wiki.parser.phase4.OccurencesFinderAll;
//import com.eti.wiki.parser.phase5.GraphFileReader;
//
//public class Main {
//
//	public static void main(String[] args) {
//		doWord("Game");
//		doWord("Sport");
//		doWord("Informatics");
//		doWord("Animal");
//		doWord("Business");
//	}
//
//	private static void doWord(String word) {
//		try {
//			Files.write(Paths.get("myfile.txt"), "start".getBytes(), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Long t1 = System.currentTimeMillis();
//		// phase1();
//		Long t2 = System.currentTimeMillis();
//		 phase2(new String[] { word }); // zliczanie wyst¹pieñ
//		Long t3 = System.currentTimeMillis();
////		 phase3(new String[] { word }); // szukanie wzajemnych referencji
//		Long t4 = System.currentTimeMillis();
//		// phase4(new String[] { word }); // szukanie referencji (miêdzy
//		// wszystkimi a occurences)
//		Long t5 = System.currentTimeMillis();
////		phase5(word);
//		Long t6 = System.currentTimeMillis();
//
//		String line = word + " Times t1:" + t1 + " t2:" + t2 + " t3:" + t3 + " t4:" + t4 + " t5:" + t5 + " t6:" + t6;
//		LoggerFactory.getLogger(Main.class).info(line);
//		try {
//			Files.write(Paths.get("myfile.txt"), line.getBytes(), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void phase1() {
//		// czyta plik
//		WikiXMLReader reader = new WikiXMLReader("E:/enwiki-latest-pages-meta-current.xml");
//
//		// parsuje go do obiektu WikiPage
//		WikiXMLPagesParser parser = new WikiXMLPagesParser();
//
//		// czyta WikiPage, zamienia go na Page i wstawia do bazy
//		PageIdProcessor processor = new PageIdProcessor();
//
//		// sprawia, ¿e po przeczytaniu page'a jest on wysy³any do parsera
//		reader.addObserver(parser);
//
//		// sprawia ¿e po przeczytaniu i sparsowaniu obiekt wysy³any jest do
//		// processora
//		// ten wstawia go do bazy danych
//		parser.addObserver(processor);
//
//		// rozpocznij pracê
//		reader.read();
//	}
//
//	private static void phase2(String[] words) {
//		// przygotowanie keywords
//		for (String word : words) {
//			Configuration.KEYWORDS.add(word);
//		}
//
//		// czyta plik
//		WikiXMLReader reader = new WikiXMLReader("E:/enwiki-latest-pages-meta-current.xml");
//
//		// parsuje go do obiektu WikiPage
//		WikiXMLPagesParser parser = new WikiXMLPagesParser();
//
//		// czyta WikiPage, dodaje go do listy.
//		OccurencesCounterProcessor processor = new OccurencesCounterProcessor();
//
//		// sprawia, ¿e po przeczytaniu page'a jest on wysy³any do parsera
//		reader.addObserver(parser);
//
//		// sprawia ¿e po przeczytaniu i sparsowaniu obiekt wysy³any jest do
//		// processora
//		// processor dodaje elementy do listy (kolejka priorytetowa)
//		parser.addObserver(processor);
//
//		// rozpocznij pracê
//		reader.read();
//
//		// wstaw do bazy rekordy z kolekcji
//		processor.finishJob();
//
//		// wyczyœæ s³ówka
//		Configuration.KEYWORDS.clear();
//	}
//
//	private static void phase3(String[] words) {
//		// przygotowanie keywords
//		for (String word : words) {
//			Configuration.KEYWORDS.add(word);
//		}
//
//		// stworzenie findera
//		OccurencesFinder finder = new OccurencesFinder();
//
//		// rozpoczêcie pracy
//		finder.findReferences();
//
//		// wyczyœæ s³ówka
//		Configuration.KEYWORDS.clear();
//	}
//
//	private static void phase4(String[] words) {
//		// przygotowanie keywords
//		for (String word : words) {
//			Configuration.KEYWORDS.add(word);
//		}
//
//		// czyta plik
//		WikiXMLReader reader = new WikiXMLReader("E:/enwiki-latest-pages-meta-current.xml");
//
//		// parsuje go do obiektu WikiPage
//		WikiXMLPagesParser parser = new WikiXMLPagesParser();
//
//		// stworzenie findera
//		OccurencesFinderAll finder = new OccurencesFinderAll("E:/enwiki-latest-pages-meta-current.xml");
//
//		// sprawia, ¿e po przeczytaniu page'a jest on wysy³any do parsera
//		reader.addObserver(parser);
//
//		// sprawia ¿e po przeczytaniu i sparsowaniu obiekt wysy³any jest do
//		// processora
//		// processor dodaje elementy do listy (kolejka priorytetowa)
//		parser.addObserver(finder);
//
//		// rozpoczêcie pracy
//		finder.findReferences();
//
//		// rozpocznij pracê
//		reader.read();
//
//		// wyczyœæ s³ówka
//		Configuration.KEYWORDS.clear();
//	}
//
//	private static void phase5(String word) {
//		GraphFileReader reader = new GraphFileReader("E:/enwiki-latest-pages-meta-current.xml");
//		reader.readLayer(word, 3);
//	}
//}
