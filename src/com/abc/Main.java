package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Main {
	public static void main(String[] args) {
		Graph graph1=Graph.createGraph(1);
		System.out.println("Đồ thị rỗng\n");
		timeExecution(graph1);
		
		Graph graph2=Graph.createGraph(2);
		System.out.println("/n/nĐồ thị 1 đỉnh");
		timeExecution(graph2);
		
		Graph graph3=Graph.createGraph(3);
		System.out.println("\n\nĐồ thị 2 đỉnh không kết nối\n");
		timeExecution(graph3);
		
		Graph graph4=Graph.createGraph(4);
		System.out.println("\n\nĐồ thị 2 đỉnh kết nối\n");
		timeExecution(graph4);
		
		Graph graph5=Graph.createGraph(5);
		System.out.println("\n\nĐồ thị hoàn chỉnh\n");
		timeExecution(graph1);
		
		Graph graph6=Graph.createGraph(6);
		System.out.println("\n\nĐồ thị vòng\n");
		timeExecution(graph1);
		
		Graph graph7=Graph.genRandomGraph(20, 50);
		System.out.println("\n\nĐồ thị lớn\n");
		timeExecution(graph7);
		
        
	}
	public static void timeExecution(Graph graph) {
		graph.printGraph();
		long start = System.nanoTime();
		Map<Character, Set<Character>> adjacencyList=graph.getAdjacencyList();
		List<Set<Character>> bronKerbosch=BronKerbosch.findMaximalCliques(adjacencyList);
		System.out.println("Bron Kerbosch");
		for(Set<Character> s:bronKerbosch) {
			System.out.println(s.toString());
		}
		System.out.println("Brand And Bound");
		BranchAndBound branchAndBound=new BranchAndBound();
		branchAndBound.findMaximalCliques(adjacencyList);
		for(Set<Character> s:branchAndBound.getAllCliques()) {
			System.out.println(s.toString());
		}
		
		long duration = System.nanoTime() - start;
		System.out.println("Thời gian chạy: " + duration + " nanoseconds");
   }
	
	
}
