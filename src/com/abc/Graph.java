package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
		adjacencyList.putIfAbsent(v, new HashSet<Character>());
	}
	public void addEdge(Character v1, Character v2) {
		addVertex(v1);
		addVertex(v2);
		adjacencyList.get(v1).add(v2);
		adjacencyList.get(v2).add(v1);
	}
	public void addEdges(Character v1,Set<Character> v2) {
		addVertex(v1);
		for (Character v:v2) {
			addVertex(v);
			adjacencyList.get(v).add(v1);
		}
		adjacencyList.get(v1).addAll(v2);
	}
	public void printGraph() {
        for (Map.Entry<Character, Set<Character>> entry : this.adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
	public Set<Character> getNeibors(Character c){
		return adjacencyList.get(c);
	}
	public static Graph genRandomGraph(int vertices, int edges) {
		Random random=new Random();
		Graph graph=new Graph();
		if (edges > vertices * (vertices - 1) / 2) {
            throw new RuntimeException("Số cạnh vượt quá giới hạn cho phép.");
        }

        for (int i = 0; i < vertices; i++) {
        	Character c=(char)('a' + i);
        	graph.addVertex(c);
        }
        int currentEdges = 0;

        while (currentEdges < edges) {
            char vertex1 = (char) ('a' + random.nextInt(vertices));
            char vertex2 = (char) ('a' + random.nextInt(vertices));

            if (vertex1 != vertex2 && !graph.getNeibors(vertex1).contains(vertex2)) {
                graph.addEdge(vertex1, vertex2);
                currentEdges++;
            }
        }
        return graph;
    }
	
	public static Graph createGraph(int type) {
		Graph graph=new Graph();

        switch (type) {
            case 1: // Đồ thị rỗng
            	break;
            case 2: // Đồ thị 1 đỉnh
                graph.addVertex('a');
                break;

            case 3: // Đồ thị 2 đỉnh không kết nối
            	graph.addVertex('a');
            	graph.addVertex('b');
                break;

            case 4: // Đồ thị 2 đỉnh kết nối
                graph.addEdge('a', 'b');
                break;

            case 5: // Đồ thị hoàn chỉnh
            	graph.addEdges('a', Set.of( 'b',  'c'));
            	graph.addEdges('b', Set.of( 'a',  'c'));
            	graph.addEdges('c', Set.of( 'a',  'b'));
                break;

            case 6: // Đồ thị vòng
            	graph.addEdges('a', Set.of('b', 'e'));
            	graph.addEdges('b', Set.of('a', 'c'));
            	graph.addEdges('c', Set.of('b', 'd'));
            	graph.addEdges('d', Set.of('c', 'e'));
            	graph.addEdges('e', Set.of('d', 'a'));
                break;

            default:
                throw new IllegalArgumentException("Loại đồ thị không hợp lệ");
        }

        return graph;
    }
	
}
