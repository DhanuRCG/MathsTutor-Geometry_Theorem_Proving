package com.problem;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.Relation;

public class GeoRelationXml {
	private String type;
	private List<GeoItemXml> geoItem;
	private List<Character> mo;
	private List<Integer> mn;

	GeoRelationXml() {
		geoItem = new ArrayList<GeoItemXml>();
		mo = new ArrayList<Character>();
		mn = new ArrayList<Integer>();
	}

	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name="geoItem")
	public List<GeoItemXml> getGeoItems() {
		return geoItem;
	}

	public void setGeoItems(List<GeoItemXml> geoItem) {
		this.geoItem = geoItem;
	}
	
	@XmlElement(name="mo")
	public List<Character> getMathOperators() {
		return mo;
	}

	public void setMathOperators(List<Character> mo) {
		this.mo = mo;
	}
	
	@XmlElement(name="mn")
	public List<Integer> getMathNumbers() {
		return mn;
	}

	public void setMathNumbers(List<Integer> mn) {
		this.mn = mn;
	}

	public List<GeoItem> getGeoItemsList() {
		List<GeoItem> list = new ArrayList<GeoItem>();
		GeoItem item;
		for (int i = 0; i < geoItem.size(); i++) {
			item = geoItem.get(i).getGeoItem();
			list.add(item);
		}
		return list;
	}

	public GeoRelation getGeoRelation() {
		GeoItem item1 = getGeoItemsList().get(0);
		GeoItem item2 = getGeoItemsList().get(1);
		Relation relation = getRelation();
		GeoRelation geoRelation = new GeoRelation(item1, item2, relation);
		return geoRelation;
	}

	public Relation getRelation() {
		return Relation.valueOf(type.toUpperCase());
	}
}
