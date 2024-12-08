package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Graph {
	private Map<Character, Set<Character>> adjacencyList;
	private List<Set<Character>> maximalCliques=new ArrayList<Set<Character>>();
	public Graph(Map<Character, Set<Character>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	public Graph() {
		// TODO Auto-generated constructor stub
		this.adjacencyList=new HashMap<Character, Set<Character>>();
	}
	
	public List<Set<Character>> getMaximalCliques() {
		return maximalCliques;
	}
	public void setMaximalCliques(List<Set<Character>> maximalCliques) {
		this.maximalCliques = maximalCliques;
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
	public  SimpleGraph<String, DefaultEdge> convertToSimpleGraph() {

		Map<Character, Set<Character>> graphMap=this.adjacencyList;
        SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (Map.Entry<Character, Set<Character>> entry : graphMap.entrySet()) {

            String vertex = entry.getKey().toString();
            graph.addVertex(vertex);

            for (Character neighbor : entry.getValue()) {
                String neighborVertex = neighbor.toString();
                graph.addVertex(neighborVertex);  
                graph.addEdge(vertex, neighborVertex);  
            }
        }

        return graph;
    }
	
	
}
