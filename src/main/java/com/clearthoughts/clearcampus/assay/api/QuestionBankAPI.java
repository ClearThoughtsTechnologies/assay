package com.clearthoughts.clearcampus.assay.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.clearthoughts.clearcampus.assay.api.resources.QuestionBankModelAssembler;
import com.clearthoughts.clearcampus.assay.api.resources.QuestionBankResource;
import com.clearthoughts.clearcampus.assay.model.QuestionBank;
import com.clearthoughts.clearcampus.assay.service.QuestionBankService;

@Controller
@RequestMapping(path="/assay/questionbank")
public class QuestionBankAPI {
	
	@Autowired
	private QuestionBankService service;
	
	@Autowired
	private QuestionBankModelAssembler assembler;
	
	@PostMapping
	public ResponseEntity<QuestionBankResource> createQuestionBank(QuestionBankResource qbr)
	{
		QuestionBank qb = new QuestionBank(qbr.get_id(), qbr.getCompanyId(), qbr.getName(), qbr.getDescription());
		QuestionBank nqb = service.createQuestionBank(qb);
		QuestionBankResource nqbr = assembler.toModel(nqb);
		
		return ResponseEntity.created(nqbr.getLink("self").get().toUri()).body(nqbr);
	}
	
	@PutMapping
	public ResponseEntity<QuestionBankResource> updateQuestionBank(QuestionBankResource qbr)
	{
		QuestionBank qb = new QuestionBank(qbr.get_id(), qbr.getCompanyId(), qbr.getName(), qbr.getDescription());
		QuestionBank nqb = service.updateQuestionBank(qb);
		QuestionBankResource nqbr = assembler.toModel(nqb);
		
		return ResponseEntity.ok(nqbr);
	}
	
	@GetMapping(path = "/{questionBankId}")
	public ResponseEntity<QuestionBankResource> getQuestionBankById(@PathVariable(value = "questionBankId") String questionBankId)
	{
		QuestionBank findQuestionBankById = service.findQuestionBankById(questionBankId);
		return ResponseEntity.ok(assembler.toModel(findQuestionBankById));
	}
	
	@GetMapping(path = "/company/{companyId}")
	public ResponseEntity<CollectionModel<QuestionBankResource>> getAllQuestionBankOfCompany(@PathVariable(value = "companyId") String companyId)
	{
		List<QuestionBank> findAllQuestionBankOfCompany = service.findAllQuestionBankOfCompany(companyId);
		return ResponseEntity.ok(assembler.toCollectionModel(findAllQuestionBankOfCompany));
	}
	
	@DeleteMapping(path = "/{questionBankId}")
	public ResponseEntity<Long> deleteQuestionBankById(@PathVariable(value = "questionBankId") String questionBankId)
	{
		long noOfDeletion = service.deleteQuestionBankById(questionBankId);
		return ResponseEntity.ok(noOfDeletion);
	}
	
	@PostMapping(path = "/{questionBankId}/file")
	public ResponseEntity<Boolean> addQuestionFromFile(@RequestBody MultipartFile file)
	{
		boolean result = service.addQuestionToQuestionBankFromFile(file);
		return ResponseEntity.ok(result);
	}
	
}
