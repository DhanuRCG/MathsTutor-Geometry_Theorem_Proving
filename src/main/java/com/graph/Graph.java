package com.graph;

import java.util.ArrayList;
import java.util.Iterator;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.GeoType;
import com.geometry.Relation;
import com.knowledge.HashTestKnowledge;
import com.knowledge.KnowledgeHolder;

public class Graph implements java.io.Serializable {
	
	private ArrayList<GraphNode> graphNodes;

	public Graph(ArrayList<GraphNode> graphNodes) {
		this.graphNodes = graphNodes;
	}

	public Graph() {
		this.graphNodes = new ArrayList<GraphNode>();
	}
	
	public GraphNode findNode(GeoRelation relation){
		
		if(relation == null) return null;
		
		for (Iterator iterator = this.graphNodes.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			if(relation.sameRelation(graphNode.getRelation())){
	//			System.out.println("in find node check : ");
	//			System.out.print("given relation : " + relation.getName());
	//			System.out.println(" matching to : " + graphNode.getRelation().getName());
				return graphNode;
			}
		}
		
		return null;
	}
	
	//create and add new graph node
	public void addNode(GeoRelation relation, GeoRelation... predesisors){
		
		if( relationExists(relation) ) return; // relation exists
		
		ArrayList predesisorsList = new ArrayList<GeoRelation>();
		
		for (int i = 0; i < predesisors.length; i++) {
			
			GraphNode predesissorNode = findNode(predesisors[i]);

			if(predesissorNode == null){
				predesissorNode = new GraphNode(predesisors[i], null);
			}
			
			predesisorsList.add(predesissorNode);
		}
		
		GraphNode graphNode = new GraphNode(relation, predesisorsList);
		
		this.graphNodes.add(graphNode);
	}
	
	//create and add new graph node with reason
		public void addNode(GeoRelation relation, String reason, GeoRelation... predesisors){
			
			if( relationExists(relation) ) return; // relation exists
			
			ArrayList predesisorsList = new ArrayList<GeoRelation>();
			
			for (int i = 0; i < predesisors.length; i++) {
				
				GraphNode predesissorNode = findNode(predesisors[i]);

				if(predesissorNode == null){
					predesissorNode = new GraphNode(predesisors[i], null);
				}
				
				predesisorsList.add(predesissorNode);
			}
			
			GraphNode graphNode = new GraphNode(relation, predesisorsList);
			
			graphNode.addReason(reason);
			
			this.graphNodes.add(graphNode);
		}
	
	// add new graph node
		public void addNode(GraphNode graphNode){
			
			if( relationExists(graphNode.getRelation()) ) {
				return;
				
			} // relation exists
			
			this.graphNodes.add(graphNode);
			
		}
		
		public void addNode(GeoRelation relation){
			
			if( relationExists(relation) ) {
				return;
				
			} 
			addRootNode(relation);
		}
		
		
	public boolean relationExists(GeoRelation relation){
		return findNode(relation) != null;
	}
	
	
	//add new geoRelation Graph node to graph if one of the angles are related with a node
	public void anotherNameForSameAngle(Angle angle1, Angle angle2){
		
		
		ArrayList<GraphNode> nodes = getAngleContainingGraphNodes(angle1);
		
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			
			
			if(graphNode.getRelation().firstItem.sameItem(angle1)){
				
				GraphNode temp = new GraphNode(new GeoRelation(graphNode.getRelation().getFirstItem(), 
						graphNode.getRelation().getSecondItem(), graphNode.getRelation().relation), 
						graphNode.getPredesisors());
				
				temp.getRelation().setFirstItem(angle2);
				
				if(graphNode.isRootFact()){ temp.setRootFact(); }
				
				addNode(temp);
			}

			if(graphNode.getRelation().secondItem.sameItem(angle1)){
				
				GraphNode temp = new GraphNode(new GeoRelation(graphNode.getRelation().getFirstItem(), 
						graphNode.getRelation().getSecondItem(), graphNode.getRelation().relation), 
						graphNode.getPredesisors());
				
				temp.getRelation().setSecondItem(angle2);
				
				addNode(temp);
			}
			
		}
		
		nodes = getAngleContainingGraphNodes(angle2);
		
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			
			if(graphNode.getRelation().firstItem.sameItem(angle2)){
				
				GraphNode temp = new GraphNode(new GeoRelation(graphNode.getRelation().getFirstItem(), 
						graphNode.getRelation().getSecondItem(), graphNode.getRelation().relation), 
						graphNode.getPredesisors());
				
				temp.getRelation().setFirstItem(angle1);
				
				addNode(temp);
			}
			
			if(graphNode.getRelation().secondItem.sameItem(angle2)){
				
				GraphNode temp = new GraphNode(new GeoRelation(graphNode.getRelation().getFirstItem(), 
						graphNode.getRelation().getSecondItem(), graphNode.getRelation().relation), 
						graphNode.getPredesisors());
				
				temp.getRelation().setSecondItem(angle1);
				
				addNode(temp);
			}
			
		}
		
		

	}
	
	public ArrayList<GraphNode> getAngleContainingGraphNodes(Angle angle){
		
		ArrayList<GraphNode> returningGraphNodes = new ArrayList<GraphNode>();
			
		for (Iterator iterator = graphNodes.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			
			if(graphNode.getRelation().firstItem.sameItem(angle) || 
					graphNode.getRelation().secondItem.sameItem(angle))
			{
				returningGraphNodes.add(graphNode);
			}
		}
		
		return returningGraphNodes;
	}
	
	public void printGraph(){
		for (Iterator iterator = graphNodes.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			
			System.out.println(graphNode.getName());
		}
	}
	

	public ArrayList<GraphNode> getGraphNodes() {
		return graphNodes;
	}

	public void addInitialKnowledge(KnowledgeHolder initialGivenKnowledge) {
		
		for (Iterator iterator = initialGivenKnowledge.getFullKnowledge().iterator(); iterator.hasNext();) {
			GeoRelation relation = (GeoRelation) iterator.next();
			
			addRootNode(relation);
			//System.out.println(relation.getName() + "root");
			
		}
		
	}
	
	

	public void addRootNode(GeoRelation relation){
		
		GraphNode graphNode = new GraphNode(relation, null, true);
		
		this.graphNodes.add(graphNode);
	}
	
	public void removeSameItemParents(HashTestKnowledge hashKnowledge){
		
		for (Iterator iterator = graphNodes.iterator(); iterator.hasNext();) {
			GraphNode graphNode = (GraphNode) iterator.next();
			
			ArrayList<GraphNode> removeTemp = new ArrayList<GraphNode>();
			
			if(graphNode.getPredesisors() == null) { continue; }
			
			for (Iterator iterator2 = graphNode.getPredesisors().iterator(); iterator2.hasNext();) {
				GraphNode parentNode = (GraphNode) iterator2.next();
				
				
				GeoRelation geoRelation = parentNode.getRelation();
				
				if(geoRelation.relation == Relation.EQUALS){
					if(geoRelation.firstItem.type == GeoType.ANGLE && geoRelation.secondItem.type == GeoType.ANGLE){
						if(hashKnowledge.isSameAngleWithDifferentName(
								geoRelation.firstItem.getName() , geoRelation.secondItem.getName()))
						{
							removeTemp.add(parentNode);
							//System.out.println("ADDING PARENT " +graphNode.getName() + " : " +parentNode.getName() + " -> " + parentNode.getPredesisorNames());
							//parentNode.getPredesisors();
							graphNode.addPredesisior(parentNode.getPredesisors());
							
						}
					}
					else{ continue; }
				}
				else{ continue; }
				
			}
			
			for (Iterator iterator2 = removeTemp.iterator(); iterator2.hasNext();) {
				GraphNode removeparentNode = (GraphNode) iterator2.next();
				
				graphNode.getPredesisors().remove(removeparentNode);
			}
			
		}
	}

	
	

}
