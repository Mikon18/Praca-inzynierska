package com.eti.wiki.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pagerank")
public class PageRank {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="value")
	private double value;
	
	@Column(name="ref_id")
	private long ref_id;
	
	
	public PageRank() {
		// TODO Auto-generated constructor stub
	}
	
	public PageRank(long id, String title, double value, long ref_id) {
		super();
		this.id = id;
		this.title = title;
		this.value = value;
		this.ref_id = ref_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public long getRef_id() {
		return ref_id;
	}

	public void setRef_id(long ref_id) {
		this.ref_id = ref_id;
	}
	

}
