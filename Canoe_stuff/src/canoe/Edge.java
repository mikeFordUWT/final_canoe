package canoe;


public class Edge {

	private int weight;
	private Node nextNode;
	
	//constructor
	public Edge(int w, Node n){
		weight = w;
		nextNode = n;
	}
	
	public void printEdge(){
		System.out.println("Edge weight: " + weight + "  \tDestination: " + nextNode.getElement());
	}
	
	
}