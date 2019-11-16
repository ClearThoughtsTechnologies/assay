package com.clearthoughts.clearcampus.assay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clearthoughts.clearcampus.assay.exception.AssayException;
import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final String BLANK_STRING = "";
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question createNewQuestion(Question q) {

		Optional<Question> createQuestion = questionRepository.createQuestion(q);
		if (!createQuestion.isPresent()) {
			throw new AssayException("Unable to create question");
		}
		return createQuestion.get();
	}

	

	@Override
	public Question updateQuestion(Question q) {
		Optional<Question> oq = questionRepository.updateQuestion(q);
		if (!oq.isPresent()) {
			throw new AssayException("Question is not updated. Please try again");
		}
		return oq.get();
	}

	@Override
	public Question findQuestionById(String questionId) {
		Optional<Question> oq = questionRepository.findQuestionById(questionId);
		if (!oq.isPresent()) {
			throw new AssayException("Question is not found. Please try again with proper question id");
		}
		return oq.get();
	}

	@Override
	public List<Question> findAllQuestionOfQuestionBank(String questionBankId) {
		List<Question> questions = questionRepository.findAllQuestionsForQuestionBank(questionBankId);
		if (questions == null || questions.size() == 0) {
			throw new AssayException("No question found for question bank with id:" + questionBankId);
		}
		return questions;
	}

	@Override
	public long deleteQuestion(Question q) {
		long res = questionRepository.deleteQuestion(q);
		if (res == 0) {
			throw new AssayException("No question deleted");
		}
		return res;
	}

	@Override
	public long deleteQuestionById(String questionId) {
		long res = questionRepository.deleteQuestionById(questionId);
		if (res == 0) {
			throw new AssayException("No question deleted");
		}
		return res;
	}

	@Override
	public long deleteAllQuestionsOfQuestionBank(String questionBankId) {
		try {
			List<Question> allQuestions = findAllQuestionOfQuestionBank(questionBankId);
			allQuestions.forEach(q->deleteQuestion(q));
			return allQuestions.size();
		} catch (Exception e) {
			throw new AssayException("No question found for question bank with id:"+questionBankId);
		}
	}

}
