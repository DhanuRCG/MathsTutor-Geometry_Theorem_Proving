package com.markingSchemeParser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class MarkSetXML {
	private int id;
	private List<MarkBlockXML> markBlock; 
	
	
	MarkSetXML(){
		markBlock = new ArrayList<MarkBlockXML>();
	}
	
	@XmlAttribute 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="markBlock")  
	public List<MarkBlockXML> getMarkBlockXML() {
		return markBlock;
	}

	public void setMarkBlockXML(List<MarkBlockXML> markBlock) {
		this.markBlock = markBlock;
	}
	
}
