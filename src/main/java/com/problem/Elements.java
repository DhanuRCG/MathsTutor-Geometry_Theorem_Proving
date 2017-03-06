package com.problem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import com.geometry.*;

public class Elements {
	private List<PointXml> point;
	private List<LineXml> line;
	private List<AngleXml> angle;
	private List<TriangleXml> triangle;
	private List<QuadrangleXml> quadrangle;
	private List<GeoItem> geoItems;

	Elements() {
		point = new ArrayList<PointXml>();
		line = new ArrayList<LineXml>();
		angle = new ArrayList<AngleXml>();
		triangle = new ArrayList<TriangleXml>();
		quadrangle = new ArrayList<QuadrangleXml>();
		geoItems = new ArrayList<GeoItem>();
		
	}

	@XmlElement(name = "point")
	public List<PointXml> getPointXml() {
		return point;
	}

	public void setPointXml(List<PointXml> point) {
		this.point = point;
	}

	@XmlElement(name = "line")
	public List<LineXml> getLineXml() {
		return line;
	}

	public void setLineXml(List<LineXml> line) {
		this.line = line;
	}

	@XmlElement(name = "angle")
	public List<AngleXml> getAngleXml() {
		return angle;
	}

	public void setAngleXml(List<AngleXml> angle) {
		this.angle = angle;
	}
	
	@XmlElement(name = "triangle")
	public List<TriangleXml> getTriangleXml() {
		return triangle;
	}

	public void setTriangleXml(List<TriangleXml> triangle) {
		this.triangle = triangle;
	}
	
	@XmlElement(name = "quadrangle")
	public List<QuadrangleXml> getQuadrangleXml() {
		return quadrangle;
	}

	public void setQuadrangleXml(List<QuadrangleXml> quadrangle) {
		this.quadrangle = quadrangle;
	}

	private List<Point> getPoints() {
		List<Point> list = new ArrayList<Point>();
		Point item;
		for (int i = 0; i < point.size(); i++) {
			item = point.get(i).getPoint();
			list.add(item);
			geoItems.add(item);
		}
		return list;
	}

	private List<Line> getLines() {
		List<Line> list = new ArrayList<Line>();
		Line item;
		for (int i = 0; i < line.size(); i++) {
			item = line.get(i).getLine();
			list.add(item);
			geoItems.add(item);
		}
		return list;
	}

	private List<Angle> getAngles() {
		List<Angle> list = new ArrayList<Angle>();
		Angle item;
		for (int i = 0; i < angle.size(); i++) {
			item = angle.get(i).getAngle();
			list.add(item);
			geoItems.add(item);
		}
		return list;
	}
	
	private List<Triangle> getTriangles() {
		List<Triangle> list = new ArrayList<Triangle>();
		Triangle item;
		for (int i = 0; i < triangle.size(); i++) {
			item = triangle.get(i).getTriangle();
			list.add(item);
			geoItems.add(item);
		}
		return list;
	}

	public List<GeoItem> getGeoItems() {
		getPoints();
		getLines();
		getAngles();
		getTriangles();
		getQuadrangle();
		return geoItems;
	}

	private List<Quadrangle> getQuadrangle() {
		// TODO Auto-generated method stub
		List<Quadrangle> list = new ArrayList<Quadrangle>();
		Quadrangle item;
		for (int i = 0; i < quadrangle.size(); i++) {
			item = quadrangle.get(i).getQuadrangle();
			list.add(item);
			geoItems.add(item);
		}
		return list;
	}
}
