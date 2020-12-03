package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem0200NumberOfIslands {

//    public static void main(String[] args) {
//        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
//        Problem0200NumberOfIslands a = new Problem0200NumberOfIslands();
//        a.numIslands(grid);
//    }

    // 并查集
    public int numIslands0(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] root = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                if ((i == 0 || grid[i - 1][j] == '0') && (j == 0 || grid[i][j - 1] == '0')) {
                    root[i][j] = i * 1000 + j;
                    count++;
                } else if (i == 0 || grid[i - 1][j] == '0') {
                    root[i][j] = getRoot(root, i, j - 1);
                } else if (j == 0 || grid[i][j - 1] == '0') {
                    root[i][j] = getRoot(root, i - 1, j);
                } else {
                    int topRoot = getRoot(root, i - 1, j);
                    int leftRoot = getRoot(root, i, j - 1);
                    if (topRoot != leftRoot) {
                        int r = leftRoot / 1000;
                        int c = leftRoot % 1000;
                        root[r][c] = topRoot;
                        count--;
                    }
                    root[i][j] = topRoot;
                }
            }
        }
        return count;
    }

    public int getRoot(int[][] root, int i, int j) {
        while (root[i][j] != i * 1000 + j) {
            int m = root[i][j] / 1000;
            int n = root[i][j] % 1000;
            i = m;
            j = n;
        }
        return 1000 * i + j;
    }

    // 深度优先
    public int numIslands1(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        grid[i][j] = '0';
        int[][] coord = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int s = 0; s < coord.length; s++) {
            int x = i + coord[s][0];
            int y = j + coord[s][1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(grid, x, y);
            }
        }
    }

    // 广度优先
    public int numIslands2(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[][] coord = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    queue.offer(i * n + j);
                    while (!queue.isEmpty()) {
                        int value = queue.poll();
                        for (int k = 0; k < coord.length; k++) {
                            int x = value / n + coord[k][0];
                            int y = value % n + coord[k][1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                                grid[x][y] = '0';
                                queue.offer(x * n + y);
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m * n];
        int[] rank = new int[m * n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int value = i * n + j;
                    rank[value] = 1;
                    parent[value] = value;
                    int top = (i - 1) * n + j;
                    int right = i * n + j - 1;
                    if ((i == 0 || grid[i - 1][j] == '0') && (j == 0 || grid[i][j - 1] == '0')) {
                        count++;
                    } else if (i == 0 || grid[i - 1][j] == '0') {
                        union(parent, rank, value, right);
                    } else if (j == 0 || grid[i][j - 1] == '0') {
                        union(parent, rank, value, top);
                    } else {
                        int topRoot = find(parent, top);
                        int rightRoot = find(parent, right);
                        if (topRoot != rightRoot) {
                            union(parent, rank, top, right);
                            count--;
                        }
                        union(parent, rank, top, value);
                    }
                }
            }
        }
        return count;
    }

    public int find(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public void union(int[] parent, int[] rank, int i, int j) {
        int rootI = find(parent, i);
        int rootJ = find(parent, j);
        if (rootI != rootJ) {
            if (rootI < rootJ) {
                parent[rootI] = rootJ;
            } else if (rootI > rootJ) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI] += 1;
            }
        }
    }
}
