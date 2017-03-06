package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class Theorem_03_2_Test {
	
	public static final void main(String[] args) {
        try {
        		
        	Point A = new Point('A');
        	Point B = new Point('B');
        	Point C = new Point('C');        	
        	Point D = new Point('D');
        	Point O = new Point('O');
        	
        	Line AB = new Line(A, B);
        	Line CD = new Line(C, D);
        	Line BC = new Line(B, C);
        	Line AD = new Line(A, D);
        	
        	Line AO = new Line(A, O);
        	Line DO = new Line(D, O);
        	Line BO = new Line(B, O);
        	Line CO = new Line(C, O);
        	
        	Angle ABC = new Angle(BC, AB);
        	Angle BCD = new Angle(BC, CD);
        	Angle ABD = new Angle(AB, AD);
        	Angle ADC = new Angle(CD, AD);
        	GeoRelation parrLines = new GeoRelation(AB, CD, Relation.PARALLEL_LINES);
  
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rule");

            // go !
        	kSession.insert(A);
            kSession.insert(B);
            kSession.insert(C);
            kSession.insert(AB);
            kSession.insert(CD);
            kSession.insert(BC);
            kSession.insert(AD);
            kSession.insert(ABC);
            kSession.insert(BCD);
            kSession.insert(ABD);
            kSession.insert(ADC);
            kSession.insert(parrLines);
             
            kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
