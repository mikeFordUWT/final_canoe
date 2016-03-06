package canoe;

import java.util.ArrayList;
import java.util.Arrays;

public class runner {
	private static int SIZE =4;

	public static void main(String[] args) {
		
		int[][] mat = {{   0,     4,    11, 15},
					   {9999,     0,     6, 12},
					   {9999, 99999,     0,  8},
					   {9999, 99999, 99999,  0}};
		Algorithm alg = new Algorithm(SIZE);
		int[][] randomM = alg.randomMatrixGenerate();
		if(SIZE < 20){
			printMatrix(mat);
			int[][] path = alg.minCost(mat);
			printMatrix(path);
//			printFirst(path);
			
			ArrayList<Integer> canoes = alg.whichCanoes(path);
			System.out.println(canoes.toString());
		} else {
			int[][] path = alg.minCost(mat);
			

			
			ArrayList<Integer> canoes = alg.whichCanoes(path);
			System.out.println(canoes.toString());
		}
		
		
		alg.createStationGraph(mat);
		Graph g  = alg.createStationGraph(mat);
		System.out.println();
		printMatrix(mat);
//		int[] minDist = 
		g.Dijkstra(g.getFirst(), mat);
		System.out.println(g.getNodes().getLast().getMinDistance());
//		System.out.println(Arrays.toString(minDist));
	}

	
	private static void printFirst(int[][] inputMatrix){
		System.out.print("[");
		for(int i = 0; i <inputMatrix.length; i++){
			System.out.print(inputMatrix[0][i]+", ");
		}
		System.out.println("]");
		
	}
	
	private static void printMatrix(int[][] inputMatrix){
		int width = inputMatrix.length;
		System.out.println(inputMatrix.length + "x" + inputMatrix.length);
		for(int i =0; i<width; i++){
			System.out.print("[");
			for(int j = 0; j < width; j++){
				System.out.print(String.format("%7d", inputMatrix[i][j]));
				if(j < inputMatrix[i].length - 1) System.out.print(", ");
			}
			System.out.println("]");
		}
		System.out.println();
	}
}
