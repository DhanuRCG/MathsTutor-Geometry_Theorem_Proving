package com.problem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class SubQuestion {
	private String id;
	private String isProof;
	private GeoRelationXml geoRelation;

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getIsProof() {
		return isProof;
	}

	public void setIsProof(String isProof) {
		this.isProof = isProof;
	}

	@XmlElement(name = "geoRelation")
	public GeoRelationXml getGeoRelationXml() {
		return geoRelation;
	}

	public void setGeoRelationXml(GeoRelationXml geoRelation) {
		this.geoRelation = geoRelation;
	}
}
