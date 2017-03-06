package com.test;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class AngleSameItemTest {
	
	public static void main(String args[]){
		
		Point A = new Point('A');
		Point B = new Point('B');
		Point C = new Point('C');
		Point D = new Point('D');
		Point E = new Point('E');
		
		Line AB = new Line(A, B);
		Line BC = new Line(B, C);
		Line CD = new Line(C, D);
		Line CE = new Line(C, E);
		Line BA = new Line(B, A);
		
		Angle ABC = new Angle(AB, BC);
		Angle BCD = new Angle(BC, CD);
		Angle DCE = new Angle(CD, CE);
		Angle test = new Angle(BA, BC);
		
		
		System.out.println(test.middle.getName());
		System.out.println(test.pointOne.getName());
		System.out.println(test.pointThree.getName());
		System.out.println(ABC.middle.getName());
		
		GeoRelation relData = new GeoRelation(ABC, BCD, Relation.EQUALS);
		GeoRelation relinf = new GeoRelation(BCD, DCE, Relation.EQUALS);
		
		System.out.println(ABC.sameItem(test));
	}

}
