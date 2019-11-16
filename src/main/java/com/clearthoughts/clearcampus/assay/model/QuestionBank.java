package com.clearthoughts.clearcampus.assay.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document
public class QuestionBank {
	private String _id;
	private String companyId;
	private String name;
	private String description;
	
	public QuestionBank()
	{
		
	}
	public QuestionBank(String _id, String companyId, String name, String description) {
		super();
		this._id = _id;
		this.companyId = companyId;
		this.name = name;
		this.description = description;
		
	}
	
}
