package com.problem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import com.geometry.*;

public class Two_parallel_line_segments {
	private List<LineXml> line;
	private List<GeoItem> lines;
	
	Two_parallel_line_segments(){
		line = new ArrayList<LineXml>();
		lines = new ArrayList<GeoItem>();
	}

	@XmlElement(name="line")
	public List<LineXml> getLineXml() {
		return line;
	}

	public void setLineXml(List<LineXml> line) {
		this.line = line;
	}
	
	public List<GeoItem> getLines(){
		Line item;
		for(int i=0;i<line.size();i++){
			item=line.get(i).getLine();
			lines.add(item);
		}
		return lines;
	}
	
	public GeoRelation getGeoRelation() {
		getLines();
		GeoItem item1 = lines.get(0);
		GeoItem item2 = lines.get(1);
		Relation relation = getRelation();
		GeoRelation geoRelation = new GeoRelation(item1, item2, relation);
		return geoRelation;
	}

	public Relation getRelation() {
		return Relation.valueOf("PARALLEL_LINES");
	}
}
