package com.problem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import com.geometry.*;

public class Constraints {
	private List<Two_parallel_line_segments> two_parallel_line_segments;
	private List<Point_on_line_segment> point_on_line_segment;
	private List<Free_point> free_point;
	private List<Midpoint_of_line_segment> midpoint_of_line_segment;
	private List<Two_equal_line_segments> two_equal_line_segments;
	
	private List<Two_equal_angle_segments> two_equal_angle_segments;
	
	private List<GeoRelation> relations;
	
	Constraints(){
		two_parallel_line_segments = new ArrayList<Two_parallel_line_segments>();
		point_on_line_segment = new ArrayList<Point_on_line_segment>();
		midpoint_of_line_segment = new ArrayList<Midpoint_of_line_segment>();
		two_equal_line_segments = new ArrayList<Two_equal_line_segments>();
		two_equal_angle_segments = new ArrayList<Two_equal_angle_segments>();
		relations = new ArrayList<GeoRelation>();
	}

	@XmlElement(name="two_parallel_line_segments")
	public List<Two_parallel_line_segments> getTwo_parallel_line_segmentsXml() {
		return two_parallel_line_segments;
	}

	public void setTwo_parallel_line_segmentsXml(List<Two_parallel_line_segments> two_parallel_line_segments) {
		this.two_parallel_line_segments = two_parallel_line_segments;
	}

	@XmlElement(name="point_on_line_segment")
	public List<Point_on_line_segment> getPoint_on_line_segmentXml() {
		return point_on_line_segment;
	}

	public void setPoint_on_line_segmentXml(List<Point_on_line_segment> point_on_line_segment) {
		this.point_on_line_segment = point_on_line_segment;
	}

	@XmlElement(name="free_point")
	public List<Free_point> getFree_pointXml() {
		return free_point;
	}

	public void setFree_pointXml(List<Free_point> free_point) {
		this.free_point = free_point;
	}

	@XmlElement(name="midpoint_of_line_segment")
	public List<Midpoint_of_line_segment> getMidpoint_of_line_segmentXml() {
		return midpoint_of_line_segment;
	}

	public void setMidpoint_of_line_segmentXml(List<Midpoint_of_line_segment> midpoint_of_line_segment) {
		this.midpoint_of_line_segment = midpoint_of_line_segment;
	}

	@XmlElement(name="two_equal_line_segments")
	public List<Two_equal_line_segments> getTwo_equal_line_segmentsXml() {
		return two_equal_line_segments;
	}

	public void setTwo_equal_line_segmentsXml(List<Two_equal_line_segments> two_equal_line_segments) {
		this.two_equal_line_segments = two_equal_line_segments;
	}
	
	@XmlElement(name="two_equal_angle_segments")
	public List<Two_equal_angle_segments> getTwo_equal_angle_segmentsXml() {
		return two_equal_angle_segments;
	}

	public void setTwo_equal_angle_segmentsXml(List<Two_equal_angle_segments> two_equal_angle_segments) {
		this.two_equal_angle_segments = two_equal_angle_segments;
	}
	
	public List<GeoRelation> getRelations(){
		for(int i=0;i<two_parallel_line_segments.size();i++){
			relations.add(two_parallel_line_segments.get(i).getGeoRelation());
		}
		for(int i=0;i<point_on_line_segment.size();i++){
			relations.add(point_on_line_segment.get(i).getGeoRelation());
		}
		for(int i=0;i<midpoint_of_line_segment.size();i++){
			GeoRelation midPoint = midpoint_of_line_segment.get(i).getGeoRelation();
			relations.add(midPoint);
			GeoRelation onLine = new GeoRelation(midPoint.firstItem, midPoint.secondItem, Relation.ON_THE_LINE);
			relations.add(onLine);
		}
		for(int i=0;i<two_equal_line_segments.size();i++){
			relations.add(two_equal_line_segments.get(i).getGeoRelation());
		}
		for(int i=0;i<two_equal_angle_segments.size();i++){
			relations.add(two_equal_angle_segments.get(i).getGeoRelation());
		}
		return relations;
	}
}
