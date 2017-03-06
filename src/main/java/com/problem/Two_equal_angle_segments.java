package com.problem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.geometry.Angle;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Relation;

public class Two_equal_angle_segments {

	private List<AngleXml> angle;
	private List<GeoItem> geoItems;
	
	Two_equal_angle_segments(){
		angle = new ArrayList<AngleXml>();
	
		geoItems = new ArrayList<GeoItem>();
	}
	@XmlElement
	public List<AngleXml> getAngle() {
		return angle;
	}

	public void setLine(List<AngleXml> angle) {
		this.angle = angle;
	}
	
	public List<GeoItem> getAngles(){
		Angle item;
		for(int i=0;i<angle.size();i++){
			item=angle.get(i).getAngle();
			geoItems.add(item);
		}
		return geoItems;
	}
	
	public GeoRelation getGeoRelation() {
		// TODO Auto-generated method stub
		getAngles();
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
