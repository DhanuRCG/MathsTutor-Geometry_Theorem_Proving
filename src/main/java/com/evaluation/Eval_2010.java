package com.evaluation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.engine.CheckingEngine;
import com.engine.GradeAnswer;
import com.geometry.GeoRelation;
import com.graph.Graph;
import com.knowledge.HashTestKnowledge;
import com.knowledge.StepStatus;
import com.knowledge.StudentAnswerHolder;
import com.markingSchemeParser.MarkingSchemeParser;
import com.markingSchemeParser.SubQuestionXML;

public class Eval_2010 {

	public static void main(String args[]) {

		long startTime = System.currentTimeMillis();
		
		// Load Student Answer
		String fileName = "src/main/java/com/answers/2010-Answer-15" + ".txt";
		CheckingEngine checkingEngine = new CheckingEngine();
		ArrayList<Integer> subquestionpotions = checkingEngine.loadStudentAnswer(fileName);

		// Load Problem
		String paperId = "ProblemFormat - 2010 - I2G.xml";
		String path = "src/main/java/com/problem/";
		fileName = path + paperId;
		checkingEngine.loadProblem(fileName);

		// Add rules
		ArrayList<String[]> rules = new ArrayList<>();
		rules.add(new String[] { "Theorem02.drl" });
		rules.add(new String[] { "Theorem03_1.drl" });
		rules.add(new String[] { "Theorem03_2.drl" });
		rules.add(new String[] { "Theorem03_3.drl" });
		rules.add(new String[] { "Theorem06.drl" });
		rules.add(new String[] { "Theorem12.drl" });
		rules.add(new String[] { "Theorem13.drl" });
		rules.add(new String[] { "Theorem21.drl" });

		CheckingEngine.hashTestKnowledge.generateSameItemPackege();

		try {
			FileInputStream fileIn = new FileInputStream("Maps/" + paperId + "_map.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			CheckingEngine.setHashTestKnowledge((HashTestKnowledge) in.readObject());
			in.close();
			fileIn.close();
		} catch (IOException i) {
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
			CheckingEngine.hashTestKnowledge.generateSameItemPackege();

		} catch (ClassNotFoundException c) {
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
			CheckingEngine.hashTestKnowledge.generateSameItemPackege();
		}

		try {
			FileOutputStream fileOut = new FileOutputStream("Maps/" + paperId + "_map.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(CheckingEngine.hashTestKnowledge);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			// i.printStackTrace();
		}

		try {
			FileInputStream fileIn = new FileInputStream("Graphs/" + paperId + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			CheckingEngine.setGraph((Graph) in.readObject());
			in.close();
			fileIn.close();
		} catch (IOException i) {
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
		} catch (ClassNotFoundException c) {
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
		}

		try {
			FileOutputStream fileOut = new FileOutputStream("Graphs/" + paperId + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(CheckingEngine.graph);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

		//CheckingEngine.graph.printGraph();

		for (Iterator<?> iterator = CheckingEngine.answerHolder.getAnswers().iterator(); iterator.hasNext();) {
			GeoRelation answer = (GeoRelation) iterator.next();
			if (checkingEngine.isSameItemEqualsRelation(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.CORRECT);
				CheckingEngine.answerHolder.currentPosition++;
				continue;

			}
			if (!CheckingEngine.graph.relationExists(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.INCORRECT);
				CheckingEngine.answerHolder.currentPosition++;
				continue;
			}

			if (checkingEngine.isParentsExistsInAnswerAndValid(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.CORRECT);
				CheckingEngine.studentsMarkedStepHolder.insertGeoRelation(answer);
				CheckingEngine.answerHolder.currentPosition++;
				continue;

			}
			if (!checkingEngine.isParentsExistsInAnswerAndValid(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.CORRECT_BUT_PREVIOUS_STEP_MISSING);
				CheckingEngine.studentsMarkedStepHolder.insertGeoRelation(answer);
				CheckingEngine.answerHolder.currentPosition++;
				continue;

			}

			CheckingEngine.answerHolder.currentPosition++;
		}

		CheckingEngine.answerHolder.printMarkedAnswer();

		ArrayList<GeoRelation> answers = new ArrayList<GeoRelation>();
		ArrayList<StepStatus> status = new ArrayList<StepStatus>();
		int studentTotalMarks = 0;
		
		// Sub question 1
		int i = 0;
		for (; i < subquestionpotions.get(1); i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub1 = new StudentAnswerHolder(answers, status);

		MarkingSchemeParser markingSchemeParser = new MarkingSchemeParser();
		SubQuestionXML subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010.xml").getQuestionXML()
				.get(0).getSubQuestionXML().get(0);
		GradeAnswer answerGrader = new GradeAnswer();
		studentTotalMarks += answerGrader.grade(sub1, subQuestionXML);

		answers.clear();
		status.clear();

		// Sub question 02
		for (; i < subquestionpotions.get(2); i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub2 = new StudentAnswerHolder(answers, status);
		subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010.xml").getQuestionXML()
				.get(0).getSubQuestionXML().get(1);
		studentTotalMarks += answerGrader.grade(sub2, subQuestionXML);

		answers.clear();
		status.clear();

		// Sub question 03
		for (; i < subquestionpotions.get(3); i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub3 = new StudentAnswerHolder(answers, status);
		subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010.xml").getQuestionXML()
				.get(0).getSubQuestionXML().get(2);
		studentTotalMarks += answerGrader.grade(sub3, subQuestionXML);
		answers.clear();
		status.clear();

		// Sub question 04
		for (; i < CheckingEngine.answerHolder.getSize(); i++) {
			answers.add(CheckingEngine.answerHolder.getAnswers().get(i));
			status.add(CheckingEngine.answerHolder.getStatus().get(i));
		}

		StudentAnswerHolder sub4 = new StudentAnswerHolder(answers, status);
		subQuestionXML = markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010.xml").getQuestionXML()
				.get(0).getSubQuestionXML().get(3);
		studentTotalMarks += answerGrader.grade(sub4, subQuestionXML);
		answers.clear();
		status.clear();
		
		int totalMarks = Integer.parseInt(markingSchemeParser
				.getMarkingScheme("src/main/java/com/markingSchemeParser/MarkingScheme_2010.xml").getQuestionXML()
				.get(0).getTotalMarks());
		System.out.println("Total Marks : "+studentTotalMarks+"/"+totalMarks);
		long endTime = System.currentTimeMillis();
		System.out.println("time taken :" + (endTime - startTime));
	}

}
