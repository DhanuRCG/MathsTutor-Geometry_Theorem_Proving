package com.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.sentensesimilarity.core.SentensePair;

import com.geometry.GeoRelation;
import com.geometry.GeoType;
import com.knowledge.StepStatus;
import com.knowledge.StudentAnswerHolder;
import com.markingSchemeParser.MarkBlockXML;
import com.markingSchemeParser.MarkSetXML;
import com.markingSchemeParser.StepXML;
import com.markingSchemeParser.SubQuestionXML;

public class GradeAnswer {

	public int grade(StudentAnswerHolder answerHolder, SubQuestionXML subQuestionXML) {
		int totalMarks = subQuestionXML.getTotalMarks();
		double studentMarks = 0;
		
		List<MarkSetXML> markSetXMLList = subQuestionXML.getMarkSetXML();

		// Iterate through marksets and assign highest marks for an answer
		for (Iterator iterator1 = markSetXMLList.iterator(); iterator1.hasNext();) {
			
			//All answers and status should be added each time when marking new markset as those are 
			//removed when marking a block
			ArrayList<GeoRelation> answers = new ArrayList<GeoRelation>();
			answers.addAll(answerHolder.getAnswers());
			ArrayList<StepStatus> status = new ArrayList<StepStatus>();
			status.addAll(answerHolder.getStatus());
			StudentAnswerHolder tempAnswerHolder = new StudentAnswerHolder(answers,status);
			
			MarkSetXML markSetXML = (MarkSetXML) iterator1.next();
			List<MarkBlockXML> markBlockList;
			double marksForAMarkSet = 0;

			markBlockList = markSetXML.getMarkBlockXML();

			if (markBlockList == null) {
				marksForAMarkSet = 0;
			}
			
			for (Iterator iterator = markBlockList.iterator(); iterator.hasNext();) {
				MarkBlockXML markBlockXML = (MarkBlockXML) iterator.next();
				marksForAMarkSet += giveMarkForBlock(markBlockXML, tempAnswerHolder);
			}
			
			if (marksForAMarkSet > studentMarks)
				studentMarks = marksForAMarkSet;
			
			int studentMark = Integer.parseInt(((studentMarks+"").split("\\."))[0]);
			if(studentMark == totalMarks){
				break;
			}
		}
		int studentMark = Integer.parseInt(((studentMarks+"").split("\\."))[0]);
		System.out.println("Sub question " + subQuestionXML.getId() + " Marks : " + studentMark + "/" + totalMarks);
		return studentMark;
	}

	public double giveMarkForBlock(MarkBlockXML markBlockXML, StudentAnswerHolder studentAnswerHolder) {
		double marksForBlock;
		GeoRelation markBlockGeoRelation;

		marksForBlock = 0;
		for (Iterator iterator = markBlockXML.getStepXML().iterator(); iterator.hasNext();) {
			StepXML step = (StepXML) iterator.next();
			int studentStepPosition = -1;
			markBlockGeoRelation = step.getExpressionXML().getGeoRelation();

			//System.out.println(markBlockGeoRelation.getName());
			// Take similar expressions for markBlockGeoRelation
			ArrayList<GeoRelation> relations = null;
			if (markBlockGeoRelation.firstItem.type == GeoType.ANGLE) {
				CheckingEngine.hashTestKnowledge.generateSameItemPackege();
				relations = CheckingEngine.hashTestKnowledge.getSameRelation(markBlockGeoRelation);
			}
			studentStepPosition = studentAnswerHolder.getGeoRelationPosition(markBlockGeoRelation);

			int count = 0;
			while (studentStepPosition == -1 && relations != null) {
				if (count == relations.size())
					break;
				studentStepPosition = studentAnswerHolder.getGeoRelationPosition(relations.get(count++));
			}
			
			
			List<Object> reasons = new ArrayList<Object>();
			if (relations != null)
				for (GeoRelation r : relations) {
					reasons.addAll(CheckingEngine.reasonHolder.getReasonCollection(r));
				}

			if(!step.getReasonXML().getPhrase().equals(""))
				reasons.add(step.getReasonXML().getPhrase());
			
			if (studentStepPosition != -1) {
				
				if(studentAnswerHolder.getStatus()
						.get(studentStepPosition) == StepStatus.CORRECT_BUT_PREVIOUS_STEP_MISSING 
						&& !markBlockXML.isAllowMissingsteps()){
					//No marks Given
					//As necessary steps are missing
					//System.out.println("Marks Not Given: "+step.getExpressionXML().getMark()+ " : "+markBlockGeoRelation.getName() );
				}	
				else if (studentAnswerHolder.getStatus().get(studentStepPosition) == StepStatus.CORRECT
						|| studentAnswerHolder.getStatus()
								.get(studentStepPosition) == StepStatus.CORRECT_BUT_PREVIOUS_STEP_MISSING) {
					 
					//System.out.println("Student : " +studentAnswerHolder.getAnswers().get(studentStepPosition).getReason());
					if(!step.getReasonRequired()){
						marksForBlock += step.getExpressionXML().getMark();
						//System.out.println("Marks Given Reason not required: "+step.getExpressionXML().getMark()+ " : "+markBlockGeoRelation.getName());
					}
					else if(step.getReasonXML().getRequired()){
						if(isReasonCorrect(reasons, studentAnswerHolder.getAnswers().get(studentStepPosition).getReason())){
							marksForBlock += step.getExpressionXML().getMark();
							//System.out.println("Marks Given Reason required: "+step.getExpressionXML().getMark()+ " : "+markBlockGeoRelation.getName());
						}
					}
					else{
						marksForBlock += step.getExpressionXML().getMark();
						//System.out.println("Marks Given :"+step.getExpressionXML().getMark()+ " : "+markBlockGeoRelation.getName());
					}	
				}
				
				studentAnswerHolder.getAnswers().remove(studentStepPosition);
				studentAnswerHolder.getStatus().remove(studentStepPosition);
			}

		}
		
		if(markBlockXML.getMinMark() == null){//If a minimum mark is not assigned
			//Do nothing
		}else if(marksForBlock < Double.parseDouble(markBlockXML.getMinMark()))
		{
			marksForBlock = 0;
		}
		
		if (markBlockXML.getMaxMark().equals("total"))
			return marksForBlock;
		else {
			if (marksForBlock > markBlockXML.getMaxmarks())
				return markBlockXML.getMaxmarks();
			else
				return marksForBlock;
		}
	}
	
	public boolean isReasonCorrect(List<Object> reasons,String studentReason){
		SentensePair sentensePair = new SentensePair();
		sentensePair.setSentense01(studentReason);
		double value = 0;
		double temp = 0;
		
		for(Object reason:reasons){
			//System.out.println("Generated: "+(String)reason);
			sentensePair.setSentense02((String)reason);
			sentensePair.evaluateSentenses(true, 0.89, true, true);
			temp = Double.parseDouble(sentensePair.getSimVal());
			if( temp > value)
				value = temp;
			
		}
		//System.out.print(studentReason +" : ");
		//System.out.println("Value : "+value);
		if(value > 0.5)
			return true;
		
		return false;
	}
}
