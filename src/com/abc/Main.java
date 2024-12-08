package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Main {
	public static void main(String[] args) {
		
		Graph graph=new Graph();
		graph.addEdges('a', Set.of('b', 'c'));
		graph.addEdges('b', Set.of('a', 'c', 'd'));
		graph.addEdges('c', Set.of('a', 'b', 'd'));
		graph.addEdges('d',Set.of('b', 'c', 'e'));
		graph.addEdges('e', Set.of('d'));
		
		List<Set<Character>> bronKerbosch=BronKerbosch.findMaximalCliques(graph.getAdjacencyList());
		for(Set<Character> s:bronKerbosch) {
			System.out.println(s.toString());
		}
		System.out.println("===");
		BranchAndBound branchAndBound=new BranchAndBound();
		SimpleGraph<String, DefaultEdge> simpleGraph=graph.convertToSimpleGraph();
		branchAndBound.branchAndBound(new HashSet<>(), new HashSet<>(simpleGraph.vertexSet()), new HashSet<>(), simpleGraph);
		List<Set<Character>> brandAndBound=branchAndBound.getAllCliques();
		for(Set<Character> s:brandAndBound) {
			System.out.println(s.toString());
		}
        
	}
	
	
}
