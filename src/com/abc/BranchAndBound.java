package com.abc;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BranchAndBound {

	private static void branchAndBound(Map<Character, Set<Character>> adjacencyList,Set<Set<Character>> allCliques,Set<Character> currentClique, Set<Character> candidates) {
        if (candidates.isEmpty()) {
            if (!currentClique.isEmpty()) {
                // Thêm currentClique vào allCliques 
                allCliques.add(currentClique); // Dùng HashSet để tự động loại bỏ trùng lặp
            }
            return;
        }
        //Duyệt qua các đỉnh trong candidates
        for (Character v : candidates) {
            HashSet<Character> newCurrentClique = new HashSet<>(currentClique);  // Sao chép currentClique vào newCurrentClique
            newCurrentClique.add(v);  // Thêm đỉnh v vào newCurrentClique
            HashSet<Character> newCandidates = new HashSet<>(candidates);  // Sao chép candidates vào newCandidates
            newCandidates.retainAll(adjacencyList.getOrDefault(v,new HashSet<Character>()));  // Tìm giao của newCandidates với các đỉnh kề của v
            branchAndBound(adjacencyList,allCliques,newCurrentClique, newCandidates);  // Gọi đệ quy
        }
    }

	public static Set<Set<Character>> findMaximalCliques(Map<Character, Set<Character>> adjacencyList) {
		Set<Set<Character>> allCliques= new HashSet<Set<Character>>();
        allCliques.clear();  // Xóa danh sách clique trước khi tìm kiếm
        HashSet<Character> currentClique = new HashSet<>();  // Clique hiện tại
        HashSet<Character> candidates = new HashSet<>(adjacencyList.keySet());  // Danh sách các đỉnh có thể thêm vào clique
        branchAndBound(adjacencyList,allCliques,currentClique, candidates);  // Bắt đầu tìm kiếm
        return allCliques;  // Trả về danh sách các clique cực đại 
    }

	    
    
}
