package com.eti.wiki.model.collection;

import org.apache.commons.lang3.StringUtils;

import com.eti.wiki.Configuration;
import com.eti.wiki.model.WikiPage;

public class PageContent implements Comparable<PageContent> {

	private WikiPage page;
	private int keywordCount;

	public PageContent(WikiPage page) {
		super();
		this.page = page;
		this.keywordCount = 0;
		
		for(String keyword : Configuration.KEYWORDS){
			addKeywordCount(keyword);
		}
	}

	public void addKeywordCount(String keyword) {
		keywordCount += StringUtils.countMatches(page.getContent(), keyword);
	}

	@Override
	public int compareTo(PageContent o) {
		if (o.keywordCount < keywordCount) {
			return 1;
		} else if (o.keywordCount == keywordCount) {
			return 0;
		}
		return -1;
	}

	public WikiPage getPage() {
		return page;
	}

	public void setPage(WikiPage page) {
		this.page = page;
	}

	public int getKeywordCount() {
		return keywordCount;
	}

	public void setKeywordCount(int keywordCount) {
		this.keywordCount = keywordCount;
	}
}
