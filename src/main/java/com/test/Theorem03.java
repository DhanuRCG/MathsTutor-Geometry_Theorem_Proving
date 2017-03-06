package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class Theorem03 {
	public static void main(String args[]){
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
	        	Point E=new Point('E');
	        	Point F=new Point('F');
	        	Point G=new Point('G');
	        	Point H=new Point('H');
	        	
	        	Line AB =new Line(A,B);
	        	Line CD =new Line(C,D);
	        	Line GF=new Line(G,F);
	        	Line EH=new Line(E,H);
	        	
	        	Line EF=new Line(E, F);
	        	Line AE=new Line(A,E);
	        	Line DF=new Line(D, F);
	        	Line EG=new Line(E,G);
	        	Line CE=new Line(C, E);
	        	Line CF=new Line(C, F);
	        	Line FG=new Line(F, G);
	        	Line BE=new Line(B, E);
	        	Line FH=new Line(F, H);
	        	
	        	Angle AEG=new Angle(AE, EG);
	        	Angle GEB=new Angle(EG, BE);
	        	Angle BEF=new Angle(BE, EF);
	        	Angle AEF=new Angle(EF, AE);
	        	Angle CFE=new Angle(CF, EF);
	        	Angle EFD=new Angle(EF, DF);
	        	Angle DFH=new Angle(DF, FH);
	        	Angle CFH=new Angle(FH, CF);
	        	
	        	
	        	GeoRelation online1=new GeoRelation(AB, E, Relation.ON_THE_LINE);
	        	GeoRelation online2=new GeoRelation(CD, F, Relation.ON_THE_LINE);
	        	GeoRelation online3=new GeoRelation(EH, F, Relation.ON_THE_LINE);
	        	GeoRelation online4=new GeoRelation(FG, E, Relation.ON_THE_LINE);
	        	
	        	GeoRelation online5=new GeoRelation(AB, CD, Relation.PARALLEL_LINES);
	        	
	        	kSession.insert(AEG);
	        	kSession.insert(GEB);
	        	kSession.insert(BEF);
	        	kSession.insert(AEF);
	        	kSession.insert(CFE);
	        	kSession.insert(EFD);
	        	kSession.insert(DFH);
	        	kSession.insert(CFH);
	        	
	        	kSession.insert(online1);
	        	kSession.insert(online2);
	        	//kSession.insert(twoangle);
	        	
	        	kSession.insert(online3);
	        	kSession.insert(online4);
	        	kSession.insert(online5);
	        	
	        	//kSession.insert(correspondingAngle);
	        	
	        	kSession.fireAllRules();
	        	
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	}
}
