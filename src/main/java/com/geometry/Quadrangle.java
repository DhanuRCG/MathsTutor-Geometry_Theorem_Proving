package com.geometry;

import java.util.Arrays;

public class Quadrangle extends GeoItem{

	public Point points[];
    public Line lines[];
    public Angle angles[];
    public boolean isAParalleologram;
    
	public Quadrangle(Point a, Point b, Point c,Point d) {
		super(GeoType.QUADRANGLE);
		// TODO Auto-generated constructor stub
		points = new Point[4];
        lines = new Line[4];
        angles = new Angle[4];
        
        points[0] = a;
        points[1] = b;
        points[2] = c;
        points[3] = d;
        
        lines[0] = new Line(a, b);
        lines[1] = new Line(b, c);
        lines[2] = new Line(c, d);
        lines[3] = new Line(a, d);
        
        angles[0] = new Angle(lines[1], lines[0]);
        angles[1] = new Angle(lines[2], lines[1]);
        angles[2] = new Angle(lines[3], lines[2]);
        angles[3] = new Angle(lines[0], lines[3]);
        
        isAParalleologram = false;
        
        String tempName = a.getName() + b.getName() + c.getName() + d.getName();
        char[] chars = tempName.toCharArray();
        Arrays.sort(chars);
        name = new String(chars);
	}
	public Quadrangle(String name) {
		super(GeoType.QUADRANGLE);
		char pointNames[] = name.toCharArray();
		Point a = new Point(pointNames[0]);
		Point b = new Point(pointNames[1]);
		Point c = new Point(pointNames[2]);
		Point d = new Point(pointNames[3]);
		points = new Point[4];
		lines = new Line[4];
		angles = new Angle[4];

		points[0] = a;
		points[1] = b;
		points[2] = c;
		points[3] = d;

		lines[0] = new Line(a, b);
		lines[1] = new Line(b, c);
		lines[2] = new Line(c, d);
		lines[3] = new Line(d, a);

		angles[0] = new Angle(lines[0], lines[3]);
		angles[1] = new Angle(lines[1], lines[0]);
		angles[2] = new Angle(lines[2], lines[1]);
		angles[3] = new Angle(lines[3], lines[2]);
		name = getName();
	}

	@Override
	public Boolean sameItem(GeoItem item) {
		// TODO Auto-generated method stub
		for(Point p :((Quadrangle)item).points){
			if(isAPoint(p)){}
			else{
				return false;
			}
		}
		return true;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public boolean getIsAParallelogram() {
		return isAParalleologram;
	}
	
	public void setIsAParallelogram(boolean f) {
		isAParalleologram = f;
	}
	
	public boolean isAPoint(Point A){
		if(points[0].sameItem(A)){
			return true;
		}
		if(points[1].sameItem(A)){
			return true;
		}
		if(points[2].sameItem(A)){
			return true;
		}
		if(points[3].sameItem(A)){
			return true;
		}
		return false;
		
	}
	
	public boolean isALine(Line line){
		
		if( !isAPoint(line.endPoints[0]) && !isAPoint(line.endPoints[1]) ){
			
			return false;
		}
		
		for (int i = 0; i < 4; i++) {
			
			if(lines[i].sameItem(line)) return true;
					
		}

		return false; 
		
	}
	
	public boolean isAnAngle(Angle angle){
		
		return isAPoint(angle.pointOne) && isAPoint(angle.middle) && isAPoint(angle.pointThree) ;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
