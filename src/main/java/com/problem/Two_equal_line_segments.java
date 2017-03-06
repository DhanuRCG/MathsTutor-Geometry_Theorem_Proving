package com.problem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Relation;

public class Two_equal_line_segments {
	private List<LineXml> line;
	private List<GeoItem> geoItems;
	
	Two_equal_line_segments(){
		line = new ArrayList<LineXml>();
		geoItems = new ArrayList<GeoItem>();
	}

	@XmlElement
	public List<LineXml> getLine() {
		return line;
	}

	public void setLine(List<LineXml> line) {
		this.line = line;
	}
	
	public List<GeoItem> getLines(){
		Line item;
		for(int i=0;i<line.size();i++){
			item=line.get(i).getLine();
			geoItems.add(item);
		}
		return geoItems;
	}
	
	public GeoRelation getGeoRelation() {
		getLines();
		GeoItem item1 = geoItems.get(0);
		GeoItem item2 = geoItems.get(1);
		Relation relation = getRelation();
		GeoRelation geoRelation = new GeoRelation(item1, item2, relation);
		return geoRelation;
	}

	public Relation getRelation() {
		return Relation.valueOf("EQUALS");
	}
}
