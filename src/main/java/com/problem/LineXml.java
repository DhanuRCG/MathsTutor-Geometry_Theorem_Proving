package com.problem;

import javax.xml.bind.annotation.XmlAttribute;
import com.geometry.*;

public class LineXml {
	private String id;

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Line getLine() {
		return new Line(id);
	}
}
