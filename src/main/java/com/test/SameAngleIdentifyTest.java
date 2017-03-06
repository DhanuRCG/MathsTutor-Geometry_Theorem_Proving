package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class SameAngleIdentifyTest {
	public static final void main(String[] args) {
        try {
        	
        	Point A = new Point('A');
        	Point B = new Point('B');
        	Point C = new Point('C');        	
        	Point D = new Point('D');
  
        	Line AB = new Line(A,B);
        	//Line BC = new Line(B,C);
        	Line AD = new Line(A,D);
        	Line AC = new Line(A,C);
        	
        	Angle DAB = new Angle(AD, AB);
        	Angle DAC = new Angle(AD, AC);
        	
        	GeoRelation on_the_line = new GeoRelation(AB, C, Relation.ON_THE_LINE);
        	
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            kSession.insert(on_the_line);
            kSession.insert(A);
            kSession.insert(B);
            kSession.insert(C);
            kSession.insert(D);
            kSession.insert(AB);
            //kSession.insert(BC);
            kSession.insert(AD);
            kSession.insert(AC);
            kSession.insert(DAB);
            kSession.insert(DAC);
            kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
}
