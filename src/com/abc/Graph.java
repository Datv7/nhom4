package com.abc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
	private Map<Character, Set<Character>> adjacencyList;

	public Graph(Map<Character, Set<Character>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	public Graph() {
		// TODO Auto-generated constructor stub
		this.adjacencyList=new HashMap<Character, Set<Character>>();
	}
	public Map<Character, Set<Character>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(Map<Character, Set<Character>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	public void addVertex(Character v) {
		if(adjacencyList.containsKey(v)) return;
		adjacencyList.put(v, new HashSet<Character>());
	}
	public void addEdge(Character v1, Character v2) {
		addVertex(v1);
		addVertex(v2);
		adjacencyList.get(v1).add(v2);
	}
	public void addEdges(Character v1,Set<Character> v2) {
		addVertex(v1);
		for (Character v:v2) {
			addVertex(v);
		}
		adjacencyList.get(v1).addAll(v2);
	}
	public Set<Character> getNeighbors(Character v){
		return adjacencyList.get(v);
	}
}
