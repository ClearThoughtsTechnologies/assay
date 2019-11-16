package com.clearthoughts.clearcampus.assay.api.resources;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.model.TestPaper;
import com.clearthoughts.clearcampus.assay.model.TestPaper.*;

import lombok.Data;

@Data
public class TestPaperResource extends RepresentationModel<TestPaperResource>{
	private String _id;
	private String companyId;
	private String name;
	private String description;
	private Attempt attempt;
	private Appearance appearance;
	private Evaluation evaluation;
	private ResultDeclare resultDeclare;
	private boolean publicTestPaper;
	private List<Question> questions;

	public TestPaperResource() {

	}

	public TestPaperResource(String _id, String companyId, String name, String description, Attempt attempt,
			Appearance appearance, Evaluation evaluation, ResultDeclare resultDeclare,
			boolean publicTestPaper, List<Question> questions) {
		super();
		this._id = _id;
		this.companyId = companyId;
		this.name = name;
		this.description = description;
		this.attempt = attempt;
		this.appearance = appearance;
		this.evaluation = evaluation;
		this.resultDeclare = resultDeclare;
		
		this.publicTestPaper = publicTestPaper;
		this.questions = questions;
	}

	public TestPaperResource(TestPaper tp) {
		this(tp.get_id(), tp.getCompanyId(), tp.getName(), tp.getDescription(), tp.getAttempt(), tp.getAppearance(),
				tp.getEvaluation(), tp.getResultDeclare(),  tp.isPublicTestPaper(),
				tp.getQuestions());
	}

}
