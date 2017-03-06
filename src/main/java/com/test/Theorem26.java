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

public class Theorem26 {
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
        	Point F= new Point('F');
        	Line BC = new Line(B,C);
        	Line AB = new Line(A,B);
        	Line AC = new Line(A,C);
        	Line DE = new Line(D,E);
        	Line BD = new Line(B,D);
        	Line BE = new Line(B,E);
        	Line CE = new Line(C,E);
        	Line AD = new Line(A,D);
        	Line EF = new Line(E,F);
        	Line DF = new Line(D,F);
        	Triangle ABC = new Triangle(A, B, C);
        	Triangle DEF = new Triangle(D, E, F);
        	 
        	Angle abc = new Angle(AB,BC);
        	Angle bca = new Angle(BC,AC);
        	Angle cab = new Angle(AB,AC);
        	
        	Angle def = new Angle(DE,EF);
        	Angle efd = new Angle(EF,DF);
        	Angle fde = new Angle(DE,DF);
        	abc.value = 90;
//        	GeoRelation abcequaldef= new GeoRelation(abc, def, Relation.EQUALS);
//        	GeoRelation bcaequalefd= new GeoRelation(bca, efd, Relation.EQUALS);
//        	GeoRelation cabequalfde= new GeoRelation(cab, fde, Relation.EQUALS);
//        	//RetioGeoRelation a = new RetioGeoRelation(A,B,Relation.EQUALS,C,X);
//        	
//        	GeoRelation ABCcongruentDEF= new GeoRelation(ABC, DEF, Relation.CONGRUENT);
//        	ABCcongruentDEF.reason = "කෝ.කෝ.කෝ.";
        	
        	//GeoRelation DEparaBC= new GeoRelation(DE, BC, Relation.PARALLEL_LINES);
        	kSession.insert(ABC);
        	kSession.insert(abc);
//        	kSession.insert(ABCcongruentDEF);
//        	kSession.insert(DEF);
//        	kSession.insert(abcequaldef);
//        	kSession.insert(bcaequalefd);
//        	kSession.insert(cabequalfde);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}
}
