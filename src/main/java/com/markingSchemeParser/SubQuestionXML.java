package com.markingSchemeParser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class SubQuestionXML {
	private int id;
	private int totalMarks;
	private boolean isProof;
	private List<MarkSetXML> markSet;
	
	SubQuestionXML(){
		markSet = new ArrayList<MarkSetXML>();
	}
	
	@XmlAttribute 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlAttribute 
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	@XmlAttribute
	public boolean getIsProof() {
		return isProof;
	}
	public void setIsProof(boolean isProof) {
		this.isProof = isProof;
	}
	
	@XmlElement(name="markSet")
	public List<MarkSetXML> getMarkSetXML() {
		return markSet;
	}
	public void setMarkSetXML(List<MarkSetXML> markset) {
		this.markSet = markset;
	}
	
}
