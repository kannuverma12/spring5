package com.s5.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s5.entity.SplittedDocuments;
import com.s5.model.DatabaseData;
import com.s5.service.SplittedDocumentService;
import com.s5.utilities.HibernateUtility;

@RestController
@RequestMapping("/split")
public class SplittedDocumentController {
	
	@Autowired
	private SplittedDocumentService splittedDocumentService;
	
	@Autowired
	private HibernateUtility hibernateUtility;
	
	@RequestMapping("/getSplittedDocuments")
	public List<SplittedDocuments> getSplittedDocuments() {
		
		return splittedDocumentService.getAllSplittedDocuments();
	}
	
	@PostConstruct
	public void init() {
		
		System.out.println("hibernate Utitlity init called...");
		
		DatabaseData dd = new DatabaseData();
		dd.setDatabaseIP("192.168.201.53");
		dd.setDatabaseName("MYSQL");
		dd.setDatabasePassword("Karan@123");
		dd.setDatabasePort("3306");
		dd.setDatabaseSchema("CT_docverification");
		dd.setDatabaseUserName("karan");
		dd.setDataSourceDriver("com.mysql.jdbc.Driver");
		dd.setTimeout(300000);

		hibernateUtility.buildSessionFactory(dd);
	}
}
