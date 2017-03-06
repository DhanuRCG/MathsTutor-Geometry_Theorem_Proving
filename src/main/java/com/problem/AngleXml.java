package com.problem;

import com.geometry.*;
import javax.xml.bind.annotation.XmlAttribute;

public class AngleXml {
	private String id;

	@XmlAttribute
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Angle getAngle() {
		return new Angle(id);
	}
}
