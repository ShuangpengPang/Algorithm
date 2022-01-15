package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0864ShortestPathToGetAllKeys {

    public int shortestPathAllKeys0(String[] grid) {
        final int INF = Integer.MAX_VALUE;
        int m = grid.length, n = grid[0].length();
        Map<Character, int[]> location = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c != '.' && c != '#') {
                    location.put(c, new int[]{i, j});
                }
            }
        }
        int keyCount = (location.size() - 1) >> 1;
        List<int[]> possibles = new ArrayList<>();
        permutation(new int[keyCount], 0, keyCount, possibles);
        int ans = INF;
        next: for (int[] possible : possibles) {
            int step = 0;
            for (int i = 0; i < keyCount; ++i) {
                char s = i == 0 ? '@' : (char) ('a' + possible[i - 1]);
                char t = (char) ('a' + possible[i]);
                int keyMask = 0;
                for (int j = 0; j < i; ++j) {
                    keyMask |= (1 << possible[j]);
                }
                int result = bfs(grid, s, t, keyMask, location);
                if (result == INF) {
                    continue next;
                }
                step += result;
                if (step >= ans) {
                    continue next;
                }
            }
            ans = step;
        }
        return ans == INF ? -1 : ans;
    }

    private int bfs(String[] grid, char source, char target, int keyMask, Map<Character, int[]> location) {
        final int INF = Integer.MAX_VALUE;
        int[] num1 = location.get(source), num2 = location.get(target);
        int sx = num1[0];
        int sy = num1[1];
        int tx = num2[0];
        int ty = num2[1];
        int m = grid.length, n = grid[0].length();
        boolean[][] seen = new boolean[m][n];
        seen[sx][sy] = true;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                int[] pair = queue.poll();
                for (int[] dir : dirs) {
                    int x = pair[0] + dir[0], y = pair[1] + dir[1];
                    if (x == tx && y == ty) {
                        return step;
                    }
                    if (x >= 0 && x < m && y >= 0 && y < n && !seen[x][y] && grid[x].charAt(y) != '#') {
                        char c = grid[x].charAt(y);
                        if (c >= 'A' && c <= 'Z' && (keyMask >> (c - 'A') & 1) == 0) {
                            continue;
                        }
                        seen[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return INF;
    }

    private void permutation(int[] nums, int used, int size, List<int[]> ans) {
        if (size == 0) {
            ans.add(nums.clone());
            return;
        }
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (((used >> i) & 1) == 0) {
                nums[size - 1] = i;
                permutation(nums, used | (1 << i), size - 1, ans);
            }
        }
    }


    class ANode {
        Node node;
        int d;

        ANode(Node node, int d) {
            this.node = node;
            this.d = d;
        }
    }

    class Node {
        char c;
        int state;

        Node(char c, int state) {
            this.c = c;
            this.state = state;
        }

        @Override
        public int hashCode() {
            return c * 256 + state;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Node)) {
                return false;
            }
            Node other = (Node) obj;
            return this.c == other.c && this.state == other.state;
        }
    }

    public int shortestPathAllKeys1(String[] grid) {
        int m = grid.length, n = grid[0].length();
        Map<Character, int[]> location = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c != '.' && c != '#') {
                    location.put(c, new int[]{i, j});
                }
            }
        }
        Map<Character, Map<Character, Integer>> graph = new HashMap<>();
        for (char c : location.keySet()) {
            graph.put(c, bfs(c, grid, location));
        }
        int targetState = (1 << (location.size() >> 1)) - 1;
        Map<Node, Integer> dis = new HashMap<>();
        PriorityQueue<ANode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.d));
        Node start = new Node('@', 0);
        pq.offer(new ANode(start, 0));
        final int INF = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            ANode aNode = pq.poll();
            Node node = aNode.node;
            int d = aNode.d;
            if (d >= dis.getOrDefault(node, INF)) {
                continue;
            }
            if (node.state == targetState) {
                return d;
            }
            dis.put(node, d);
            int state = node.state;
            for (char c : graph.get(node.c).keySet()) {
                if (Character.isUpperCase(c) && (state & (1 << (c - 'A'))) <= 0) {
                    continue;
                }
                int s = Character.isLowerCase(c) ? state | (1 << (c - 'a')) : state;
                pq.offer(new ANode(new Node(c, s), d + graph.get(node.c).get(c)));
            }
        }
        return -1;
    }

    private Map<Character, Integer> bfs(char source, String[] grid, Map<Character, int[]> location) {
        int m = grid.length, n = grid[0].length();
        Map<Character, Integer> dict = new HashMap<>();
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[] first = location.get(source);
        seen[first[0]][first[1]] = true;
        queue.offer(first);
        int step = 0;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                int[] pair = queue.poll();
                for (int[] dir : dirs) {
                    int x = pair[0] + dir[0], y = pair[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !seen[x][y]) {
                        char c = grid[x].charAt(y);
                        if (c == '#') {
                            continue;
                        }
                        seen[x][y] = true;
                        if (c != '.') {
                            dict.put(c, step);
                        } else {
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return dict;
    }



    class Info {
        int x;
        int y;
        int state;

        Info(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }

    public int shortestPathAllKeys(String[] grid) {
        int m =grid.length, n = grid[0].length();
        int startX = -1;
        int startY = -1;
        int target = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c >= 'a' && c <= 'z') {
                    target |= 1 << (c - 'a');
                } else if (c == '@') {
                    startX = i;
                    startY = j;
                }
            }
        }
        boolean[][][] seen = new boolean[m][n][target + 1];
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(startX, startY, 0));
        seen[startX][startY][0] = true;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                Info info = queue.poll();
                int x = info.x, y = info.y, state = info.state;
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        char c = grid[nx].charAt(ny);
                        if (c == '#' || (c >= 'A' && c <= 'Z' && (state & (1 << (c - 'A'))) == 0)) {
                            continue;
                        }
                        int newState = c >= 'a' && c <= 'z' ? state | (1 << (c - 'a')) : state;
                        if (seen[nx][ny][newState]) {
                            continue;
                        }
                        if (newState == target) {
                            return step;
                        }
                        seen[nx][ny][newState] = true;
                        queue.offer(new Info(nx, ny, newState));
                    }
                }
            }
        }
        return -1;
    }
}
