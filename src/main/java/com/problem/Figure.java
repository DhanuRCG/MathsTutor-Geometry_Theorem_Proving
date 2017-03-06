package com.problem;

import javax.xml.bind.annotation.XmlElement;

public class Figure {
	private Construction construction;

	@XmlElement
	public Construction getConstruction() {
		return construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}
}
