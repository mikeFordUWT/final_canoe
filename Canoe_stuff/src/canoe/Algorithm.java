package canoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithm {
	private int mySize;
	private int max;
	private Graph g;
	private static int MIN = 1;
	private static int INF = 99999;

	public Algorithm(int size) {
		mySize = size;
		if (mySize <= 400) {
			max = 2500;
		} else if (mySize > 400 && mySize <= 600) {
			max = 5000;
		} else {
			max = 10000;
		}
	}

	public int[][] randomMatrixGenerate() {
		int[][] A = new int[mySize][mySize];
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				if (i == j) {
					A[i][j] = 0;
				} else if (i > j) {
					A[i][j] = INF;
				} else {
					int upper;
					int lower;
					double dj;
					double upperDouble;
					double lowerDouble;
					if (i == 0) {
						if (j == 1) {
							lower = 1;
							dj = j;
							upperDouble = (dj / (mySize - 1) * max);
							upper = (int) upperDouble;
							A[i][j] = ThreadLocalRandom.current().nextInt(lower, upper);
						} else {
							lower = A[i][j - 1] + 1;
							dj = j;
							upperDouble = (dj / (mySize - 1) * max);
							upper = (int) upperDouble;
							A[i][j] = ThreadLocalRandom.current().nextInt(lower, upper);
						}
					} else { // i>0
						if (A[i][j - 1] == 0) {
							lower = 1;
							upper = A[i - 1][j];

							A[i][j] = ThreadLocalRandom.current().nextInt(lower, upper);
						} else {
							lower = A[i][j - 1] + 1;
							upper = A[i - 1][j];
							A[i][j] = ThreadLocalRandom.current().nextInt(lower, upper);
						}
					}
				}
			}
		}

		return A;
	}

	/**
	 * finds the min cost of taking canoes from start to finish using the costs
	 * supplied by the input matrix
	 * 
	 * @param inputMatrix
	 *            [][]
	 * @return int[][] that shows the min possible cost to reach each cell in
	 *         the matrix
	 */
	public int[][] minCost(int[][] inputMatrix) {
		int[][] toReturn = new int[inputMatrix.length][inputMatrix.length];
		for (int j = 0; j < inputMatrix.length; j++) {
			toReturn[0][j] = inputMatrix[0][j];
			toReturn[j][0] = inputMatrix[0][0];
		}
		int i;
		for (int j = 1; j < inputMatrix.length; j++) {
			for (i = 1; i < inputMatrix.length; i++) {
				if (inputMatrix[i][j] == 0) {
					toReturn[i][j] = toReturn[i - 1][j];
				} else {
					toReturn[i][j] = Math.min(toReturn[i - 1][j], (toReturn[i][j - 1] + inputMatrix[i][j]));
				}
			}
		}
		return toReturn;
	}

	/**
	 * Using the mincost matrix, determines which stations were stopped at when
	 * finding the least cost route from start to finish
	 * 
	 * @param inputMatrix
	 *            [][]
	 * @return an arrayList of int values representing stations that are
	 *         required stops
	 */
	public ArrayList<Integer> whichCanoes(int[][] inputMatrix) {
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		int j = inputMatrix.length - 1;
		toReturn.add(1);// must take canoe from station 1
		toReturn.add(j + 1);
		for (int i = inputMatrix.length - 1; i > 0; i--) {
			if (inputMatrix[i][j] != inputMatrix[i - 1][j]) {
				toReturn.add(j);
				j--;
			}
			if (j == 0) {// TODO add functionality for hitting top of matrix
				toReturn.add(j);
			}
		}
		if (toReturn.size() == 0) {
			toReturn.add(inputMatrix.length);
		}
		Collections.sort(toReturn);
		return toReturn;
	}

	/**
	 * Creates a station Graph for the given input matrix
	 * 
	 * @param inputMatrix
	 * @return a graph
	 */
	public Graph createStationGraph(int[][] inputMatrix) {
		// set up a graph with nodes as stations and weighted cost edges to
		// other stations
		Node station = new Node(1);
		Graph graph = new Graph(station);

		for (int i = 1; i < inputMatrix.length + 1; i++) {
			graph.addNode(new Node(i));
		}

		// add the nodes and edges
		for (int i = 0; i < inputMatrix.length; i++) {
			// get the station we're connecting
			station = graph.getNodes().get(i);
			for (int j = 0; j < inputMatrix.length; j++) {
				if ((inputMatrix[i][j] != INF) && (inputMatrix[i][j] != 0)) {
					// add the edges to that station
					station.addEdge(new Edge(inputMatrix[i][j], graph.getNodes().get(j)));
				}
			}
		}
		 graph.printGraph();
		return graph;
	}

	public void bForceCanoes(int[][] inputMatrix) {

		Graph graph = createStationGraph(inputMatrix);
		// generate the subsets
		int stations = inputMatrix.length;
		int[] A = new int[stations + 1];
		boolean[] B = new boolean[A.length];
		for (int i = 1; i < stations + 1; i++) {
			A[i] = i;
		}
		int[][] subsets = getSubsets(A, stations, 0, 0, B);
	}

	/**
	 * A function to return the subsets of a given array
	 * Guidance from: http://algorithms.tutorialhorizon.com/print-all-combinations-of-subset-of-size-k-from-given-array/
	 */
	private int[][] getSubsets(int[] A, int k,  int start, int curLen, boolean[] used){
		int[][] toReturn = new int[A.length][A.length];
		for(int j=0; j< A.length; j++){
			if (curLen == k){
				for(int i =0; i < A.length; i++){
					if (used[i] == true){
						toReturn[j][i] = A[i];
						System.out.print(A[i]);
					}
				}
			}
			if (start == A.length){
				break;
			}
			
			used[start] = true;
			getSubsets(A, k, start +1, curLen +1, used);
			
			used[start] = false;
			getSubsets(A, k, start +1, curLen, used);
		}
		return toReturn;
	}
	
//	public ArrayList<Node> divideAndConquer(int[][] inputMatrix){
//		Graph g = this.createStationGraph(inputMatrix);
////		ArrayList<Node> toReturn = g.BFS();
//		
//		
//		
////		return toReturn;
//		
//		
//	}
	
	
}
