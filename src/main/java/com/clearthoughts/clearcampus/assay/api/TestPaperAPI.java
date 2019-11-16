package com.clearthoughts.clearcampus.assay.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clearthoughts.clearcampus.assay.api.resources.TestPaperModelAssemler;
import com.clearthoughts.clearcampus.assay.api.resources.TestPaperResource;
import com.clearthoughts.clearcampus.assay.model.TestPaper;
import com.clearthoughts.clearcampus.assay.service.TestPaperService;

@Controller
@RequestMapping(path = "/assay/testpaper")
public class TestPaperAPI {
	
	@Autowired
	private TestPaperService service;
	
	@Autowired
	private TestPaperModelAssemler assembler;

	@PostMapping
	public ResponseEntity<TestPaperResource> createTestPaper(TestPaperResource tpr) {
		TestPaper tp = new TestPaper(tpr.get_id(), tpr.getCompanyId(), tpr.getName(), tpr.getDescription(),
				tpr.getAttempt(), tpr.getAppearance(), tpr.getEvaluation(), tpr.getResultDeclare(),
				 tpr.isPublicTestPaper(), tpr.getQuestions());
		TestPaper ntp = service.createTestPaper(tp);
		TestPaperResource model = assembler.toModel(ntp);
		return ResponseEntity.created(model.getLink("self").get().toUri()).body(model);
	}

	@PutMapping
	public ResponseEntity<TestPaperResource> updateTestPaper(TestPaperResource tpr) {
		TestPaper tp = new TestPaper(tpr.get_id(), tpr.getCompanyId(), tpr.getName(), tpr.getDescription(),
				tpr.getAttempt(), tpr.getAppearance(), tpr.getEvaluation(), tpr.getResultDeclare(),
				tpr.isPublicTestPaper(), tpr.getQuestions());
		TestPaper ntp = service.createTestPaper(tp);
		TestPaperResource model = assembler.toModel(ntp);
		return ResponseEntity.ok(model);
	}

	@GetMapping(path = "/{testPaperId}")
	public ResponseEntity<TestPaperResource> findTestPaperForId(@PathVariable String testPaperId) {
		TestPaper tp = service.findTestPaperById(testPaperId);
		TestPaperResource model = assembler.toModel(tp);
		return ResponseEntity.ok(model);
	}

	@GetMapping(path = "/company/{companyId}")
	public ResponseEntity<CollectionModel<TestPaperResource>> findAllTestPaperForCompany(String companyId) {
		List<TestPaper> allTps = service.findAllTestPaperOfCompany(companyId);
		CollectionModel<TestPaperResource> collectionModel = assembler.toCollectionModel(allTps);
		return ResponseEntity.ok(collectionModel);
	}

	@DeleteMapping(path = "/{testPaperId}")
	public ResponseEntity<Long> deleteTestPaper(String testPaperId) {
		long count = service.deleteTestPaper(testPaperId);
		return ResponseEntity.ok(count);
	}

}
