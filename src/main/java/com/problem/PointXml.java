package com.problem;

import javax.xml.bind.annotation.XmlAttribute;

import com.geometry.Point;

public class PointXml {
	private String id;

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Point getPoint() {
		char[] name = id.toCharArray();
		return new Point(name[0]);
	}
}
