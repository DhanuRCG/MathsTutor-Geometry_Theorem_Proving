package com.problem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class Midpoint_of_line_segment {
	private PointXml point;
	private LineXml line;
	private List<GeoItem> geoItems;
	
	Midpoint_of_line_segment(){
		geoItems = new ArrayList<GeoItem>();
	}

	@XmlElement
	public PointXml getPoint() {
		return point;
	}

	public void setPoint(PointXml point) {
		this.point = point;
	}

	@XmlElement
	public LineXml getLine() {
		return line;
	}

	public void setLine(LineXml line) {
		this.line = line;
	}
	
	public List<GeoItem> getGeoItems(){
		Point pointItem;
		Line lineItem;
		lineItem=line.getLine();
		geoItems.add(lineItem);
		pointItem=point.getPoint();
		geoItems.add(pointItem);
		return geoItems;
	}
	
	public GeoRelation getGeoRelation() {
		getGeoItems();
		GeoItem item1 = geoItems.get(0);
		GeoItem item2 = geoItems.get(1);
		Relation relation = getRelation();
		GeoRelation geoRelation = new GeoRelation(item1, item2, relation);
		return geoRelation;
	}

	public Relation getRelation() {
		return Relation.valueOf("MIDPOINT");
	}
}
