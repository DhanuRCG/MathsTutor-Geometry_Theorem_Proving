package com.problem;

import javax.xml.bind.annotation.XmlElement;

public class Free_point {
	private PointXml point;

	@XmlElement
	public PointXml getPoint() {
		return point;
	}

	public void setPoint(PointXml point) {
		this.point = point;
	}
}
