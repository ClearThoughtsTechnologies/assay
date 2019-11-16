package com.clearthoughts.clearcampus.assay.api.resources;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.clearthoughts.clearcampus.assay.api.QuestionAPI;
import com.clearthoughts.clearcampus.assay.model.Question;

@Component
public class QuestionModelAssembler extends RepresentationModelAssemblerSupport<Question, QuestionResource> {

	

	public QuestionModelAssembler() {
		super(QuestionAPI.class, QuestionResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public QuestionResource toModel(Question question) {
		QuestionResource qr = new QuestionResource(question);
		Link selfRel = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuestionAPI.class).getQuestionById(question.get_id())).withSelfRel();
		selfRel = selfRel.expand(question.getQuestionBankId());
		qr.add(selfRel);
		return qr;
	}

	

}
