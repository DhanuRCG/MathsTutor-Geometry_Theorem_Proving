package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.geometry.ExpressionRelation;
import com.geometry.Point;
import com.geometry.Triangle;

public class Theorem10 {
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
	        	
	        	Triangle ABC=new Triangle(A,B,C);
	        	
	        	
	        	kSession.insert(ABC);
	        	
	        
	        	kSession.fireAllRules();
	        	
	        	
	        	
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	}
}
