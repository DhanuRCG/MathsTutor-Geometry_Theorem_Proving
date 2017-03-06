package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.engine.CheckingEngine;
import com.geometry.GeoRelation;
import com.graph.Graph;
import com.graph.GraphNode;
import com.knowledge.HashTestKnowledge;
import com.knowledge.StepStatus;

public class AlternativeCeckingEng {

	public static void main(String args[]) {

		// Load Student Answer
		 String fileName = "src/main/java/com/io/2010-Answer-03.txt";
		//String fileName = "src/main/java/com/io/2013-Answer-1.txt";

		CheckingEngine checkingEngine = new CheckingEngine();
		ArrayList<Integer> subquestionpotions = checkingEngine.loadStudentAnswer(fileName);

		// Load Problem
		String paperId = "ProblemFormat - 2010 - I2G.xml";
		//String paperId = "ProblemFormat-2013-I2G2.xml";
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

		System.out.println("initial knowledge");
		checkingEngine.initialGivenKnowledge.printdata();
		CheckingEngine.hashTestKnowledge.generateSameItemPackege();

		try {
			FileInputStream fileIn = new FileInputStream("Maps/" + paperId + "_map.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			CheckingEngine.setHashTestKnowledge((HashTestKnowledge) in.readObject());
			in.close();
			fileIn.close();
		} catch (IOException i) {
			// i.printStackTrace();
			long startTime = System.currentTimeMillis();
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
			long endTime = System.currentTimeMillis();
			System.out.println("time taken :" + (endTime - startTime) / 1000);

			CheckingEngine.hashTestKnowledge.generateSameItemPackege();

		} catch (ClassNotFoundException c) {
			System.out.println("Graph  not found");
			// c.printStackTrace();
			long startTime = System.currentTimeMillis();
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
			long endTime = System.currentTimeMillis();
			System.out.println("time taken :" + (endTime - startTime) / 1000);
			CheckingEngine.hashTestKnowledge.generateSameItemPackege();

		}

		try {
			FileOutputStream fileOut = new FileOutputStream("Maps/" + paperId + "_map.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(CheckingEngine.hashTestKnowledge);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved");
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
			// i.printStackTrace();

			long startTime = System.currentTimeMillis();
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
			long endTime = System.currentTimeMillis();
			System.out.println("time taken :" + (endTime - startTime) / 1000);

		} catch (ClassNotFoundException c) {
			System.out.println("Graph  not found");
			// c.printStackTrace();
			long startTime = System.currentTimeMillis();
			CheckingEngine.graph.addInitialKnowledge(checkingEngine.initialGivenKnowledge);
			checkingEngine.buildGraph(rules);
			long endTime = System.currentTimeMillis();
			System.out.println("time taken :" + (endTime - startTime) / 1000);

		}

		try {
			FileOutputStream fileOut = new FileOutputStream("Graphs/" + paperId + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(CheckingEngine.graph);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

		// checkingEngine.graph.removeSameItemParents(checkingEngine.hashTestKnowledge);

		CheckingEngine.graph.printGraph();

		System.out.println("Graph size " + CheckingEngine.graph.getGraphNodes().size());

		for (Iterator<?> iterator = CheckingEngine.answerHolder.getAnswers().iterator(); iterator.hasNext();) {
			GeoRelation answer = (GeoRelation) iterator.next();
			System.out.print(answer.getName() + " ");
			System.out.println(CheckingEngine.graph.relationExists(answer));

			System.out.println("position :" + CheckingEngine.answerHolder.currentPosition);

			if (checkingEngine.isSameItemEqualsRelation(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.CORRECT);
				CheckingEngine.answerHolder.currentPosition++;
				continue;

			}

			/*
			 * if(checkingEngine.answerHolder.currentPosition == 11){
			 * System.out.println(answer.getName());
			 * System.out.println(answer.getReason());
			 * //System.out.println("name" +
			 * checkingEngine.graph.findNode(answer).getPredesisorNames());
			 * System.out.println(checkingEngine.graph.findNode(answer).
			 * getPredesisors()); System.out.println("is Root" +
			 * checkingEngine.graph.findNode(answer).isRootFact()); }
			 */

			if (!CheckingEngine.graph.relationExists(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.INCORRECT);
				System.out.println("INCORRECT");
				System.out.println();
				CheckingEngine.answerHolder.currentPosition++;
				continue;
			}

			if (checkingEngine.isParentsExistsInAnswerAndValid(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.CORRECT);
				System.out.println("CORRECT");
				System.out.println();
				CheckingEngine.studentsMarkedStepHolder.insertGeoRelation(answer);
				CheckingEngine.answerHolder.currentPosition++;
				continue;

			}
			if (!checkingEngine.isParentsExistsInAnswerAndValid(answer)) {
				CheckingEngine.answerHolder.getStatus().add(StepStatus.CORRECT_BUT_PREVIOUS_STEP_MISSING);
				System.out.println("CORRECT_BUT_PREVIOUS_STEP_MISSING");
				System.out.println();
				CheckingEngine.studentsMarkedStepHolder.insertGeoRelation(answer);
				CheckingEngine.answerHolder.currentPosition++;
				continue;

			}

			if (CheckingEngine.graph.relationExists(answer)
					&& CheckingEngine.graph.findNode(answer).getPredesisors() != null) {

				System.out.println(CheckingEngine.graph.findNode(answer).getReasons());

				for (Iterator<?> iterator2 = CheckingEngine.graph.findNode(answer).getPredesisors()
						.iterator(); iterator2.hasNext();) {
					GraphNode node = (GraphNode) iterator2.next();

					System.out.println(node.getName());
					System.out.println(CheckingEngine.graph.relationExists(node.getRelation()));

				}

			}

			System.out.println();
			CheckingEngine.answerHolder.currentPosition++;
		}

		System.out.println("---------------------------");
		CheckingEngine.hashTestKnowledge.printKnowledge();

		CheckingEngine.answerHolder.printMarkedAnswer();
	}

}
