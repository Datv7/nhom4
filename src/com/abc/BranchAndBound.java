package com.abc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class BranchAndBound {
	private int maxCliqueSize = 0; // Kích thước lớn nhất của bè cực đại
    private Set<String> maxClique = new HashSet<>(); // Bè cực đại lớn nhất
    private List<Set<String>> allCliques = new ArrayList<>();

    public void branchAndBound(Set<String> currentClique, Set<String> candidates, Set<String> excluded, SimpleGraph<String, DefaultEdge> graph) {
        if (candidates.isEmpty() && excluded.isEmpty()) {
            allCliques.add(new HashSet<>(currentClique));
            if (currentClique.size() > maxCliqueSize) {
                maxCliqueSize = currentClique.size();
                maxClique = new HashSet<>(currentClique);
            }
            return;
        }

        // Chọn pivot tối ưu để giảm nhánh
        String pivot = candidates.stream()
                .max(Comparator.comparingInt(v -> getNeighbors(v, graph).size()))
                .orElse(null);

        if (pivot == null) return;

        Set<String> neighbors = getNeighbors(pivot, graph);
        Set<String> nonNeighbors = new HashSet<>(candidates);
        nonNeighbors.removeAll(neighbors);

        // Sử dụng bản sao của nonNeighbors để tránh lỗi đồng thời
        List<String> toExplore = new ArrayList<>(nonNeighbors);
        for (String vertex : toExplore) {
            Set<String> newClique = new HashSet<>(currentClique);
            newClique.add(vertex);

            Set<String> newCandidates = new HashSet<>(candidates);
            newCandidates.retainAll(getNeighbors(vertex, graph));

            Set<String> newExcluded = new HashSet<>(excluded);
            newExcluded.retainAll(getNeighbors(vertex, graph));

            branchAndBound(newClique, newCandidates, newExcluded, graph);
            candidates.remove(vertex);
            excluded.add(vertex);
        }
    }
    private Set<String> getNeighbors(String vertex, SimpleGraph<String, DefaultEdge> graph) {
        Set<String> neighbors = new HashSet<>();
        for (DefaultEdge edge : graph.edgesOf(vertex)) {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            neighbors.add(source.equals(vertex) ? target : source);
        }
        return neighbors;
    }
	public List<Set<Character>> getAllCliques() {
		return changeStringToCharacter(allCliques);
	}
    private List<Set<Character>> changeStringToCharacter(List<Set<String>> allCliques) {
    	List<Set<Character>> maximalCliques=new ArrayList<Set<Character>>();

        for (Set<String> stringSet : allCliques) {
            Set<Character> charSet = new HashSet<>();

            for (String str : stringSet) {
                if (!str.isEmpty()) {
                    charSet.add(str.charAt(0)); 
                }
            }

            maximalCliques.add(charSet);
        }

		return maximalCliques;
    }
}
