package com.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.geometry.Angle;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.Triangle;
import com.problem.Problem;

public class ProblemGetter {
	private Problem problem;
	
	public ProblemGetter(String fileName){
		this.problem = readFile(fileName);
	}
	public Problem readFile(String fileName){
		try{
			File file = new File(fileName);  
	        JAXBContext jaxbContext = JAXBContext.newInstance(Problem.class);  
	   
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	        Problem problem = (Problem) jaxbUnmarshaller.unmarshal(file); 
	        
	        return problem;
		}
		catch(JAXBException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GeoItem> getProblemFigureData(){
		List<GeoItem> geoItemList = problem.getQuestion().getFigure().getConstruction().getElements().getGeoItems();
		return geoItemList;
	}
	
	public List<GeoRelation> getProblemGivenRelations(){
		List<GeoRelation> geoRelations = problem.getQuestion().getFigure().getConstruction().getConstraints().getRelations();
		return geoRelations;
	}
}
