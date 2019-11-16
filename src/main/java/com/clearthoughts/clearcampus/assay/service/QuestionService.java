package com.clearthoughts.clearcampus.assay.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clearthoughts.clearcampus.assay.model.Question;

public interface QuestionService {
	public Question createNewQuestion(Question q);
	public Question updateQuestion(Question q);
	public Question findQuestionById(String questionId);
	public List<Question> findAllQuestionOfQuestionBank(String questionBankId);
	public long deleteQuestion(Question q);
	public long deleteQuestionById(String questionId);
	public long deleteAllQuestionsOfQuestionBank(String questionBankId);
	
}
