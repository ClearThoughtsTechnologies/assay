package com.clearthoughts.clearcampus.assay.repository;

import java.util.List;
import java.util.Optional;

import com.clearthoughts.clearcampus.assay.model.Question;

public interface QuestionRepository {
	public Optional<Question> createQuestion(Question q);
	public Optional<Question> updateQuestion(Question q);
	public Optional<Question> findQuestionById(String questionId);
	public List<Question> findAllQuestionsForQuestionBank(String questionBankId);
	public long deleteQuestion(Question q);
	public long deleteQuestionById(String questionId);
}
