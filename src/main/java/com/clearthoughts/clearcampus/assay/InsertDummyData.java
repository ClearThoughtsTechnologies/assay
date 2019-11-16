package com.clearthoughts.clearcampus.assay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.model.QuestionBank;
import com.clearthoughts.clearcampus.assay.model.QuestionType;
import com.clearthoughts.clearcampus.assay.model.TestPaper;
import com.clearthoughts.clearcampus.assay.model.TestPaper.Appearance;
import com.clearthoughts.clearcampus.assay.model.TestPaper.Attempt;
import com.clearthoughts.clearcampus.assay.model.TestPaper.Evaluation;
import com.clearthoughts.clearcampus.assay.model.TestPaper.QuestionSequence;
import com.clearthoughts.clearcampus.assay.model.TestPaper.ResultAppearance;
import com.clearthoughts.clearcampus.assay.model.TestPaper.ResultDeclare;
import com.clearthoughts.clearcampus.assay.model.TestPaper.ResultFormat;
import com.clearthoughts.clearcampus.assay.service.QuestionBankService;
import com.clearthoughts.clearcampus.assay.service.QuestionService;
import com.clearthoughts.clearcampus.assay.service.TestPaperService;

@Component
public class InsertDummyData implements ApplicationListener<ContextRefreshedEvent> {
	private static String[] questionIds = new String[] { "questionOne", "questionTwo", "questionThree" };
	private static String[] questionTexts = new String[] { "What is capital of India?",
			"Who is prime minister of India?", "Who is president of India?" };
	private static List[] optionTexts = new List[] { Arrays.asList("Delhi", "Mumbai", "Kolkata", "Hydrabad"),
			Arrays.asList("Rahul Gandhi", "Arvind Kejriwal", "Advani", "Modi"),
			Arrays.asList("Sastri", "Kovind", "Gandhi", "Kejriwal") };
	private static String[] AnswerTexts = new String[] { "Delhi", "Modi", "Kovind" };
	private static String[] questionBankIds = new String[] { "questionBankOne", "questionBankTwo",
			"questionBankThree" };
	private static String[] testPaperIds = new String[] { "tpOne", "tpTwo", "tpThree" };

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionBankService qestionBankService;

	@Autowired
	private TestPaperService testPaperService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		insertData();
	}

	private void insertData() {
		try {
			List<Question> questions = questionService.findAllQuestionOfQuestionBank(questionBankIds[0]);
			if (questions.size() > 0) {
				return;
			}
		} catch (Exception e) {

		}
		boolean alter = true;
		List<Question> questionList = new ArrayList<Question>();
		for (int i = 0; i < questionIds.length; i++) {
			Question q = new Question(questionIds[0], questionBankIds[0],
					alter ? QuestionType.MULTIPLE_CHOICE : QuestionType.TRUE_FALSE, questionTexts[i], optionTexts[i],
					AnswerTexts[i]);
			try {
				questionList.add(questionService.createNewQuestion(q));
			} catch (Exception e) {
				e.printStackTrace();
			}
			alter = !alter;
		}

		for (int i = 0; i < questionBankIds.length; i++) {
			try {
				QuestionBank qb = new QuestionBank(questionBankIds[i], "clearthouhts", questionBankIds[i],
						questionBankIds[i] + " description");
				qestionBankService.createQuestionBank(qb);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < testPaperIds.length; i++) {
			try {
			TestPaper tp = new TestPaper(testPaperIds[i], "clearthouhts", testPaperIds[i],
					testPaperIds[i] + " description", new Attempt(2, 30),
					new Appearance(QuestionSequence.SEQUENCE, false, true), new Evaluation(10, 100, 60, 0),
					new ResultDeclare(ResultAppearance.AFTER_EXAM, ResultFormat.PERCENTAGE), false, questionList);
			testPaperService.createTestPaper(tp);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	

}
