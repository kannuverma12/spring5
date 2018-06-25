package com.s5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s5.dao.SplittedDocumentsRepository;
import com.s5.entity.SplittedDocuments;
import com.s5.service.SplittedDocumentService;

@Service
public class SplittedDocumentServiceImpl implements SplittedDocumentService {
	
	@Autowired
	private SplittedDocumentsRepository splittedDocumentsRepository;

	public List<SplittedDocuments> getAllSplittedDocuments() {
		// TODO Auto-generated method stub
		return splittedDocumentsRepository.getAllSplittedDocuments();
	}

	

	
}
