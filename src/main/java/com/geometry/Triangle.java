package com.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle extends GeoItem {

	public Point points[];
	public Line lines[];
	public Angle angles[];

	public Triangle(Point a, Point b, Point c) {
		super(GeoType.TRIANGLE);
		points = new Point[3];
		lines = new Line[3];
		angles = new Angle[3];

		points[0] = a;
		points[1] = b;
		points[2] = c;

		lines[0] = new Line(a, b);
		lines[1] = new Line(b, c);
		lines[2] = new Line(a, c);

		angles[0] = new Angle(lines[0], lines[2]);
		angles[1] = new Angle(lines[1], lines[0]);
		angles[2] = new Angle(lines[2], lines[1]);
		name = getName();
	}

	public Triangle(String name) {
		super(GeoType.TRIANGLE);
		char pointNames[] = name.toCharArray();
		Point a = new Point(pointNames[0]);
		Point b = new Point(pointNames[1]);
		Point c = new Point(pointNames[2]);
		points = new Point[3];
		lines = new Line[3];
		angles = new Angle[3];

		points[0] = a;
		points[1] = b;
		points[2] = c;

		lines[0] = new Line(a, b);
		lines[1] = new Line(b, c);
		lines[2] = new Line(a, c);

		angles[0] = new Angle(lines[0], lines[2]);
		angles[1] = new Angle(lines[1], lines[0]);
		angles[2] = new Angle(lines[2], lines[1]);

		name = getName();
	}

	@Override
	public Boolean sameItem(GeoItem item) {
		if (item.type != GeoType.TRIANGLE)
			return false;

		Point one = ((Triangle) item).points[0];
		Point two = ((Triangle) item).points[1];
		Point three = ((Triangle) item).points[2];

		if(isAPoint(one) && isAPoint(two) && isAPoint(three))
			return true;
		
		return false;
	}

	public void setValueToAngle(Angle angle) {
		if (angles[0].sameItem(angle)) {
			angles[0].value = angle.value;
		}
		if (angles[1].sameItem(angle)) {
			angles[1].value = angle.value;
		}
		if (angles[2].sameItem(angle)) {
			angles[2].value = angle.value;
		}
	}

	@Override
	public String getName() {
		char[] arr = new char[3];
		arr[0] = points[0].name;
		arr[1] = points[1].name;
		arr[2] = points[2].name;

		Arrays.sort(arr);
		String a = new String(arr);
		return a;
	}

	public boolean isAPoint(Point A) {
		if (points[0].sameItem(A)) {
			return true;
		}
		if (points[1].sameItem(A)) {
			return true;
		}
		if (points[2].sameItem(A)) {
			return true;
		}
		return false;

	}

	public boolean isALine(Line AB) {
		if (isAPoint(AB.endPoints[0]) && isAPoint(AB.endPoints[1])) {
			return true;
		}
		return false;
	}

	public Line getOppositeSide(Angle angle) {
		return new Line(angle.pointOne, angle.pointThree);
	}

	public boolean isAnAngle(Angle ABC) {
		if (ABC.sameItem(angles[0]) || ABC.sameItem(angles[1]) || ABC.sameItem(angles[2])) {
			return true;
		}
		return false;
	}
	
	public List<GeoItem> getRemainingAngles(GeoItem...geoItems ){
		List<GeoItem> list = new ArrayList<GeoItem>();
		list.addAll(Arrays.asList(angles));
		for(GeoItem givenItem: geoItems ){
			for(GeoItem a:list){  
				if(a.type == givenItem.type && a.sameItem(givenItem)){
					list.remove(a);
					break;
				}
			}  
		}
		return list;

	}
	public Line getOppositeSide(Point A){
		if(points[0].sameItem(A)){
			return new Line(points[1],points[2]);
		}
		if(points[1].sameItem(A)){
			return new Line(points[2],points[0]);
		}
		if(points[2].sameItem(A)){
			return new Line(points[0],points[1]);
		}
		return null;
		
	}
	
}
