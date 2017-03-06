package com.problem;

import javax.xml.bind.annotation.XmlAttribute;

import com.geometry.Angle;
import com.geometry.Quadrangle;

public class QuadrangleXml {

	
	private String id;

	@XmlAttribute
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Quadrangle getQuadrangle() {
		return new Quadrangle(id);
	}

}
