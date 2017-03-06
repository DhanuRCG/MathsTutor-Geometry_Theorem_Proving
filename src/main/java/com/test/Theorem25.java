package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Quadrangle;
import com.geometry.Relation;
import com.geometry.RetioGeoRelation;
import com.geometry.Triangle;

public class Theorem25 {

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
        	
        	Line BC = new Line(B,C);
        	Line BX = new Line(B,X);
        	Line CX = new Line(C,X);
        	Triangle BAX = new Triangle(B, A, X);
        	Triangle ACB = new Triangle(A, C, B);
        	 
        	//RetioGeoRelation a = new RetioGeoRelation(A,B,Relation.EQUALS,C,X);
        	
        	GeoRelation BConX= new GeoRelation(BC, X, Relation.ON_THE_LINE);
        	kSession.insert(BAX);
        	kSession.insert(ACB);
        	kSession.insert(BX);
        	kSession.insert(BC);
        	kSession.insert(BConX);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}

}
