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
import com.clearthoughts.clearcampus.assay.model.TestPaper;
import com.clearthoughts.clearcampus.assay.util.UniqueIdGenerator;
import com.clearthoughts.clearcampus.assay.util.UniqueIdGenerator.IdType;
import com.mongodb.client.result.DeleteResult;

@Repository
public class TestPaperRepositoryImpl implements TestPaperRepository {

	private static Logger logger = LoggerFactory.getLogger(TestPaperRepositoryImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public Optional<TestPaper> createTestPaper(TestPaper tp) {
		String generateUniqueId = UniqueIdGenerator.generateUniqueId(IdType.TEST_PAPER);
		tp.set_id(generateUniqueId);
		try {
			TestPaper save = template.save(tp);
			logger.info("New testpaper created with _id:" + save.get_id() + " for company:"
					+ save.getCompanyId());
			return Optional.of(save);
		} catch (Exception e) {
			logger.error("Error in creation of testpaper with _id:" + generateUniqueId + " for company:"
					+ tp.getCompanyId(), e);
			return Optional.empty();
		}
	}

	@Override
	public Optional<TestPaper> updateTestPaper(TestPaper tp) {
		try {
			TestPaper save = template.save(tp);
			logger.info("Test with _id:" + save.get_id() + " for company:"
					+ save.getCompanyId());
			return Optional.of(save);
		} catch (Exception e) {
			logger.error("Error in creation of question with _id:" + tp.get_id() + " for company:"
					+ tp.getCompanyId(), e);
			return Optional.empty();
		}
	}

	@Override
	public Optional<TestPaper> findTestPaperById(String tpId) {
		try {
			TestPaper q = template.findById(tpId, TestPaper.class);
			logger.info("Testpaper  with id:" + tpId + " found. Returning");
			return Optional.of(q);
		} catch (Exception e) {
			logger.error("Error in finding the testpaper with id:" + tpId, e);
			return Optional.empty();
		}
	}

	@Override
	public List<TestPaper> findAllTestPaperOfCompany(String companyId) {
		try {
			Query query = new Query(Criteria.where("companyId").is(companyId));
			return template.find(query, TestPaper.class);
		} catch (Exception e) {
			logger.error("Error in finding the testpapers for company with id:" + companyId, e);
			return new ArrayList<>();
		}
	}

	@Override
	public long deleteTestPaperById(String tpId) {
		try {
			TestPaper q = template.findById(tpId, TestPaper.class);
			DeleteResult remove = template.remove(q);
			if (remove.getDeletedCount() > 0) {
				logger.info("Testpaper with id:" + tpId + " is removed from database");
			} else {
				logger.info("TestPaper with id:" + tpId + " is not removed from database");
			}
			return remove.getDeletedCount();
		} catch (Exception e) {
			logger.error("Error in deleting testpaper with id:" + tpId);
			return 0L;
		}
	}

}
