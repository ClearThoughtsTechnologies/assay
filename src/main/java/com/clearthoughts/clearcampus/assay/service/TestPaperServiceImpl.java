package com.clearthoughts.clearcampus.assay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clearthoughts.clearcampus.assay.exception.AssayException;
import com.clearthoughts.clearcampus.assay.model.TestPaper;
import com.clearthoughts.clearcampus.assay.repository.TestPaperRepository;

@Service
public class TestPaperServiceImpl implements TestPaperService {

	@Autowired
	private TestPaperRepository repository;
	
	@Override
	public TestPaper createTestPaper(TestPaper testPaper) {
		Optional<TestPaper> tp = repository.createTestPaper(testPaper);
		if(!tp.isPresent()) {
			throw new AssayException("Unable to create Test paper");
		}
		return tp.get();
	}

	@Override
	public TestPaper updateTestPaper(TestPaper testPaper) {
		Optional<TestPaper> tp = repository.createTestPaper(testPaper);
		if(!tp.isPresent()) {
			throw new AssayException("Unable to update Test paper with id:"+testPaper.get_id());
		}
		return tp.get();
	}

	@Override
	public TestPaper findTestPaperById(String testPaperId) {
		Optional<TestPaper> tp = repository.findTestPaperById(testPaperId);
		
		if(!tp.isPresent()) {
			throw new AssayException("Unable to find Test paper by id:"+testPaperId);
		}
		return tp.get();
	}

	@Override
	public List<TestPaper> findAllTestPaperOfCompany(String companyId) {
		List<TestPaper> allTps = repository.findAllTestPaperOfCompany(companyId);
		if(allTps == null || allTps.size() == 0)
		{
			throw new AssayException("No test paper found for company with id:"+companyId);
		}
		return allTps;
	}

	@Override
	public long deleteTestPaper(String testPaperId) {
		long count = repository.deleteTestPaperById(testPaperId);
		if(count  == 0)
		{
			throw new AssayException("Testpaper with id:"+testPaperId+" was not deleted");
		}
		
		return count;
	}

}
