package com.markingSchemeParser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.markingSchemeParser.MarkingSchemeParser;
import com.markingSchemeParser.MarkingScheme;

public class MarkingSchemeParser {
	public static void main(String args[]) {
		
		MarkingSchemeParser markingSchema = new MarkingSchemeParser();
		String path="src/main/java/com/markingSchemeParser/MarkingScheme_2010_naduni.xml";
		MarkingScheme markingScheme = markingSchema.getMarkingScheme(path);
		
		//Testing for all the tags available in marking scheme
		System.out.println(markingScheme.getType());
		System.out.println(markingScheme.getSectionid());
		System.out.println(markingScheme.getQuestionXML().get(0).getId());
		System.out.println(markingScheme.getQuestionXML().get(0).getTotalMarks());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getId());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getIsProof());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getTotalMarks());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getId());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getMaxMark());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getMaxmarks());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).isAllowMissingsteps());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).isOrderRequired());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getMark());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getReasonRequired());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getExpressionXML().getMark());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getExpressionXML().getRequired());
		System.out.println("Contain_Complex_Operators : "+markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(2).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getExpressionXML().isContain_complex_operators());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getReasonXML().getMark());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getReasonXML().getPhrase());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getReasonXML().getRequired());
		System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getExpressionXML().getGeoRelation().getName());
		//System.out.println(markingScheme.getQuestionXML().get(0).getSubQuestionXML().get(1).getMarkSetXML().get(0).getMarkBlockXML().get(0).getStepXML().get(0).getReasonXML().getGeoRelation().getName());	
		
	}

	
	public MarkingScheme getMarkingScheme(String path){
		MarkingScheme markingScheme = null;
		File file = new File(path);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(MarkingScheme.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			markingScheme = (MarkingScheme) jaxbUnmarshaller.unmarshal(file);
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return markingScheme;
	}
}
