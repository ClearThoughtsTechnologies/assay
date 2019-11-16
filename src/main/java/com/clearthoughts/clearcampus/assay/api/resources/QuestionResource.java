package com.clearthoughts.clearcampus.assay.api.resources;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.model.QuestionType;

import lombok.Data;

@Data
public class QuestionResource extends RepresentationModel<QuestionResource> {

	private String _id;
	private String questionBankId;
	private QuestionType questionType;
	private String questionText;
	private List<String> options;
	private String answerText;

	public QuestionResource() {

	}

	public QuestionResource(String _id, String questionBankId, QuestionType questionType, String questionText,
			List<String> options, String answerText) {
		super();
		this._id = _id;
		this.questionBankId = questionBankId;
		this.questionType = questionType;
		this.questionText = questionText;
		this.options = options;
		this.answerText = answerText;
	}

	public QuestionResource(Question q) {
		this(q.get_id(), q.getQuestionBankId(), q.getQuestionType(), q.getQuestionText(), q.getOptions(),
				q.getAnswerText());
	}

}
