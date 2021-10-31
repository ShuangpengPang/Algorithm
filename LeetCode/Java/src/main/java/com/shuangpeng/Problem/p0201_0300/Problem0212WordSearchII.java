package com.shuangpeng.Problem.p0201_0300;

import java.util.*;

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

    public List<String> findWords1(char[][] board, String[] words) {
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

    class Trie {
        boolean isWord;
        Trie[] tries;
        int count;

        Trie() {
            tries = new Trie[26];
            isWord = false;
            count = 0;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie trie = root;
            int length = word.length();
            for (int i = 0; i < length; ++i) {
                int j = word.charAt(i) - 'a';
                Trie[] tries = trie.tries;
                if (tries[j] == null) {
                    tries[j] = new Trie();
                    ++trie.count;
                }
                trie = tries[j];
            }
            trie.isWord = true;
        }
        int m = board.length, n = board[0].length;
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                backtrack(board, i, j, root, sb, ans);
            }
        }
        return ans;
    }

    private void backtrack(char[][] board, int i, int j, Trie root, StringBuilder sb, List<String> list) {
        int m = board.length, n = board[0].length;
        int k = board[i][j] - 'a';
        if (root.tries[k] == null) {
            return;
        }
        sb.append(board[i][j]);
        if (root.tries[k].isWord) {
            list.add(sb.toString());
            root.tries[k].isWord = false;
        }
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        char c = board[i][j];
        board[i][j] = '#';
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == '#') {
                continue;
            }
            backtrack(board, x, y, root.tries[k], sb, list);
            if (root.tries[k].count == 0) {
                root.tries[k] = null;
                --root.count;
                break;
            }
        }
        board[i][j] = c;
        sb.deleteCharAt(sb.length() - 1);
    }
}
