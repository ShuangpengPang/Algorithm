package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0212WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
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
}
