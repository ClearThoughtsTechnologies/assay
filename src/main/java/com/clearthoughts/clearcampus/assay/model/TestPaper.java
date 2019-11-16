package com.clearthoughts.clearcampus.assay.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

@Document
public class TestPaper {
	
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
	
	public TestPaper()
	{
		this.questions = new ArrayList<>();
	}
	
	public TestPaper(String _id, String companyId, String name, String description, Attempt attempt,
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

	public void addQuestion(Question q)
	{
		this.questions.add(q);
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Attempt getAttempt() {
		return attempt;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public ResultDeclare getResultDeclare() {
		return resultDeclare;
	}

	public void setResultDeclare(ResultDeclare resultDeclare) {
		this.resultDeclare = resultDeclare;
	}

	public boolean isPublicTestPaper() {
		return publicTestPaper;
	}

	public void setPublicTestPaper(boolean publicTestPaper) {
		this.publicTestPaper = publicTestPaper;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	/**
	 * This class will classify the re-attempt properties of test paper
	 *
	 */
	public static class Attempt {
		int attemptTimesAllowed;
		int delayAfterEachAttempt;

		public Attempt(int attemptTimesAllowed, int delayAfterEachAttempt) {
			this.attemptTimesAllowed = attemptTimesAllowed;
			this.delayAfterEachAttempt = delayAfterEachAttempt;
		}

		public int getAttemptTimesAllowed() {
			return attemptTimesAllowed;
		}

		public void setAttemptTimesAllowed(int attemptTimesAllowed) {
			this.attemptTimesAllowed = attemptTimesAllowed;
		}

		public int getDelayAfterEachAttempt() {
			return delayAfterEachAttempt;
		}

		public void setDelayAfterEachAttempt(int delayAfterEachAttempt) {
			this.delayAfterEachAttempt = delayAfterEachAttempt;
		}

	}

	public static class Appearance {
		QuestionSequence questionSequence;
		boolean displayAllQuestion;
		boolean showPreviousAllowed;
		
		public Appearance()
		{
			
		}
		
		public Appearance(QuestionSequence questionSequence, boolean displayAllQuestion, boolean showPreviousAllowed) {
			super();
			this.questionSequence = questionSequence;
			this.displayAllQuestion = displayAllQuestion;
			this.showPreviousAllowed = showPreviousAllowed;
		}



		public QuestionSequence getQuestionSequence() {
			return questionSequence;
		}

		public void setQuestionSequence(QuestionSequence questionSequence) {
			this.questionSequence = questionSequence;
		}

		public boolean isDisplayAllQuestion() {
			return displayAllQuestion;
		}

		public void setDisplayAllQuestion(boolean displayAllQuestion) {
			this.displayAllQuestion = displayAllQuestion;
		}

		public boolean isShowPreviousAllowed() {
			return showPreviousAllowed;
		}

		public void setShowPreviousAllowed(boolean showPreviousAllowed) {
			this.showPreviousAllowed = showPreviousAllowed;
		}

	}

	public static enum QuestionSequence {
		RANDOM("random"), SEQUENCE("sequence");
		String itsString;

		private QuestionSequence(String str) {
			this.itsString = str;
		}

		@Override
		public String toString() {
			return this.itsString;
		}

		public String getText()
		{
			return this.itsString;
		}
		
		@JsonCreator
		public static QuestionSequence fromText(String text)
		{
			for(QuestionSequence qs : QuestionSequence.values())
			{
				if(qs.getText().equalsIgnoreCase(text))
				{
					return qs;
				}
			}
			throw new IllegalArgumentException("Provided value is not valid");
		}
	}

	public static class Evaluation {
		int totalQuestion;
		int totalMarks;
		int passMarks;
		int negativeMarksPerQuestion;

		public Evaluation() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		public Evaluation(int totalQuestion, int totalMarks, int passMarks, int negativeMarksPerQuestion) {
			super();
			this.totalQuestion = totalQuestion;
			this.totalMarks = totalMarks;
			this.passMarks = passMarks;
			this.negativeMarksPerQuestion = negativeMarksPerQuestion;
		}



		public int getTotalQuestion() {
			return totalQuestion;
		}

		public void setTotalQuestion(int totalQuestion) {
			this.totalQuestion = totalQuestion;
		}

		public int getTotalMarks() {
			return totalMarks;
		}

		public void setTotalMarks(int totalMarks) {
			this.totalMarks = totalMarks;
		}

		public int getPassMarks() {
			return passMarks;
		}

		public void setPassMarks(int passMarks) {
			this.passMarks = passMarks;
		}

		public int getNegativeMarksPerQuestion() {
			return negativeMarksPerQuestion;
		}

		public void setNegativeMarksPerQuestion(int negativeMarksPerQuestion) {
			this.negativeMarksPerQuestion = negativeMarksPerQuestion;
		}

	}
	
	public static class ResultDeclare {
		ResultAppearance resultAppearance;
		ResultFormat resultFormat;
		
		
		
		public ResultDeclare() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ResultDeclare(ResultAppearance resultAppearance, ResultFormat resultFormat) {
			super();
			this.resultAppearance = resultAppearance;
			this.resultFormat = resultFormat;
		}



		public ResultAppearance getResultAppearance() {
			return resultAppearance;
		}
		
		public void setResultAppearance(ResultAppearance resultAppearance) {
			this.resultAppearance = resultAppearance;
		}
		public ResultFormat getResultFormat() {
			return resultFormat;
		}
		public void setResultFormat(ResultFormat resultFormat) {
			this.resultFormat = resultFormat;
		}
		
	}
	
	public static enum ResultAppearance {
		AFTER_QUESTION("AfterQuestion"), AFTER_EXAM("AfterExam"), EMAIL("Email"), NO_SHOW("NoShow");
		
		private String itsString;
		
		private ResultAppearance(String itsString)
		{
			this.itsString = itsString;
		}
		
		@Override
		public String toString() {
			return this.itsString;
		}
		
		public String getText()
		{
			return this.itsString;
		}
		@JsonCreator
		public static ResultAppearance fromText(String text)
		{
			for(ResultAppearance ra : ResultAppearance.values())
			{
				if(ra.getText().equalsIgnoreCase(text))
				{
					return ra;
				}
			}
			throw new IllegalArgumentException("Provided value is not valid");
		}
	}
	
	public static enum ResultFormat {
		PASS_FAIL("PASS_FAIL"), RANK("RANK"), PERCENTAGE("PERCENTAGE"), PERCENTILE("PERCENTILE");
		private String itsString;
		
		private ResultFormat(String itsString)
		{
			this.itsString = itsString;
		}
		
		@Override
		public String toString() {
			return this.itsString;
		}
		
		public String getText()
		{
			return this.itsString;
		}
		
		@JsonCreator
		public static ResultFormat fromText(String text)
		{
			for(ResultFormat rf : ResultFormat.values())
			{
				if(rf.getText().equalsIgnoreCase(text))
				{
					return rf;
				}
			}
			throw new IllegalArgumentException("Provided value is not valid");
		}
		
	}

}
