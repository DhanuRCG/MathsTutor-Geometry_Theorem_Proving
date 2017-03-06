package com.problem;

import javax.xml.bind.annotation.XmlAttribute;
import com.geometry.Triangle;

public class TriangleXml {
	private String id;

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Triangle getTriangle() {
		return new Triangle(id);
	}
}
