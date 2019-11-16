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

import com.clearthoughts.clearcampus.assay.model.QuestionBank;
import com.clearthoughts.clearcampus.assay.util.UniqueIdGenerator;
import com.clearthoughts.clearcampus.assay.util.UniqueIdGenerator.IdType;
import com.mongodb.client.result.DeleteResult;

@Repository
public class QuestionBankRepositoryImpl implements QuestionBankRepository {

	private static Logger logger = LoggerFactory.getLogger(QuestionBankRepositoryImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public Optional<QuestionBank> createQuestionBank(QuestionBank qb) {

		String uniqueId = UniqueIdGenerator.generateUniqueId(IdType.QUESTION_BANK);
		qb.set_id(uniqueId);
		try {
			QuestionBank save = template.save(qb);
			logger.info("New question bank created with _id:" + save.get_id() + " for company:" + save.getCompanyId());
			return Optional.of(save);
		} catch (Exception e) {
			logger.error(
					"Error in creation of question bank with _id:" + uniqueId + " for company:" + qb.getCompanyId(), e);
			return Optional.empty();
		}

	}

	@Override
	public Optional<QuestionBank> updateQuestionBank(QuestionBank qb) {
		try {

			QuestionBank save = template.save(qb);
			return Optional.of(save);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<QuestionBank> findQustionBankById(String qbId) {
		try {
			QuestionBank qb = template.findById(qbId, QuestionBank.class);
			logger.info("Question bank with id:" + qbId + " found. Returning");
			return Optional.of(qb);
		} catch (Exception e) {
			logger.error("Error in finding the question bank with id:" + qbId, e);
			return Optional.empty();
		}
	}

	@Override
	public List<QuestionBank> findAllQustionBankOfCompany(String companyId) {
		Query query = new Query(Criteria.where("companyId").is(companyId));
		try {
			List<QuestionBank> find = template.find(query, QuestionBank.class);
			return find;
		} catch (Exception e) {
			logger.error("Error in fining question banks for company with id:" + companyId, e);
			return new ArrayList<QuestionBank>();
		}
	}

	@Override
	public long deleteQuestionBank(QuestionBank qb) {
		return deleteQuestionById(qb.get_id());
	}

	@Override
	public long deleteQuestionById(String qbId) {
		try {
			QuestionBank qb = template.findById(qbId, QuestionBank.class);
			DeleteResult remove = template.remove(qb);
			if (remove.getDeletedCount() > 0) {
				logger.info("Question bank with id:" + qbId + " is removed from database");
			} else {
				logger.info("Question bank with id:" + qbId + " is not removed from database");
			}
			return remove.getDeletedCount();
		} catch (Exception e) {
			logger.error("Error in deleting question bank with id:" + qbId);
			return 0L;
		}
	}

}
