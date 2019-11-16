package com.clearthoughts.clearcampus.assay.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QuestionType {
	MULTIPLE_CHOICE("MULTI"), TRUE_FALSE("BOOLEAN");
	
	private String itsString;

	private QuestionType(String itsString) {
		this.itsString = itsString;
	}
	
	public String getText()
	{
		return this.itsString;
	}
	
	@JsonCreator
	public static QuestionType fromText(String text)
	{
		
		for(QuestionType qt : QuestionType.values())
		{
			if(qt.getText().equalsIgnoreCase(text))
			{
				return qt;
			}
		}
		throw new IllegalArgumentException("Provided value is not valid");
	}
	
	@Override
	public String toString() {
		return this.itsString;
	}
	
	
	
}
