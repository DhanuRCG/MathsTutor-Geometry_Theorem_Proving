package com.test;

import java.util.ArrayList;

import com.engine.CheckingEngine;
import com.engine.GradeAnswer;
import com.geometry.GeoRelation;
import com.knowledge.StepStatus;
import com.knowledge.StudentAnswerHolder;
import com.markingSchemeParser.MarkingSchemeParser;
import com.markingSchemeParser.SubQuestionXML;

public class CheckEngTest {

	public static void main(String args[]) {

		// Load Student Answer
		String fileName = "src/main/java/com/io/2010-Answer-3.txt";
		CheckingEngine checkingEngine = new CheckingEngine();
		ArrayList subquestionpotions = checkingEngine.loadStudentAnswer(fileName);
		System.out.println(subquestionpotions.size());

		// Load Problem
		fileName = "src/main/java/com/problem/ProblemFormat - 2010 - I2G.xml";
		checkingEngine.loadProblem(fileName);

		// Mark Answer Using InferenceEngine
		checkingEngine.markAnswer();

		// Split student answer to sub questions
		ArrayList<GeoRelation> answers = new ArrayList<GeoRelation>();
		ArrayList<StepStatus> status = new ArrayList<StepStatus>();
		
		// Sub question 1
		int i = 0;
		for (; i < 4; i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}
		
		StudentAnswerHolder sub1 = new StudentAnswerHolder(answers, status);
		
		MarkingSchemeParser markingSchemeParser = new MarkingSchemeParser();
		SubQuestionXML subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010_dhanushka.xml")
				.getQuestionXML().get(0).getSubQuestionXML().get(0);
		GradeAnswer answerGrader = new GradeAnswer();
		answerGrader.grade(sub1,subQuestionXML);
		
		
		answers.clear();
		status.clear();

		// Sub question 02
		for (; i < 11; i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub2 = new StudentAnswerHolder(answers, status);
		subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010_dhanushka.xml")
				.getQuestionXML().get(0).getSubQuestionXML().get(1);
		answerGrader.grade(sub2,subQuestionXML);
		
		answers.clear();
		status.clear();

		// Sub question 03
		for (; i < 14; i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub3 = new StudentAnswerHolder(answers, status);
		subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010_dhanushka.xml")
				.getQuestionXML().get(0).getSubQuestionXML().get(2);
		answerGrader.grade(sub3,subQuestionXML);
		answers.clear();
		status.clear();

		// Sub question 04
		for (; i < CheckingEngine.answerHolder.getSize(); i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub4 = new StudentAnswerHolder(answers, status);
		subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010_dhanushka.xml")
				.getQuestionXML().get(0).getSubQuestionXML().get(3);
		answerGrader.grade(sub4,subQuestionXML);
		answers.clear();
		status.clear();
		
		
	}

}
