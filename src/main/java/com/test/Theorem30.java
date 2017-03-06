package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.RetioGeoRelation;
import com.geometry.Triangle;

public class Theorem30 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
        	
        	
	  		
        	
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
        	Point A= new Point('A');
        	Point B= new Point('B');
        	Point C= new Point('C');
        	Point X= new Point('X');
        	Point Y= new Point('Y');
        	Point Z= new Point('Z');
        	Line BC = new Line(B,C);
        	Line AB = new Line(A,B);
        	Line AC = new Line(A,C);
        	Line XY = new Line(X,Y);
        	Line BX = new Line(B,X);
        	Line BY = new Line(B,Y);
        	Line CY = new Line(C,Y);
        	Line AX = new Line(A,X);
        	Line YZ = new Line(Y,Z);
        	Line XZ = new Line(X,Z);
        	Triangle ABC = new Triangle(A, B, C);
        	Triangle XYZ = new Triangle(X, Y, Z);
//        	 
//        	Angle abc = new Angle(AB,BC);
//        	Angle bca = new Angle(BC,AC);
//        	Angle cab = new Angle(AB,AC);
//        	
//        	Angle def = new Angle(DE,EF);
//        	Angle efd = new Angle(EF,DF);
//        	Angle fde = new Angle(DE,DF);
        	
//        	GeoRelation abcequaldef= new GeoRelation(abc, def, Relation.EQUALS);
//        	GeoRelation bcaequalefd= new GeoRelation(bca, efd, Relation.EQUALS);
//        	GeoRelation cabequalfde= new GeoRelation(cab, fde, Relation.EQUALS);
        	//RetioGeoRelation a = new RetioGeoRelation(A,B,Relation.EQUALS,C,X);
        	
//        	GeoRelation ABCcongruentDEF= new GeoRelation(ABC, DEF, Relation.CONGRUENT);
//        	ABCcongruentDEF.reason = "කෝ.කෝ.කෝ.";
        	
        	//GeoRelation DEparaBC= new GeoRelation(DE, BC, Relation.PARALLEL_LINES);
        	
        	RetioGeoRelation retioGeoRelation1 = new RetioGeoRelation(AB,XY, Relation.EQUALS, AC, XZ);
        	RetioGeoRelation retioGeoRelation2 = new RetioGeoRelation(AC,XZ, Relation.EQUALS, BC, YZ);
        	kSession.insert(ABC);
        	kSession.insert(XYZ);
        	kSession.insert(retioGeoRelation1);
        	kSession.insert(retioGeoRelation2);
        	

        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}
}
