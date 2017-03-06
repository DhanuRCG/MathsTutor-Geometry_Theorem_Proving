package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.Triangle;

public class Theorem27 {

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
        	Point D= new Point('D');
        	Point E= new Point('E');
        	
        	Line BC = new Line(B,C);
        	Line AB = new Line(A,B);
        	Line AC = new Line(A,C);
        	Line DE = new Line(D,E);
        	Triangle ABC = new Triangle(A, B, C);
        	
        	 
        	//RetioGeoRelation a = new RetioGeoRelation(A,B,Relation.EQUALS,C,X);
        	
        	GeoRelation ABonD= new GeoRelation(AB, D, Relation.ON_THE_LINE);
        	GeoRelation AConE= new GeoRelation(AC, E, Relation.ON_THE_LINE);
        	GeoRelation DEparaBC= new GeoRelation(DE, BC, Relation.PARALLEL_LINES);
        	kSession.insert(ABC);
        	
        	kSession.insert(ABonD);
        	kSession.insert(AConE);
        	kSession.insert(DEparaBC);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}


}
