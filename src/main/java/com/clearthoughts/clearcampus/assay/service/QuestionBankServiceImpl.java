package com.clearthoughts.clearcampus.assay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clearthoughts.clearcampus.assay.exception.AssayException;
import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.model.QuestionBank;
import com.clearthoughts.clearcampus.assay.repository.QuestionBankRepository;
import com.clearthoughts.clearcampus.assay.util.QuestionFileUtil;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

	@Autowired
	private QuestionBankRepository qbRepo;

	@Autowired
	private QuestionService questionService;

	@Override
	public QuestionBank createQuestionBank(QuestionBank qb) {
		Optional<QuestionBank> qbo = qbRepo.createQuestionBank(qb);
		if (!qbo.isPresent()) {
			throw new AssayException("Unable to create Question bank. Please try again");
		}
		return qbo.get();
	}

	@Override
	public QuestionBank updateQuestionBank(QuestionBank qb) {

		Optional<QuestionBank> qbo = qbRepo.updateQuestionBank(qb);

		if (!qbo.isPresent()) {
			throw new AssayException("Unable to update Question bank. Please try again");
		}
		return qbo.get();
	}

	@Override
	public boolean addQuestionToQuestionBankFromFile(MultipartFile file) {
		List<Question> generateQuestionFromFile = QuestionFileUtil.generateQuestionFromFile(file);
		if (generateQuestionFromFile == null || generateQuestionFromFile.size() == 0) {
			throw new AssayException(
					"Unable to create questions from uploaded file. Please check the file internal format. Please follow the structure of sample file");
		}
		List<Question> notCreatedQuestionList = new ArrayList<>();
		generateQuestionFromFile.forEach(q -> {
			try {
				Question cq = questionService.createNewQuestion(q);
			} catch (Exception e) {
				notCreatedQuestionList.add(q);
			}
		});
		if (notCreatedQuestionList.size() > 0) {
			String message = "";
			if (notCreatedQuestionList.size() == generateQuestionFromFile.size()) {
				message = "No question added. Please try again";
			} else {
				StringBuilder builder = new StringBuilder("Following questions are not added. Please try again.\n");
				notCreatedQuestionList.forEach(q -> {
					builder.append(q.getQuestionText());
					builder.append("\n");
				});
				message = builder.toString();
			}

			throw new AssayException(message);
		}
		return true;
	}

	@Override
	public QuestionBank findQuestionBankById(String questionBankId) {
		Optional<QuestionBank> qb = qbRepo.findQustionBankById(questionBankId);
		if (!qb.isPresent()) {
			throw new AssayException("No question bank found with id:" + questionBankId);
		}
		return qb.get();
	}

	@Override
	public List<QuestionBank> findAllQuestionBankOfCompany(String companyId) {
		List<QuestionBank> qbs = qbRepo.findAllQustionBankOfCompany(companyId);
		if (qbs == null || qbs.size() == 0) {
			throw new AssayException("No Question bank found for company with id:" + companyId);
		}
		return qbs;
	}

	@Override
	public long deleteQuestionBankById(String questionBankId) {
		try {
			questionService.deleteAllQuestionsOfQuestionBank(questionBankId);
			return qbRepo.deleteQuestionById(questionBankId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public long deleteQuestionBank(QuestionBank qb) {
		return deleteQuestionBankById(qb.get_id());
	}

}
