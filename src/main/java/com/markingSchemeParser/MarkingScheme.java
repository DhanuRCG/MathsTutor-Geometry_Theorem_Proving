package com.markingSchemeParser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MarkingScheme {

	private String type;
	private String sectionid;
	private List<QuestionXML> question;

	public MarkingScheme() {
		super();
		question = new ArrayList<QuestionXML>();
	}

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public String getSectionid() {
		return sectionid;
	}

	@XmlElement
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public List<QuestionXML> getQuestionXML() {
		return question;
	}

	@XmlElement(name = "question")
	public void setQuestionXML(List<QuestionXML> question) {
		this.question = question;
	}
}
