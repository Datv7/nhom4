package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		System.out.println("aa");
//		Map<Character, Set<Character>> graph = new HashMap<>();
		
		
		Graph graph=new Graph();
		graph.addEdges('a', Set.of('b', 'c'));
		graph.addEdges('b', Set.of('a', 'c', 'd'));
		graph.addEdges('c', Set.of('a', 'b', 'd'));
		graph.addEdges('d',Set.of('b', 'c', 'e'));
		graph.addEdges('e', Set.of('d'));
		
		Bronkerbosch bronkerbosch=new Bronkerbosch();
		List<Set<Character>> cliques=bronkerbosch.findCliques(graph.getAdjacencyList());
		
		for(Set<Character> s:cliques) {
			System.out.println(s.toString());
		}
        
	}
	
	
}
