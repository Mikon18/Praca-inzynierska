package com.eti.wiki.parser.phase5;

import com.eti.wiki.Configuration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.model.Page;
import com.eti.wiki.model.Reference;
import com.eti.wiki.model.WikiPage;
import com.eti.wiki.model.collection.PageContent;
import com.eti.wiki.model.collection.ReferencePair;
import com.eti.wiki.ui.IParsingProgressListener;
import com.eti.wiki.ui.IStoppable;

/**
 * Celem graph file readera jest przeczytanie pliku w celu odnalezienia
 * refrences z warstwy.
 *
 * @author dawid
 *
 */
public class GraphFileReader implements IStoppable {

	private String fileName;
	private BufferedReader fileReader;
	private IParsingProgressListener listener;
	private int counter;
	private int deep;
	private boolean isRunning = true;

	public GraphFileReader(String name, IParsingProgressListener listener, int depth) {
		this.listener = listener;
		this.counter = 0;
		this.deep = depth;
		fileName = name;
	}

	public void stop() {
		isRunning = false;
	}

	public void readLayer(String keyword, int depth) {
		HashSet<String> layer_set = new HashSet<>();

		layer_set.add(keyword);
		HashMap<String, WikiPage> map = getReferencesFromLevel(layer_set);
		for (int i = 0; i < depth; i++) {
			layer_set.clear();
			if(!isRunning){
				break;
			}
			for (Entry<String, WikiPage> entry : map.entrySet()) {
				Set<String> references = parseReferences(entry.getValue().getContent());
				if(!isRunning){
					break;
				}
				layer_set.addAll(references);
			}

			HashMap<String, WikiPage> referencesIds = getReferencesFromLevel(layer_set);
			LoggerFactory.getLogger(getClass()).warn("Starting layer:" + i + " " + map.entrySet().size());
			for (Entry<String, WikiPage> entry : map.entrySet()) {
				if(!isRunning){
					break;
				}
				for (String reference : layer_set) {
					if (entry.getValue() != null && referencesIds.get(reference) != null) {
						putIntoDatabase(entry.getValue().getId(), referencesIds.get(reference).getId(), keyword);
					} else {
						// LoggerFactory.getLogger(getClass()).error("Some
						// null.");
					}
				}
			}
			map = referencesIds;
			LoggerFactory.getLogger(getClass()).warn("Finished layer:" + i);
		}
	}

	private void putIntoDatabase(long parentId, long referenceId, String keyword) {
		// LoggerFactory.getLogger(getClass()).info("Wstawiam rekord");
		Session session = DatabaseSession.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			if (parentId != -1 && referenceId != -1) {
				session.save(new Reference(parentId, referenceId, keyword));
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

	public Set<String> parseReferences(String pageContent) {
		Set<String> referenceSet = new HashSet<String>();
		String substring = pageContent;
		String referenceSubstring = null, referenceTitle = null;

		int referenceStart = substring.indexOf("[["), referenceEnd = -1;
		while (referenceStart > 0) {
			if(!isRunning){
				break;
			}
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
								// LoggerFactory.getLogger(getClass()).info("aioobe");
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

	public HashMap<String, WikiPage> getReferencesFromLevel(Set<String> references) {

		return readFile(references);
	}

	private void closeFile() {
		try {
			fileReader.close();
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error("Error opening file: " + e.getMessage());
			e.printStackTrace();
		}
		LoggerFactory.getLogger(getClass()).info("File has been closed.");
	}

	private void openFile() {
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error("Error opening file: " + e.getMessage());
			e.printStackTrace();
		}
		LoggerFactory.getLogger(getClass()).info("File has been opened");
	}

	private HashMap<String, WikiPage> readFile(Set<String> references) {
		HashMap<String, WikiPage> map = new HashMap<>();
		openFile();
		LoggerFactory.getLogger(getClass()).info("File read started.");
		ReferencePair page = readPage();

		int counter = 0;
		Long timeStart = System.currentTimeMillis();
		while (page != null) {
			if(!isRunning){
				break;
			}
			if (references.contains(page.title)) {
				map.put(page.title, parsePage(page.content));
				if (references.size() == map.size()) {
					return map;
				}
			}

			page = readPage();

			listener.progressChanged(counter++, Configuration.PAGES_ALL * deep);
			if (((counter++) % 100000) == 0) {
				LoggerFactory.getLogger(getClass()).info("Parsed: " + counter + " pages. Parse time = "
						+ String.valueOf(System.currentTimeMillis() - timeStart));
				timeStart = System.currentTimeMillis();
				System.gc();
			}
			if (counter % 1000 == 0) {
				listener.currentlyProcessedPageChanged(page.title);
			}
		}
		LoggerFactory.getLogger(getClass()).info("File read finished.");
		closeFile();
		return map;
	}

	private ReferencePair readPage() {
		StringBuilder builder = new StringBuilder();

		String line = null;
		String extractedTitle = null;
		boolean pageStarted = false, pageEnded = false;
		try {
			line = fileReader.readLine();

			if (line == null) {
				return null;
			}

			while (line != null && !pageEnded) {
				if (line.contains("<title>") && line.contains("</title>")) {
					try {
						extractedTitle = line.split("<title>")[1].split("</title>")[0];
					} catch (Exception e) {
						LoggerFactory.getLogger(getClass())
								.info("Title error: " + e.getMessage() + " on title: " + line);
					}
				}
				if (line.contains("<page>")) {
					pageStarted = true;
				}
				if (pageStarted) {
					builder.append(line);
				}
				if (line.contains("</page>")) {
					pageEnded = true;
				} else {
					line = fileReader.readLine();
				}
			}
			LoggerFactory.getLogger(getClass()).debug("Page is : " + builder.toString());
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error("Error reading file: " + e.getMessage());
			e.printStackTrace();
		}

		return new ReferencePair(extractedTitle, builder.toString());
	}

	private WikiPage parsePage(String pageString) {
		try {
			Document doc = loadXMLFromString(pageString);
			if (doc.hasChildNodes()) {
				Node root = doc.getElementsByTagName("page").item(0);
				if (root.hasChildNodes()) {
					// Node idNode = null, titleNode = null, contentNode= null;

					NodeList nodeList = root.getChildNodes();
					return parsePageForGraph(nodeList);
				}
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error("Parser not created error.");
			e.printStackTrace();
		}
		LoggerFactory.getLogger(getClass()).warn("Page didnt initialize.");
		return null;
	}

	private Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	private WikiPage parsePageForGraph(NodeList nodeList) {
		String content = null, title = null;
		long id = -1;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equals("title")) {
					// titleNode = currentNode;
					title = currentNode.getTextContent();

				}
				if (currentNode.getNodeName().equals("id")) {
					// idNode = currentNode;
					try {
						id = (Integer.parseInt(currentNode.getTextContent()));
					} catch (NumberFormatException e) {
						LoggerFactory.getLogger(getClass()).error("Parser not created error.");
					}
				}
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					if (currentNode.getNodeName().equals("revision")) {
						// contentNode=extractContent(currentNode);
						content = extractContent(currentNode).getTextContent();
					}
				}
			}
		}
		return new WikiPage(id, content, title);
	}

	private Node extractContent(Node revisionNode) {
		NodeList nodeList = revisionNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName().equals("text")) {
				return currentNode;
			}
		}
		return null;
	}
}
