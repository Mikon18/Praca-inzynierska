package com.eti.wiki.model.collection;

public class ReferenceConnection {
	private String from;
	private String to;
	private String keyword;
	public ReferenceConnection(String from, String to, String keyword) {
		super();
		this.from = from;
		this.to = to;
		this.keyword = keyword;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
