package com.markingSchemeParser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class MarkBlockXML {
	private List<StepXML> step;
	private boolean orderRequired;
	private boolean allowMissingsteps;
	private String maxMark;
	private String minMark;//When marks given for two or more steps as a whole
						//marks have been divided among them equally(can be decimal)
						//minimum mark has defined so as to not give decimal marks
						//If student's mark is not minimum, will assign zero as mark for that 
	
	public MarkBlockXML() {
		// TODO Auto-generated constructor stub
		step = new ArrayList<StepXML>();
	}

	@XmlElement(name = "step")
	public List<StepXML> getStepXML() {
		return step;
	}

	public void setStepXML(List<StepXML> step) {
		this.step = step;
	}

	@XmlAttribute
	public boolean isOrderRequired() {
		return orderRequired;
	}

	public void setOrderRequired(boolean order) {
		this.orderRequired = order;
	}

	@XmlAttribute
	public boolean isAllowMissingsteps() {
		return allowMissingsteps;
	}

	public void setAllowMissingsteps(boolean allowMissingsteps) {
		this.allowMissingsteps = allowMissingsteps;
	}

	@XmlAttribute
	public String getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(String maxMark) {
		this.maxMark = maxMark;
	}

	@XmlAttribute
	public String getMinMark() {
		return minMark;
	}

	public void setMinMark(String minMark) {
		this.minMark = minMark;
	}
	
	public int getMaxmarks() {

		if (maxMark.equalsIgnoreCase("total"))

			return -1;

		else {
			try {

				return Integer.parseInt(maxMark);

			} catch (Exception e) {
				return -1;
			}
		}
	}
}
