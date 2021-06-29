package com.shuangpeng.Problem;

import java.util.*;

public class Problem0773SlidingPuzzle {

    class Node {
        int[][] board;
        int x;
        int y;
        int value;

        Node(int[][] board) {
            int m = board.length;
            int n = board[0].length;
            this.board = new int[m][n];
            int factor = (int) Math.pow(10, m * n - 1);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    this.board[i][j] = board[i][j];
                    value += board[i][j] * factor;
                    factor /= 10;
                    if (board[i][j] == 0) {
                        this.x = i;
                        this.y = j;
                    }
                }
            }
        }
    }

    public int slidingPuzzle0(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int target = 123450;
        Node node = new Node(board);
        if (node.value == target) {
            return 0;
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(node.value);
        int step = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        int[][] direct = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (move(queue, direct, visited, target)) {
                    return step;
                }
            }
        }
        return -1;
    }

    private boolean move(Queue<Node> queue, int[][] direct, Set<Integer> visited, int target) {
        Node node = queue.poll();
        int[][] board = node.board;
        int m = board.length;
        int n = board[0].length;
        int x = node.x;
        int y = node.y;
        for (int i = 0; i < direct.length; i++) {
            int nX = x + direct[i][0];
            int nY = y + direct[i][1];
            if (nX >= 0 && nX < m && nY >= 0 && nY < n) {
                int d = board[nX][nY];
                board[nX][nY] = 0;
                board[x][y] = d;
                Node nNode = new Node(board);
                if (nNode.value == target) {
                    return true;
                }
                if (!visited.contains(nNode.value)) {
                    queue.offer(nNode);
                    visited.add(nNode.value);
                }
                board[nX][nY] = d;
                board[x][y] = 0;
            }
        }
        return false;
    }

    public int slidingPuzzle1(int[][] board) {
        StringBuilder builder = new StringBuilder();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(Integer.toString(board[i][j]));
            }
        }
        String string = builder.toString();
        String target = "123450";
        if (string.equals(target)) {
            return 0;
        }
        int[][] direct = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        List<Integer>[] moves = new List[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> list = new ArrayList<>();
                for (int k = 0; k < direct.length; k++) {
                    int x = i + direct[k][0];
                    int y = j + direct[k][1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        list.add(x * n + y);
                    }
                }
                moves[i * n + j] = list;
            }
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(string);
        visited.add(string);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                for (String next : nextMove(s, moves)) {
                    if (next.equals(target)) {
                        return step;
                    }
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> nextMove(String s, List<Integer>[] moves) {
        int index = s.indexOf('0');
        char[] chars = s.toCharArray();
        List<Integer> move = moves[index];
        List<String> list = new ArrayList<>(move.size());
        for (int i : move) {
            char c = chars[i];
            chars[i] = '0';
            chars[index] = c;
            list.add(new String(chars));
            chars[i] = c;
            chars[index] = '0';
        }
        return list;
    }

    class AStar {
        String string;
        int f;
        int g;
        int h;

        AStar(String string, int g) {
            this.string = string;
            this.g = g;
            this.h = heuristic(string, map);
            this.f = this.g + this.h;
        }
    }

    int[][] map;

    public int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(board[i][j]);
            }
        }
        String s = builder.toString();
        String target = "123450";
        if (s.equals(target)) {
            return 0;
        }
        int[][] direct = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        List<Integer>[] moves = getMoves(m, n, direct);
        map = getMap(m, n);
        PriorityQueue<AStar> queue = new PriorityQueue<>((a, b) -> {
            if (a.f != b.f) {
                return a.f - b.f;
            }
            return b.g - a.g;
        });
        Set<String> visited = new HashSet<>();
        queue.offer(new AStar(s, 0));
        visited.add(s);
        while (!queue.isEmpty()) {
            AStar node = queue.poll();
            String string = node.string;
            for (String next : nextStrings(string, moves)) {
                if (next.equals(target)) {
                    return node.g + 1;
                }
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(new AStar(next, node.g + 1));
                }
            }
        }
        return -1;
    }

    private int[][] getMap(int m, int n) {
        int k = m * n;
        int[][] map = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                map[i][j] = Math.abs(i / n - j / n)
                        + Math.abs(i % n - j % n);
            }
        }
        return map;
    }

    private int heuristic(String s, int[][] map) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != '0') {
                sum += map[i][c - '0' - 1];
            }
        }
        return sum;
    }

    private List<Integer>[] getMoves(int m, int n, int[][] direct) {
        List<Integer>[] moves = new List[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> list = new ArrayList<>();
                for (int k = 0; k < direct.length; k++) {
                    int x = i + direct[k][0];
                    int y = j + direct[k][1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        list.add(x * n + y);
                    }
                }
                moves[i * n + j] = list;
            }
        }
        return moves;
    }

    private List<String> nextStrings(String s, List<Integer>[] moves) {
        int index = s.indexOf('0');
        char[] chars = s.toCharArray();
        List<Integer> move = moves[index];
        List<String> list = new ArrayList<>(move.size());
        for (int i : move) {
            swap(chars, index, i);
            list.add(new String(chars));
            swap(chars, index, i);
        }
        return list;
    }

    private void swap(char[] chars, int x, int y) {
        char c = chars[x];
        chars[x] = chars[y];
        chars[y] = c;
    }

//    public static void main(String[] args) {
//        Problem0773SlidingPuzzle a = new Problem0773SlidingPuzzle();
//        a.slidingPuzzle(new int[][]{{1,2,3},{4,0,5}});
//    }
}
