package com.knowledge;

import java.util.ArrayList;

import com.geometry.GeoRelation;


public class StudentAnswerHolder extends KnowledgeHolder{
	
	private ArrayList<GeoRelation> answers = new ArrayList<GeoRelation>();
	
	private ArrayList<StepStatus> status = new ArrayList<StepStatus>();
	
	public int currentPosition;
	
	private int finalMarks = 0;
	
	public StudentAnswerHolder(ArrayList<GeoRelation> answers){
		super.knowledge = answers;
		this.answers = answers;
		currentPosition = 0;
	
	}
	
	public StudentAnswerHolder(ArrayList<GeoRelation> answers,ArrayList<StepStatus> status){
		super.knowledge = answers;
		this.answers = answers;
		this.status = status;
		currentPosition = 0;
	
	}
	public void initPostion(){
		currentPosition = 0;
	}
	
	public GeoRelation getNextAnswerStep(){

		if( endOfAnswer() ) return null;
		
		return answers.get(currentPosition++);

	}
	
	public int getGeoRelationPosition(GeoRelation relation){
		
	
			
			for (int i = 0; i < answers.size(); i++)  {
				

				GeoRelation geoRelation = answers.get(i);

				//System.out.println(geoRelation.getName());

				
				//if two relation types are not matching
				if( geoRelation.relation != relation.relation)
					continue;
				
				//if two first items are miss match
				if( geoRelation.firstItem.type != relation.firstItem.type)
					continue;

				//if two second items are miss match
				if( geoRelation.secondItem.type != relation.secondItem.type)
					continue;

				//if relation1 item1 == relation2.item1
				// and relation1 item2 == relation2.item2
				if(( geoItemsAreSame(geoRelation.firstItem, relation.firstItem) )
						&& ( geoItemsAreSame(geoRelation.secondItem, relation.secondItem) ) 
						//&& ( relationReasonsAreSame(geoRelation, comparingRelation))
						
						)
					return i;

				//if relation1 item2 == relation2.item1
				// and relation1 item1 == relation2.item2
				if(( geoItemsAreSame(geoRelation.secondItem, relation.firstItem) )
						&& ( geoItemsAreSame(geoRelation.firstItem, relation.secondItem) )
						//&& ( (  relationReasonsAreSame(geoRelation, comparingRelation) ))
						)
					return i;

			}
			
		
		return -1;

	}
	
	public boolean isGeorelationExists(GeoRelation relation){
		
		return getGeoRelationPosition(relation) != -1;
		
	}
	
	public boolean endOfAnswer(){
		
		return currentPosition >= answers.size();

	}
	
	public boolean markCurrentAnswer(StepStatus status){
		
		this.status.add( status );
		
		return true;
	}
	
	public void printAnswer(){
		
		for (int i = 0; i < answers.size() ; i++) {
			
			System.out.println(answers.get(i).getName());

		}
	}
	
	public void printMarkedAnswer(){
		for (int i = 0; i < answers.size() ; i++) {
			
			System.out.println(answers.get(i).getName() + "\t\t" + status.get(i).name());

		}
	}
	
	public StepStatus getStatusOfCurrentRelation(){
		return status.get(currentPosition-1);
	}

	public ArrayList<StepStatus> getStatus() {
		return status;
	}

	public void setStatus(ArrayList<StepStatus> status) {
		this.status = status;
	}

	public ArrayList<GeoRelation> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<GeoRelation> answers) {
		this.answers = answers;
	}
	
	public int getFinalMarks() {
		return finalMarks;
	}
	public void addToFinalMarks(int finalMarks) {
		this.finalMarks += finalMarks;
	}
	
	
	
	public void printAfterGiveMark() {
		// TODO Auto-generated method stub
		
		//float finalMarks = 0;
		
		/*for (int i = 0; i < answers.size() ; i++) {
			
			System.out.println("step : "+answers.get(i).getName()+"     "+answers.get(i).getMarkforrelation() + "\t\t" + answers.get(i).getMarkforreason());
	
		}*/
		
		System.out.println("Final Marks :" + this.finalMarks);
		
	}
	

}
