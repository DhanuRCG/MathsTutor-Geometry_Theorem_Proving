package com.knowledge;

import java.util.ArrayList;

import com.geometry.GeoRelation;

public class TransitionKnowledgeHolder extends KnowledgeHolder{
	
	public TransitionKnowledgeHolder(ArrayList<GeoRelation> knowledge){
		super(knowledge);
	}

	public TransitionKnowledgeHolder(){

	}
	
	public void clear(){
		knowledge = null;
		knowledge = new ArrayList<GeoRelation>();	
	}
	
	public void insertKnowledgeBulk(KnowledgeHolder knowledge){
		this.knowledge.addAll(knowledge.getFullKnowledge());	
	}
	
}
