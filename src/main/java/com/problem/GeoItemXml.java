package com.problem;

import javax.xml.bind.annotation.XmlAttribute;
import com.geometry.*;

public class GeoItemXml {
	private String type;
	private String id;

	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GeoItem getGeoItem() {
		GeoItem item;
		switch (type) {
		case "line":
			item = new Line(id);
			break;
		case "angle":
			item = new Angle(id);
			break;
		case "triangle":
			item = new Triangle(id);
			break;
		default:
			item = null;
			break;
		}
		return item;
	}
}
