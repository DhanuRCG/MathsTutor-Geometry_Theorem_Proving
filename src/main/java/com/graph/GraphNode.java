package com.graph;

import java.util.ArrayList;
import java.util.Iterator;

import com.geometry.GeoRelation;
import com.geometry.Relation;
import com.google.common.collect.Multimap;

public class GraphNode  implements java.io.Serializable {
	
	private GeoRelation relation;
	private ArrayList<GraphNode> predesisors;
	private boolean isRootFact;
	private  Graph graph;
	private ArrayList<String> reasons;
	
	public GraphNode(GeoRelation relation, ArrayList<GraphNode> predesisors) {
		this.relation = relation;
		this.predesisors = predesisors;
		this.isRootFact = false;
		this.reasons = new ArrayList<String>();
		setReasons();
	}
	
	public GraphNode(GeoRelation relation, ArrayList<GraphNode> predesisors, boolean root) {
		this.relation = relation;
		this.predesisors = predesisors;
		this.isRootFact = false;
		if(root) this.isRootFact = true;
		this.reasons = new ArrayList<String>();
		setReasons();
	}

	public String getName(){
		return this.relation.getName() +" --> "+ getPredesisorNames();
	}

	public String getPredesisorNames(){
		
		if(predesisors == null && isRootFact) return "ROOT";
		if(predesisors == null ) return "";
		
		String name = "";
		for (Iterator iterator = predesisors.iterator(); iterator.hasNext();) {
			GraphNode predissisorGraphNode = (GraphNode) iterator.next();
			
			name = name + " , " + predissisorGraphNode.getRelation().getName();
			
			if(isRootFact) name += " ROOT";
			
		}
		
		return name;
	}
	public GeoRelation getRelation() {
		return relation;
	}

	public void setRelation(GeoRelation relation) {
		this.relation = relation;
	}

	public ArrayList<GraphNode> getPredesisors() {
		
		ArrayList<GraphNode> tempPredesisors = new ArrayList<GraphNode>();
		
		if(predesisors == null){ return null; }
		
		for (Iterator iterator = predesisors.iterator(); iterator.hasNext();) {
			GraphNode predissisorGraphNode = (GraphNode) iterator.next();
			
			tempPredesisors.add(predissisorGraphNode);
			
		}
		return tempPredesisors;
	}

	public void setPredesisors(ArrayList<GraphNode> predesisors) {
		this.predesisors = predesisors;
	}

	public boolean isRootFact() {
		return isRootFact;
	}

	
	public void printChain(){
		String chain = relation.getName();
		
		if(!isRootFact){
			for (Iterator iterator = predesisors.iterator(); iterator.hasNext();) {
				GraphNode predesissrNode = (GraphNode) iterator.next();
				chain = chain + " --> " + predesissrNode.getName();
			}

		}
		
		System.out.println(chain);
	}

	public void clearPredesissors(Multimap<String,String> map){
		
	}
	
	public void addReason(String reason){
		this.reasons.add(reason);
	}
	
	public ArrayList<String> getReasons(){
		return this.reasons;
	}
	
	private void setReasons(){
		
		if(predesisors == null) { return; }
		
		for (Iterator iterator = predesisors.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			
			String reason = graphNode.getRelation().firstItem.getName();
			
			if(graphNode.getRelation().relation == Relation.EQUALS){
				reason += " = ";
			}

			if(graphNode.getRelation().relation == Relation.ON_THE_LINE){
				reason += " ON_THE_LINE ";
			}
			
			if(graphNode.getRelation().relation == Relation.CONGRUENT){
				reason += " අංගසම ත්‍රිකෝණ ";
			}

			if(graphNode.getRelation().relation == Relation.MIDPOINT){
				reason += " MIDPOINT ";
			}

			if(graphNode.getRelation().relation == Relation.PARALLEL_LINES){
				reason += " සමාන්තර  රේඛා ";
			}


			reason += graphNode.getRelation().secondItem.getName();

			addReason(reason);
		}
	}
	
	public void addPredesisior(ArrayList<GraphNode> predesisor){
		if(predesisor != null){
			
			for (Iterator iterator = predesisor.iterator(); iterator.hasNext();) {
				GraphNode graphNode = (GraphNode) iterator.next();
				GraphNode newGraphNode = new GraphNode(graphNode.getRelation(), graphNode.getPredesisors());

				this.predesisors.add(newGraphNode);
			}
		}
		else{
		}
	}
	
	public void setRootFact(){
		isRootFact = true;
	}
}
