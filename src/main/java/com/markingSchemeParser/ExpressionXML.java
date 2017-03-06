package com.markingSchemeParser;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.geometry.GeoRelation;

public class ExpressionXML {
	private double mark;
	private boolean required;
	private GeoRelationXML geoRelation;
	private boolean contain_complex_operators;
	private List<GeoItemXML> geoItem;
	
	
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
	
	@XmlElement(name="geoRelation")
	public GeoRelationXML getGeoRelationXML() {
		return geoRelation;
	}
	public void setGeoRelationXML(GeoRelationXML geoRelation) {
		this.geoRelation = geoRelation;
	}
	
	public GeoRelation getGeoRelation(){
		return geoRelation.getGeoRelation();
	}

	@XmlAttribute(name="contain_complex_operators")
	public boolean isContain_complex_operators() {
		return contain_complex_operators;
	}

	public void setContain_complex_operators(boolean contain_complex_operators) {
		this.contain_complex_operators = contain_complex_operators;
	}
	
	@XmlElement(name="geoItem")  
	public List<GeoItemXML> getGeoItemXML() {
		return geoItem;
	}

	public void setGeoItemXML(List<GeoItemXML> geoItem) {
		this.geoItem = geoItem;
	}
}
