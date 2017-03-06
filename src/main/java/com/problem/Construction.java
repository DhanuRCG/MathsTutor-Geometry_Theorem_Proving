package com.problem;

import javax.xml.bind.annotation.XmlElement;

public class Construction {
	private Constraints constraints;
	private Elements elements;

	@XmlElement
	public Constraints getConstraints() {
		return constraints;
	}

	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}

	@XmlElement
	public Elements getElements() {
		return elements;
	}

	public void setElements(Elements elements) {
		this.elements = elements;
	}
}
