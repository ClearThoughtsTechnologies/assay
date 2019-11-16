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

import com.clearthoughts.clearcampus.assay.api.resources.QuestionModelAssembler;
import com.clearthoughts.clearcampus.assay.api.resources.QuestionResource;
import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.service.QuestionService;

@Controller
@RequestMapping(path = "/assay/questionbank/{questionBankId}/question")
public class QuestionAPI {

	@Autowired
	private QuestionService service;
	
	@Autowired
	private QuestionModelAssembler questionModelAssembler;
	
	@PostMapping
	public ResponseEntity<QuestionResource> createQuesiton(@PathVariable("questionBankId") String questionBankId, @RequestBody QuestionResource qr)
	{
		if(qr.getQuestionBankId() == null)
		{
			qr.setQuestionBankId(questionBankId);
		}
		Question q = new Question(qr.get_id(), qr.getQuestionBankId(), qr.getQuestionType(), qr.getQuestionText(), qr.getOptions(), qr.getAnswerText());
		Question nq = service.createNewQuestion(q);
		QuestionResource model = questionModelAssembler.toModel(nq);
		return ResponseEntity.created(model.getLink("self").get().toUri()).body(model);
		
	}
	
	@PutMapping
	public ResponseEntity<QuestionResource> updateQuestion(@PathVariable("questionBankId") String questionBankId, @RequestBody Question qr)
	{
		if(qr.getQuestionBankId() == null)
		{
			qr.setQuestionBankId(questionBankId);
		}
		Question q = new Question(qr.get_id(), qr.getQuestionBankId(), qr.getQuestionType(), qr.getQuestionText(), qr.getOptions(), qr.getAnswerText());
		Question uq = service.updateQuestion(q);
		return ResponseEntity.ok(questionModelAssembler.toModel(uq));
	}
	
	@GetMapping(path = "/{questionId}")
	public ResponseEntity<QuestionResource> getQuestionById(@PathVariable String questionId)
	{
		Question q = service.findQuestionById(questionId);
		return ResponseEntity.ok(questionModelAssembler.toModel(q));
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<QuestionResource>> getAllQuesitonOfQuestionBank(@PathVariable("questionBankId") String questionBankId)
	{
		List<Question> allQ = service.findAllQuestionOfQuestionBank(questionBankId);
		CollectionModel<QuestionResource> collectionModel = questionModelAssembler.toCollectionModel(allQ);
		
		return ResponseEntity.ok(collectionModel);
	}
	
	@DeleteMapping(path="/{questionId}")
	public ResponseEntity<Long> deleteQuesitonByQuestionId(@PathVariable String questionId)
	{
		long deleteCount = service.deleteQuestionById(questionId);
		return ResponseEntity.ok(deleteCount);
	}
	
	@DeleteMapping
	public ResponseEntity<Long> deleteAllQuesitonOfQuestionBank(@PathVariable("questionBankId") String questionBankId)
	{
		long deleteCount = service.deleteAllQuestionsOfQuestionBank(questionBankId);
		return ResponseEntity.ok(deleteCount);
	}
}
