package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0212WordSearchII {

    public List<String> findWords0(char[][] board, String[] words) {
        if (board == null || words == null) {
            return new ArrayList<>();
        }
        int rows = board.length;
        int cols = board[0].length;
        int letterCount = 26;
        Map<Character, List<Integer>> map = new HashMap<>(letterCount);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = board[i][j];
                if (!map.containsKey(ch)) {
                    map.put(ch, new ArrayList<>());
                }
                map.get(ch).add(i * cols + j);
            }
        }
        boolean[][] visited = new boolean[rows][cols];
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (hasWord(board, map, visited, words[i])) {
                answer.add(words[i]);
            }
        }
        return answer;
    }

    public boolean hasWord(char[][] board, Map<Character, List<Integer>> map, boolean[][] visited, String word) {
        char ch = word.charAt(0);
        if (!map.containsKey(ch)) {
            return false;
        }
        List<Integer> list = map.get(ch);
        int size = list.size();
        int cols = board[0].length;
        for (int i = 0; i < size; i++) {
            int location = list.get(i);
            int j = location / cols;
            int k = location % cols;
            if (dfs(board, visited, word, 1, j, k)) {
                return true;
            }
        }
        return false;
    }

    int[][] coord = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean dfs(char[][] board, boolean[][] visited,
                       String word, int index, int i, int j) {
        if (index == word.length()) {
            return true;
        }
        int rows = board.length;
        int cols = board[0].length;
        char ch = word.charAt(index);
        visited[i][j] = true;
        for (int k = 0; k < coord.length; k++) {
            int m = i + coord[k][0];
            int n = j + coord[k][1];
            if (m >= 0 && m < rows && n >= 0 && n < cols && !visited[m][n] && board[m][n] == ch
                    && dfs(board, visited, word, index + 1, m, n)) {
                visited[i][j] = false;
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }


    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0
                || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            TrieNode node = root;
            for (char ch : words[i].toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.word = words[i];
        }
        int rows = board.length;
        int cols = board[0].length;
        List<String> answer = new ArrayList<>(words.length);
        int[][] coord = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (root.children.containsKey(board[i][j])) {
                    backtrack(board, root, coord, i, j, answer);
                }
            }
        }
        return answer;
    }

    public void backtrack(char[][] board, TrieNode parent, int[][] coord, int row, int col, List<String> answer) {
        char ch = board[row][col];
        TrieNode current = parent.children.get(ch);
        if (current.word != null) {
            answer.add(current.word);
            current.word = null;
        }
        int rows = board.length;
        int cols = board[0].length;
        board[row][col] = '#';
        for (int i = 0; i < coord.length; i++) {
            int newRow = row + coord[i][0];
            int newCol = col + coord[i][1];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                    && current.children.containsKey(board[newRow][newCol])) {
                backtrack(board, current, coord, newRow, newCol, answer);
            }
        }
        board[row][col] = ch;
        if (current.children.isEmpty()) {
            parent.children.remove(ch);
        }
    }
















//    class TrieNode {
//        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
//        String word = null;
//        public TrieNode() {}
//    }
//
//    class Solution {
//        char[][] _board = null;
//        ArrayList<String> _result = new ArrayList<String>();
//
//        public List<String> findWords(char[][] board, String[] words) {
//
//            // Step 1). Construct the Trie
//            TrieNode root = new TrieNode();
//            for (String word : words) {
//                TrieNode node = root;
//
//                for (Character letter : word.toCharArray()) {
//                    if (node.children.containsKey(letter)) {
//                        node = node.children.get(letter);
//                    } else {
//                        TrieNode newNode = new TrieNode();
//                        node.children.put(letter, newNode);
//                        node = newNode;
//                    }
//                }
//                node.word = word;  // store words in Trie
//            }
//
//            this._board = board;
//            // Step 2). Backtracking starting for each cell in the board
//            for (int row = 0; row < board.length; ++row) {
//                for (int col = 0; col < board[row].length; ++col) {
//                    if (root.children.containsKey(board[row][col])) {
//                        backtracking(row, col, root);
//                    }
//                }
//            }
//
//            return this._result;
//        }
//
//        private void backtracking(int row, int col, TrieNode parent) {
//            Character letter = this._board[row][col];
//            TrieNode currNode = parent.children.get(letter);
//
//            // check if there is any match
//            if (currNode.word != null) {
//                this._result.add(currNode.word);
//                currNode.word = null;
//            }
//
//            // mark the current letter before the EXPLORATION
//            this._board[row][col] = '#';
//
//            // explore neighbor cells in around-clock directions: up, right, down, left
//            int[] rowOffset = {-1, 0, 1, 0};
//            int[] colOffset = {0, 1, 0, -1};
//            for (int i = 0; i < 4; ++i) {
//                int newRow = row + rowOffset[i];
//                int newCol = col + colOffset[i];
//                if (newRow < 0 || newRow >= this._board.length || newCol < 0
//                        || newCol >= this._board[0].length) {
//                    continue;
//                }
//                if (currNode.children.containsKey(this._board[newRow][newCol])) {
//                    backtracking(newRow, newCol, currNode);
//                }
//            }
//
//            // End of EXPLORATION, restore the original letter in the board.
//            this._board[row][col] = letter;
//
//            // Optimization: incrementally remove the leaf nodes
//            if (currNode.children.isEmpty()) {
//                parent.children.remove(letter);
//            }
//        }
//    }
}
