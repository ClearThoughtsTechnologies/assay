package com.clearthoughts.clearcampus.assay.api.resources;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.clearthoughts.clearcampus.assay.model.QuestionBank;

import lombok.Data;

@Data
public class QuestionBankResource extends RepresentationModel<QuestionBankResource>{
	private String _id;
	private String companyId;
	private String name;
	private String description;
	
	public QuestionBankResource(String _id, String companyId, String name, String description) {
		super();
		this._id = _id;
		this.companyId = companyId;
		this.name = name;
		this.description = description;
	}

	public QuestionBankResource() {
		super();
	}
	
	public QuestionBankResource(QuestionBank qb)
	{
		this(qb.get_id(), qb.getCompanyId(), qb.getName(), qb.getDescription());
	}
	
	
}
