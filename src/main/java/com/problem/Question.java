package com.problem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Question {
	private String id;
	private Figure figure;
	private List<SubQuestion> subQuestion;
	
	Question(){
		subQuestion = new ArrayList<SubQuestion>();
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	@XmlElement
	public List<SubQuestion> getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(List<SubQuestion> subQuestion) {
		this.subQuestion = subQuestion;
	}
}
