package com.clearthoughts.clearcampus.assay.service;

import java.util.List;

import com.clearthoughts.clearcampus.assay.model.TestPaper;

public interface TestPaperService {
	public TestPaper createTestPaper(TestPaper testPaper);
	public TestPaper updateTestPaper(TestPaper testPaper);
	public TestPaper findTestPaperById(String testPaperId);
	public List<TestPaper> findAllTestPaperOfCompany(String companyId);
	public long deleteTestPaper(String testPaperId);
}
