package com.markingSchemeParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.geometry.Angle;
import com.geometry.GeoItem;
import com.geometry.GeoValue;
import com.geometry.Line;
import com.geometry.Triangle;

public class GeoItemXML {

	private String type;
	private String name;

	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeoItem getGeoItem() {
		GeoItem item;

		switch (type) {
		case "line":
			item = new Line(name);
			break;
		case "angle":
			item = new Angle(name);
			break;
		case "triangle":
			item = new Triangle(name);
			break;
		case "value":
			item = new GeoValue(name);
			break;
		default:
			item = null;
			break;
		}

		return item;
	}
}
