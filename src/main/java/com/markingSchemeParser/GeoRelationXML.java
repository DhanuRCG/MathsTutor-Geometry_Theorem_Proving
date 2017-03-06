package com.markingSchemeParser;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.geometry.FactorGeoRelation;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.Relation;

public class GeoRelationXML {
	private String type;
	private boolean contain_factors;
	private List<GeoItemXML> geoItem;
	private String factor;
	
	GeoRelationXML(){
		geoItem = new ArrayList<GeoItemXML>();
	}

	@XmlAttribute
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name="geoItem")  
	public List<GeoItemXML> getGeoItemXML() {
		return geoItem;
	}

	public void setGeoItemXML(List<GeoItemXML> geoItem) {
		this.geoItem = geoItem;
	}
	
	public List<GeoItem> getGeoItemsList(){
		List<GeoItem> list = new ArrayList<GeoItem>();
		GeoItem item;
		for (int i = 0; i < geoItem.size(); i++) {
			item = geoItem.get(i).getGeoItem();
			list.add(item);
		}
		return list;
	}
	
	public GeoRelation getGeoRelation(){
		GeoItem item1 = getGeoItemsList().get(0);
		GeoItem item2 = getGeoItemsList().get(1);
		Relation relation = getRelation();
		GeoRelation geoRelation = new GeoRelation(item1,item2,relation);
		
		if(isContain_factors()){
			geoRelation = new FactorGeoRelation(item1, item2, relation, factor);
		}
		return geoRelation;
	}
	
	public Relation getRelation(){
		return Relation.valueOf(type.toUpperCase());
	}

	@XmlAttribute(name="contain_factors")
	public boolean isContain_factors() {
		return contain_factors;
	}

	public void setContain_factors(boolean contain_factors) {
		this.contain_factors = contain_factors;
	}

	@XmlElement(name="factor")
	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}
}
