package com.clearthoughts.clearcampus.assay.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.util.UniqueIdGenerator;
import com.clearthoughts.clearcampus.assay.util.UniqueIdGenerator.IdType;
import com.mongodb.client.result.DeleteResult;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

	private static Logger logger = LoggerFactory.getLogger(QuestionRepositoryImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public Optional<Question> createQuestion(Question q) {
		String uniqueId = UniqueIdGenerator.generateUniqueId(IdType.QUESTION);
		q.set_id(uniqueId);
		try {
			Question save = template.save(q);
			logger.info("New question created with _id:" + save.get_id() + " for question bank:"
					+ save.getQuestionBankId());
			return Optional.of(save);
		} catch (Exception e) {
			logger.error("Error in creation of question with _id:" + uniqueId + " for question bank:"
					+ q.getQuestionBankId(), e);
			return Optional.empty();
		}
	}

	@Override
	public Optional<Question> updateQuestion(Question q) {
		try {

			Question save = template.save(q);
			return Optional.of(save);
		} catch (Exception e) {
			logger.error("Error in update of question  with _id:" + q.get_id() + " for question bank:"
					+ q.getQuestionBankId(), e);
			return Optional.empty();
		}
	}

	@Override
	public Optional<Question> findQuestionById(String questionId) {
		try {
			Question q = template.findById(questionId, Question.class);
			logger.info("Question  with id:" + questionId + " found. Returning");
			return Optional.of(q);
		} catch (Exception e) {
			logger.error("Error in finding the question with id:" + questionId, e);
			return Optional.empty();
		}
	}

	@Override
	public List<Question> findAllQuestionsForQuestionBank(String questionBankId) {
		try {
			Query query = new Query(Criteria.where("questionBankId").is(questionBankId));
			return template.find(query, Question.class);
		} catch (Exception e) {
			logger.error("Error in finding the questions of question bank with id:" + questionBankId, e);
			return new ArrayList<>();
		}
	}

	@Override
	public long deleteQuestion(Question q) {
		return deleteQuestionById(q.get_id());
	}

	@Override
	public long deleteQuestionById(String questionId) {
		try {
			Question q = template.findById(questionId, Question.class);
			DeleteResult remove = template.remove(q);
			if (remove.getDeletedCount() > 0) {
				logger.info("Question with id:" + questionId + " is removed from database");
			} else {
				logger.info("Question with id:" + questionId + " is not removed from database");
			}
			return remove.getDeletedCount();
		} catch (Exception e) {
			logger.error("Error in deleting question with id:" + questionId);
			return 0L;
		}
	}

}
