package com.clearthoughts.clearcampus.assay.repository;

import java.util.List;
import java.util.Optional;

import com.clearthoughts.clearcampus.assay.model.TestPaper;

public interface TestPaperRepository {
	public Optional<TestPaper> createTestPaper(TestPaper tp);
	public Optional<TestPaper> updateTestPaper(TestPaper tp);
	public Optional<TestPaper> findTestPaperById(String tpId);
	public List<TestPaper> findAllTestPaperOfCompany(String companyId);
	public long deleteTestPaperById(String tpId);
}
