package com.abc;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BranchAndBound {
    private Set<Set<Character>> allCliques;  // Danh sách các clique cực đại (Set để tránh trùng lặp)
    
    //Hàm Khởi tạo
    public BranchAndBound() {		//Dùng HashMap để lưu đồ thị
        allCliques = new HashSet<>();  // Dùng HashSet để lưu các clique cực đại
    }

    
    private void branchAndBound(Map<Character, Set<Character>> adjacencyList,HashSet<Character> currentClique, HashSet<Character> candidates) {
        if (candidates.isEmpty()) {
            if (!currentClique.isEmpty()) {
                // Thêm currentClique vào allCliques 
                allCliques.add(new HashSet<>(currentClique));  // Dùng HashSet để tự động loại bỏ trùng lặp
            }
            return;
        }
        //Duyệt qua các đỉnh trong candidates
        for (Character v : candidates) {
            HashSet<Character> newCurrentClique = new HashSet<>(currentClique);  // Sao chép currentClique vào newCurrentClique
            newCurrentClique.add(v);  // Thêm đỉnh v vào newCurrentClique
            HashSet<Character> newCandidates = new HashSet<>(candidates);  // Sao chép candidates vào newCandidates
            newCandidates.retainAll(adjacencyList.getOrDefault(v,new HashSet<Character>()));  // Tìm giao của newCandidates với các đỉnh kề của v
            branchAndBound(adjacencyList,newCurrentClique, newCandidates);  // Gọi đệ quy
        }
    }

    public Set<Set<Character>> getAllCliques() {
		return allCliques;
	}

	public void setAllCliques(Set<Set<Character>> allCliques) {
		this.allCliques = allCliques;
	}

	public Set<Set<Character>> findMaximalCliques(Map<Character, Set<Character>> adjacencyList) {
        allCliques.clear();  // Xóa danh sách clique trước khi tìm kiếm
        HashSet<Character> currentClique = new HashSet<>();  // Clique hiện tại
        HashSet<Character> candidates = new HashSet<>(adjacencyList.keySet());  // Danh sách các đỉnh có thể thêm vào clique
        branchAndBound(adjacencyList,currentClique, candidates);  // Bắt đầu tìm kiếm
        return allCliques;  // Trả về danh sách các clique cực đại 
    }

	    
    
}
