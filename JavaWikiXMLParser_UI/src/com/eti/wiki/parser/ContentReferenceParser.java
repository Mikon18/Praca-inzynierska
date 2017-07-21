package com.eti.wiki.parser;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.LoggerFactory;

public class ContentReferenceParser {
	public static Set<String> parseReferences(String pageContent) {
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
					LoggerFactory.getLogger(ContentReferenceParser.class).info("aioobe");
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
}
