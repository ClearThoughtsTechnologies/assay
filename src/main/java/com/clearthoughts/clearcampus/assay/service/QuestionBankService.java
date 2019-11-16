package com.clearthoughts.clearcampus.assay.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clearthoughts.clearcampus.assay.model.QuestionBank;

public interface QuestionBankService {
	public QuestionBank createQuestionBank(QuestionBank qb);
	public QuestionBank updateQuestionBank(QuestionBank qb);
	public boolean addQuestionToQuestionBankFromFile(MultipartFile file);
	public QuestionBank findQuestionBankById(String questionBankId);
	public List<QuestionBank> findAllQuestionBankOfCompany(String companyId);
	public long deleteQuestionBankById(String questionBankId);
	public long deleteQuestionBank(QuestionBank qb);
}
