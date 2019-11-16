package com.clearthoughts.clearcampus.assay.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document
public class Question {
	private String _id;
	private String questionBankId;
	private QuestionType questionType;
	private String questionText;
	private List<String> options;
	private String answerText;

	public Question() {
		options = new ArrayList<>();
	}

	public Question(String _id, String questionBankId, QuestionType questionType, String questionText,
			List<String> options, String answerText) {
		super();
		this._id = _id;
		this.questionBankId = questionBankId;
		this.questionType = questionType;
		this.questionText = questionText;
		this.options = options;
		this.answerText = answerText;
	}

	public void addOptionText(String text) {
		if (!StringUtils.isEmpty(text)) {
			this.options.add(text);
		}
	}

}
