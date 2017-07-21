package com.eti.wiki.parser;

import java.io.StringReader;
import java.util.Observable;
import java.util.Observer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.model.Page;
import com.eti.wiki.model.WikiPage;

public class WikiXMLPagesParser extends Observable implements Observer {

	private DocumentBuilder builder;

	public WikiXMLPagesParser() {
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LoggerFactory.getLogger(getClass()).error("Parser not created error.");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String pageString = String.valueOf(arg);
			WikiPage page = parsePage(pageString);

			LoggerFactory.getLogger(getClass()).debug("Page parsed.");
		}
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
	
	private WikiPage parsePage(String pageString) {
		try {
			Document doc = loadXMLFromString(pageString);
			if (doc.hasChildNodes()) {
				Node root = doc.getElementsByTagName("page").item(0);
				if (root.hasChildNodes()) {

					NodeList nodeList = root.getChildNodes();
					WikiPage page = parsePageFromNodes(nodeList);

					setChanged();
					notifyObservers(page);
				}
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error("Parser not created error.");
			e.printStackTrace();
		}

		return null;
	}

	private WikiPage parsePageFromNodes(NodeList nodeList) {
		WikiPage page = new WikiPage();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equals("title")) {
					// titleNode = currentNode;
					page.setTitle(currentNode.getTextContent());

				}
				if (currentNode.getNodeName().equals("id")) {
					// idNode = currentNode;
					try {
						page.setId(Integer.parseInt(currentNode.getTextContent()));
					} catch (NumberFormatException e) {
						LoggerFactory.getLogger(getClass()).error("Parser not created error.");
					}
				}
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					if (currentNode.getNodeName().equals("revision")) {
						// contentNode=extractContent(currentNode);
						page.setContent(extractContent(currentNode).getTextContent());
					}
				}
			}
		}
		return page;
	}

	private Document loadXMLFromString(String xml) throws Exception {
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
}
