package com.markingSchemeParser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class QuestionXML {
	private List<SubQuestionXML> subQuestion;
	
	private String totalMarks;
	
	private String id;
	
	public QuestionXML() {
		super();
		subQuestion=new ArrayList<SubQuestionXML>();
	}

	@XmlAttribute 
	public String getTotalMarks() {
		return totalMarks;
	}
	
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	@XmlAttribute 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name="subQuestion")  
	public List<SubQuestionXML> getSubQuestionXML() {
		return subQuestion;
	}

	public void setSubQuestionXML(List<SubQuestionXML> subquestion) {
		this.subQuestion = subquestion;
	}
	
}
