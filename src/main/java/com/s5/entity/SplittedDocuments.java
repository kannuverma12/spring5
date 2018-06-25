package com.s5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="splitted_documents")

public class SplittedDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "dms_doc_id")
	private Integer dmsDocId;
	
	@Column(name = "splitted_doc_id")
	private Integer splittedDocId;
	
	@Column(name = "application_id")
	private String applicationId;
	
	@Column(name = "page_number")
	private Integer pageNumber;
	
	@Column(name = "tagged_status")
	private Boolean tagged;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDmsDocId() {
		return dmsDocId;
	}

	public void setDmsDocId(Integer dmsDocId) {
		this.dmsDocId = dmsDocId;
	}

	public Integer getSplittedDocId() {
		return splittedDocId;
	}

	public void setSplittedDocId(Integer splittedDocId) {
		this.splittedDocId = splittedDocId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Boolean getTagged() {
		return tagged;
	}

	public void setTagged(Boolean tagged) {
		this.tagged = tagged;
	}
	
	
}
