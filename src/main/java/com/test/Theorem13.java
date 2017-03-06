package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.Triangle;

public class Theorem13 {
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
        	
        	
        	Line AB=new Line(A,B);
        	Line BC=new Line(B,C);
        	Line AC=new Line(A,C);
        	
        	Triangle ABC=new Triangle(A,B,C);
        	Angle ABCangle=new Angle(AB,BC);
        	
        	Angle CABangle=new Angle(AC,AB);
        	
        	GeoRelation ABCequalCAB= new GeoRelation(ABCangle, CABangle, Relation.EQUALS);
        	//GeoRelation BDonE= new GeoRelation(BD, E, Relation.ON_THE_LINE);
        	kSession.insert(ABC);
        	
        	kSession.insert(ABCequalCAB);
        	//kSession.insert(DEF);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}
}
