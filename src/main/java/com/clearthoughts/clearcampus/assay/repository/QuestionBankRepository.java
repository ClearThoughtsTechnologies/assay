package com.clearthoughts.clearcampus.assay.repository;

import java.util.List;
import java.util.Optional;

import com.clearthoughts.clearcampus.assay.model.QuestionBank;

public interface QuestionBankRepository {
	public Optional<QuestionBank> createQuestionBank(QuestionBank qb);
	public Optional<QuestionBank> updateQuestionBank(QuestionBank qb);
	public Optional<QuestionBank> findQustionBankById(String qbId);
	public List<QuestionBank> findAllQustionBankOfCompany(String companyId);
	public long deleteQuestionBank(QuestionBank qb);
	public long deleteQuestionById(String qbId);
}
