package com.shuangpeng.Problem.p0801_0900;

import java.util.HashSet;
import java.util.Set;

public class Problem0803BricksFallingWhenHit {

    // TLE
    public int[] hitBricks0(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < n; ++i) {
            if (grid[0][i] == 1) {
                dfs(grid, 0, i, 2);
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = grid[i][j] == 2 ? 1 : 0;
            }
        }
        int length = hits.length;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 1) {
                grid[x][y] = 0;
                for (int[] dir : dirs) {
                    int a = x + dir[0], b = y + dir[1];
                    if (a < 0 || a >= m || b < 0 || b >= n || grid[a][b] == 0) {
                        continue;
                    }
                    int c = getCount(grid, a, b, new HashSet<>());
                    ans[i] += c;
                    if (c > 0) {
                        dfs(grid, a, b, 0);
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int i, int j, int v) {
        grid[i][j] = v;
        int m = grid.length, n = grid[0].length;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || grid[x][y] == v) {
                continue;
            }
            dfs(grid, x, y, v);
        }
    }

    private int getCount(int[][] grid, int i, int j, Set<Integer> visited) {
        if (i == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        visited.add(i * n + j);
        int count = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || visited.contains(x * n + y)) {
                continue;
            }
            int c = getCount(grid, x, y, visited);
            if (c == 0) {
                return 0;
            }
            count += c;
        }
        return count;
    }

    public int[] hitBricks1(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        int[] parent = new int[total + 1];
        int[] size = new int[total + 1];
        parent[total] = total;
        for (int[] hit : hits) {
            int i = hit[0], j = hit[1];
            if (grid[i][j] == 1) {
                grid[i][j] = 2;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int p = i == 0 ? total : i * n + j;
                    parent[i * n + j] = p;
                    ++size[p];
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && !visited.contains(i * n + j)) {
                    dfs(grid, parent, size, i, j, visited);
                }
            }
        }
        int length = hits.length;
        int[] ans = new int[length];
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = length - 1; i >= 0; --i) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 2) {
                int hash = x * n + y;
                int p1 = x == 0 ? total : hash;
                parent[hash] = p1;
                ++size[p1];
                grid[x][y] = 1;
                int count = 0;
                for (int[] dir : dirs) {
                    int a = x + dir[0], b = y + dir[1];
                    if (a < 0 || a >= m || b < 0 || b >= n || grid[a][b] != 1) {
                        continue;
                    }
                    int p2 = findParent(parent, a * n + b);
                    if (p2 != total && p1 != p2) {
                        count += size[p2];
                    }
                    p1 = union(parent, size, p1, p2);
                }
                ans[i] = p1 == total ? count : 0;
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int[] parent, int[] size, int i, int j, Set<Integer> visited) {
        int m = grid.length, n = grid[0].length;
        int hash = i * n + j;
        visited.add(hash);
        int p = findParent(parent, hash);
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1 || visited.contains(x * n + y)) {
                continue;
            }
            p = union(parent, size, p, dfs(grid, parent, size, x, y, visited));
        }
        return p;
    }

    private int findParent(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : findParent(parent, parent[x]);
    }

    private int union(int[] parent, int[] size, int p1, int p2) {
        if (p1 == p2) {
            return p1;
        }
        int total = parent.length - 1;
        if (p1 == total || (p2 != total && size[p1] >= size[p2])) {
            parent[p2] = p1;
            size[p1] += size[p2];
            return p1;
        }
        parent[p1] = p2;
        size[p2] += size[p1];
        return p2;
    }

    class UnionFind {
        int[] parent;
        int[] size;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findParent(int x) {
            return parent[x] = x == parent[x] ? x : findParent(parent[x]);
        }

        void union(int x, int y) {
            int px = findParent(x), py = findParent(y);
            if (px == py) {
                return;
            }
            parent[px] = py;
            size[py] += size[px];
        }

        int getSize(int x) {
            return size[findParent(x)];
        }
    }

    private int toIndex(int i, int j, int n) {
        return i * n + j;
    }

    public int[] hitBricks2(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        int length = hits.length;
        for (int[] hit : hits) {
            int x = hit[0], y = hit[1];
            grid[x][y] = grid[x][y] == 1 ? 2 : 0;
        }
        int total = m * n;
        UnionFind unionFind = new UnionFind(total + 1);
        for (int i = 0; i < n; ++i) {
            if (grid[0][i] == 1) {
                unionFind.union(i, total);
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int index = toIndex(i, j, n);
                    if (grid[i - 1][j] == 1) {
                        unionFind.union(index, toIndex(i - 1, j, n));
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        unionFind.union(index, toIndex(i, j - 1, n));
                    }
                }
            }
        }
        int[] ans = new int[length];
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = length - 1; i >= 0; --i) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 2) {
                int index = toIndex(x, y, n);
                int origin = unionFind.getSize(total);
                if (x == 0) {
                    unionFind.union(index, total);
                }
                for (int[] dir : dirs) {
                    int a = x + dir[0], b = y + dir[1];
                    if (a < 0 || a >= m || b < 0 || b >= n || grid[a][b] != 1) {
                        continue;
                    }
                    unionFind.union(index, toIndex(a, b, n));
                }
                grid[x][y] = 1;
                ans[i] = Math.max(0, unionFind.getSize(total) - origin - 1);
            }
        }
        return ans;
    }

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        for (int[] hit : hits) {
            --grid[hit[0]][hit[1]];
        }
        for (int i = 0; i < n; ++i) {
            dfs(grid, 0, i);
        }
        int length = hits.length;
        int[] ans = new int[length];
        for (int i = length - 1; i >= 0; --i) {
            int x = hits[i][0], y = hits[i][1];
            ++grid[x][y];
            if (grid[x][y] == 1 && isStable(grid, x, y)) {
                ans[i] = dfs(grid, x, y) - 1;
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
            grid[i][j] = 2;
            final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            int count = 1;
            for (int[] dir : dirs) {
                count += dfs(grid, i + dir[0], j + dir[1]);
            }
            return count;
        }
        return 0;
    }

    private boolean isStable(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        return i == 0 || grid[i - 1][j] == 2 || (j + 1 < n && grid[i][j + 1] == 2) || (i + 1 < m && grid[i + 1][j] == 2) || (j > 0 && grid[i][j - 1] == 2);
    }

//    public static void main(String[] args) {
//        Problem0803BricksFallingWhenHit a = new Problem0803BricksFallingWhenHit();
//        int[][] bricks = {{1,0,0,0},{1,1,1,0}};
//        int[][] hits = {{1,0}};
//        int[] result =  a.hitBricks(bricks, hits);
//        int i = 1;
//    }
}
