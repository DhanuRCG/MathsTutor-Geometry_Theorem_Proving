package com.engine;

import java.util.ArrayList;
import java.util.Iterator;

import com.geometry.GeoRelation;
import com.knowledge.StepStatus;
import com.knowledge.StudentAnswerHolder;
import com.marking.MarkSetMarker;
import com.markingSchemeParser.MarkSetXML;
import com.markingSchemeParser.MarkingSchemeParser;

public class AnswerGrader {

	StudentAnswerHolder answerHolder;
	
	boolean lastStepFound = false;
	
	boolean continiouslyCorrect = true;

	GeoRelation finalGeorelation ;
	
	int fullmarks;
	
	public StudentAnswerHolder grade(StudentAnswerHolder answerHolder, MarkSetXML markSetXML){
		
		this.answerHolder = answerHolder;
		
		finalGeorelation = getFinalGeoRelation(markSetXML);
		
		ArrayList<GeoRelation> answers = answerHolder.getAnswers();
		
		ArrayList<StepStatus> statuses =	answerHolder.getStatus();
		for (int i = 0; i < answers.size(); i++) {
			
			GeoRelation answer = answers.get(i);
			
			StepStatus status = statuses.get(i);
			if(finalGeorelation != null && finalGeorelation.sameRelation(answer)) {
				System.out.println("last Found");
				lastStepFound = true; 
			}
			
			System.out.println("answer: " + answer.getName());
			
			if(!(status == StepStatus.CORRECT || status == StepStatus.CORRECT_BUT_PREVIOUS_STEP_MISSING )){
				System.out.println("Contain incorrect steps.");
				continiouslyCorrect = false;
			}
		}
		
		System.out.println("Full Marks for the question : " + getFullMarks(markSetXML) );
		
		if(lastStepFound && continiouslyCorrect){
			//give full marks to last answer step
			this.answerHolder.addToFinalMarks(getFullMarks(markSetXML));
			//answers.get(answers.size() - 1).setMarkforrelation(getFullMarks(markSetXML));
		}
		else{
			//give marks according to the marking schema
			MarkSetMarker markSetMarker = new MarkSetMarker();
			
			answerHolder = markSetMarker.giveMarkForMarkSet(markSetXML, answerHolder);
			this.answerHolder = answerHolder;
			System.out.println("Checked with marking scheme: "+answerHolder.getFinalMarks());
		}
		
		return this.answerHolder;
	
	}
	
	private GeoRelation getFinalGeoRelation(MarkSetXML markSetXML){
		
		try {
			
			int markBlockSize = markSetXML.getMarkBlockXML().size();
			
			int expressionSize = markSetXML.getMarkBlockXML().get(markBlockSize - 1).getStepXML().size();
			
			return markSetXML.getMarkBlockXML().get(markBlockSize - 1).getStepXML().get(expressionSize - 1).getExpressionXML().getGeoRelation();
			
		} catch (Exception e) {
			
			return null;
			
		}
		
		
	}
	
	private int getFullMarks(MarkSetXML markSetXML){
		
		int fullMarks = 0;
		
		try {
			
			int markBlockSize = markSetXML.getMarkBlockXML().size();
			
			for (int i = 0; i < markBlockSize; i++) {
				
				try {

					fullMarks += Integer.parseInt( markSetXML.getMarkBlockXML().get(i).getMaxMark() );
					
				} catch (Exception e) {
					
					if("total".compareTo(markSetXML.getMarkBlockXML().get(i).getMaxMark()) == 0){
						
						int stepSize = markSetXML.getMarkBlockXML().get(i).getStepXML().size();
						
						for (int j = 0; j < stepSize; j++) {
							
							fullMarks += markSetXML.getMarkBlockXML().get(i).getStepXML().get(j).getMark();
							
						}
					}
				}
			}
			
			return fullMarks;
			
		} catch (Exception e) {
			
			System.out.println("error" + e.getMessage());
			
			
			return 0;
			
		}
	}

}
