package com.markingSchemeParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.geometry.GeoRelation;

public class ReasonXML {
	private double mark;
	private boolean required;
	private GeoRelationXML geoRelation;
	private String phrase;

	@XmlAttribute
	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@XmlAttribute
	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@XmlElement(name = "geoRelation")
	public GeoRelationXML getGeoRelationXML() {
		return geoRelation;
	}

	public void setGeoRelationXML(GeoRelationXML geoRelation) {
		this.geoRelation = geoRelation;
	}

	@XmlElement
	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public GeoRelation getGeoRelation() {
		return geoRelation.getGeoRelation();
	}

	public String getFullReason() {

		// if(geoRelation == null) System.out.println("geo relation is null
		// ------------------");
		if (geoRelation != null && geoRelation.getGeoRelation() != null && phrase != null)
			return geoRelation.getGeoRelation().getName() + phrase;

		else if (phrase != null)
			return phrase;

		return "";

	}
}
