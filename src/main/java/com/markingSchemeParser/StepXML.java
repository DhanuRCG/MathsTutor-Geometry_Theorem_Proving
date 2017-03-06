package com.markingSchemeParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class StepXML {
	private double mark;
	private boolean reasonRequired;
	private ExpressionXML expression;
	private ReasonXML reason;
	
	@XmlAttribute 
	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}
	
	@XmlAttribute 
	public boolean getReasonRequired() {
		return reasonRequired;
	}

	public void setReasonRequired(boolean reasonRequired) {
		this.reasonRequired = reasonRequired;
	}
	
	@XmlElement(name="expression")
	public ExpressionXML getExpressionXML() {
		return expression;
	}
	
	public void setExpressionXML(ExpressionXML expression) {
		this.expression = expression;
	}
	@XmlElement(name="reason")
	public ReasonXML getReasonXML() {
		return reason;
	}
	public void setReasonXML(ReasonXML reason) {
		this.reason = reason;
	}
}
