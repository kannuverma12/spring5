package com.s5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.s5.entity.SplittedDocuments;


public interface SplittedDocumentsRepository extends CrudRepository<SplittedDocuments, Integer>{

	
	@Query("select * from splitted_documents")
    public List<SplittedDocuments> getAllSplittedDocuments();
}
