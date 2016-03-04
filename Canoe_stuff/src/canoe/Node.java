package canoe;

import java.util.LinkedList;

import javax.swing.tree.TreeNode;

public class Node {
	private LinkedList<Edge> myEdges; 
	private Integer myElement;
	private int totalEdges = 0;
	
	private int minDistance;


	public Node(int element){
		myElement = element;
		myEdges = new LinkedList<Edge>();
		minDistance = 99999;
	}

	public void addEdge(Edge edge){
		myEdges.add(edge);
		totalEdges++;
	}

	public int getElement(){
		return myElement;
	}

	public LinkedList<Edge> getEdges(){
		return myEdges;
	}

	public boolean isEmpty(){
		return myElement.equals(null);
	}

	public boolean containsEdge(Integer edge){
		return myEdges.contains(edge);
	}
	
	public Integer edgeAmount(){
		return totalEdges;
	}
	
	public void setMinDistance(int newMin){
		minDistance = newMin;
	}
	
	public int getMinDistance(){
		return minDistance;
	}
	
	
	
	
}
