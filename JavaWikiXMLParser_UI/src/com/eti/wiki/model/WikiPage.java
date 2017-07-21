package com.eti.wiki.model;

public class WikiPage {
	private long id;
	private String content;
	private String title;
	
	public WikiPage() {
		// TODO Auto-generated constructor stub
	}
	
	public WikiPage(long id, String content, String title) {
		super();
		this.id = id;
		this.content = content;
		this.title = title;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
