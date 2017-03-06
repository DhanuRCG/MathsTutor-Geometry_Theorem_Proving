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

public class Theorem_01_Test {
	public static final void main(String[] args) {
        try {
        	
        	/*Point A = new Point('A');
        	Point B = new Point('B');
        	Point C = new Point('C');        	
        	Point D = new Point('D');
  
        	Line AB = new Line(A, B);
        	Line CD = new Line(C, D);
        	Line AC = new Line(A,C);
        	Line BC = new Line(B,C);
        	
        	Angle ACD = new Angle(AC, CD);
        	Angle BCD = new Angle(CD, BC);
        	
        	GeoRelation on_the_line = new GeoRelation(AB, C, Relation.ON_THE_LINE);
  
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            kSession.insert(on_the_line);
            kSession.insert(CD);
            kSession.insert(ACD);
            kSession.insert(BCD);
            
            kSession.fireAllRules();*/
            
            Point A = new Point('A');
        	Point B = new Point('B');
        	Point C = new Point('C');        	
        	Point D = new Point('D');
        	Point E = new Point('E');
        	Point F = new Point('F');
        	Point O = new Point('O');
        	
        	Line BD = new Line(B,D);
        	Line BF = new Line(B,F);
        	Line DF = new Line(D,F);
        	
        	Angle DBF = new Angle(BD,BF);
        	Angle DFB = new Angle(BF,DF);
        	
        	Triangle DFO = new Triangle(D,F,O);
        	Triangle ECO = new Triangle(E,C,O);
        	
            GeoRelation DBFeqDFB = new GeoRelation(DBF, DFB, Relation.EQUALS);
            GeoRelation DFOconqECO = new GeoRelation(DFO, ECO, Relation.CONGRUENT_KO_KO_PA);
            GeoRelation on_the_line = new GeoRelation(DBF, DFB, Relation.EQUALS);
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
