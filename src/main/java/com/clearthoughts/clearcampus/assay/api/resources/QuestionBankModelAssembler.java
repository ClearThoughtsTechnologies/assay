package com.clearthoughts.clearcampus.assay.api.resources;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.clearthoughts.clearcampus.assay.api.QuestionBankAPI;
import com.clearthoughts.clearcampus.assay.model.QuestionBank;

@Component
public class QuestionBankModelAssembler extends RepresentationModelAssemblerSupport<QuestionBank, QuestionBankResource>{

	public QuestionBankModelAssembler() {
		super(QuestionBankAPI.class, QuestionBankResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public QuestionBankResource toModel(QuestionBank qb) {
		QuestionBankResource qbr = new QuestionBankResource(qb);
		qbr.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuestionBankAPI.class).getQuestionBankById(qb.get_id())).withSelfRel()
				);
		return qbr;
	}

}
