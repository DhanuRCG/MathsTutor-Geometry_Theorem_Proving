package com.geometry;

public class Line extends GeoItem {

	public Point[] endPoints;

	public Line(Point point0, Point point1) {
		super(GeoType.LINE);
		endPoints = new Point[2];
		if(point0.name<=point1.name){
			endPoints[0] = point0;
			endPoints[1] = point1;
		}
		else{
			endPoints[0] = point1;
			endPoints[1] = point0;
		}
		
		
		name = getName();

	}

	public Line(String lineName) {
		super(GeoType.LINE);
		char[] arr = lineName.toCharArray();
		Point point0 = new Point(arr[0]);
		Point point1 = new Point(arr[1]);
		endPoints = new Point[2];
		endPoints[0] = point0;
		endPoints[1] = point1;
		name = getName();
	}

	public Boolean sameItem(GeoItem item) {
		if (item.type != GeoType.LINE)
			return false;

		if (getName().matches(((Line) item).getName()))
			return true;

		return false;

	}

	public String getName() {
		if (endPoints[0].getName().compareTo(endPoints[1].getName()) > 0) {
			return String.valueOf(endPoints[1].name) + String.valueOf(endPoints[0].name);

		} else
			return String.valueOf(endPoints[0].name) + String.valueOf(endPoints[1].name);
	}

	public boolean isAPoint(Point A) {
		if (endPoints[0].sameItem(A)) {
			return true;
		}
		if (endPoints[1].sameItem(A)) {
			return true;
		}
		return false;

	}

	public Boolean hasACommenPoint(Line line) {

		return (isAPoint(line.endPoints[0]) || isAPoint(line.endPoints[1]));

	}

	public Point getCommenPoint(Line line) {

		if (!hasACommenPoint(line)) {
			return null;
		}

		if (isAPoint(line.endPoints[0])) {

			return line.endPoints[0];
		} else {

			return line.endPoints[1];
		}
	}
}
