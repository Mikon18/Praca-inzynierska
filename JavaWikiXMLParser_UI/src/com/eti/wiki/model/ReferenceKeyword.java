package com.eti.wiki.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wikireferencekeyword")
public class ReferenceKeyword {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name = "page_id")
	private long page_id;

	@Column(name = "reference_id")
	private long ref_id;
	
	@Column(name="keyword")
	private String keyword;

	public ReferenceKeyword() {
		// TODO Auto-generated constructor stub
	}
	
	public ReferenceKeyword(long page_id, long ref_id,String keyword) {
		super();
		this.page_id = page_id;
		this.ref_id = ref_id;
		this.keyword = keyword;
	}

	public long getPage_id() {
		return page_id;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setPage_id(long page_id) {
		this.page_id = page_id;
	}

	public long getRef_id() {
		return ref_id;
	}

	public void setRef_id(long ref_id) {
		this.ref_id = ref_id;
	}
}
