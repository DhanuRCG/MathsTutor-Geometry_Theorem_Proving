package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class Theorem_02_Test {
	public static final void main(String[] args) {
        try {
        	
        	Point A = new Point('A');
        	Point B = new Point('B');
        	Point C = new Point('C');        	
        	Point D = new Point('D');
        	Point E = new Point('E');
  
        	Line AB = new Line(A, B);
        	Line CD = new Line(C, D);
        	
        	GeoRelation on_the_line = new GeoRelation(AB, E, Relation.ON_THE_LINE);
        	GeoRelation on_the_line2 = new GeoRelation(CD, E, Relation.ON_THE_LINE);
        	  
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            kSession.insert(on_the_line);
            kSession.insert(on_the_line2);
            
            kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
