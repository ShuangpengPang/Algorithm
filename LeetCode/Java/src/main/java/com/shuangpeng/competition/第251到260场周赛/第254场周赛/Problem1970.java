package com.shuangpeng.competition.第251到260场周赛.第254场周赛;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1970 {

    public int latestDayToCross0(int row, int col, int[][] cells) {
        int left = 0, right = row * col;
        int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(mid, row, col, cells, dirs)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int day, int row, int col, int[][] cells, int[][] dirs) {
        boolean[][] array = new boolean[row][col];
        for (int d = 0; d < day; ++d) {
            array[cells[d][0] - 1][cells[d][1] - 1] = true;
        }
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < col; ++i) {
            if (!array[0][i]) {
                queue.offer(new int[]{0, i});
                visited[0][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            for (int[] dir : dirs) {
                int r = pair[0] + dir[0];
                int c = pair[1] + dir[1];
                if (r >= 0 && r < row && c >= 0 && c < col && !array[r][c] && !visited[r][c]) {
                    if (r == row - 1) {
                        return true;
                    }
                    visited[r][c] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        return false;
    }

    private boolean dfs(boolean[][] array, int r, int c, boolean[][] visited, int[][] dirs) {
        int row = array.length, col = array[0].length;
        if (array[r][c]) {
            return false;
        }
        if (r == row - 1) {
            return true;
        }
        visited[r][c] = true;
        for (int[] dir : dirs) {
            int i = r + dir[0];
            int j = c + dir[1];
            if (i >= 0 && i < row && j >= 0 && j < col && !visited[i][j] && !array[i][j] && dfs(array, i, j, visited, dirs)) {
                visited[r][c] = false;
                return true;
            }
        }
        visited[r][c] = false;
        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int M = row * col;
        int[] parent = new int[M];
        int[] size = new int[M];
        int[] flag = new int[M];
        Arrays.fill(parent, -1);
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = M - 1; i >= 0; --i) {
            int r = cells[i][0] - 1, c = cells[i][1] - 1;
            int j = r * col + c;
            parent[j] = j;
            size[j] = 1;
            flag[j] = r == 0 ? 1 : (r == row - 1 ? 2 : 0);
            for (int[] dir : dirs) {
                int x = r + dir[0];
                int y = c + dir[1];
                int k = x * col + y;
                if (x >= 0 && x < row && y >= 0 && y < col && parent[k] != -1) {
                    union(findRoot(j, parent), findRoot(k, parent), parent, size, flag);
                    int root = findRoot(j, parent);
                    if (flag[root] == 3) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    private int findRoot(int i, int[] parent) {
        if (parent[i] == -1) {
            return -1;
        }
        while (parent[i] != i) {
            int p = parent[i];
            int g = parent[p];
            parent[i] = g;
            i = g;
        }
        return i;
    }

    private void union(int p1, int p2, int[] parent, int[] size, int[] flag) {
        if (p1 == p2) {
            return;
        }
        if (size[p1] >= size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
            flag[p1] |= flag[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
            flag[p2] |= flag[p1];
        }
    }

    //    private void print(boolean[][] array) {
//        int m = array.length, n = array[0].length;
//        for (int i = 0; i < m; ++i) {
//            for (int j = 0; j < n; ++j) {
//                if (array[i][j]) {
//                    System.out.print("" + 1 + ',');
//                } else {
//                    System.out.print("" + 0 + ',');
//                }
//            }
//            System.out.println();
//        }
//    }

//    public static void main(String[] args) {
//        Problem1970 a = new Problem1970();
////        a.latestDayToCross(13, 9, new int[][]{{12,6},{3,4},{2,9},{9,4},{9,2},{6,4},{4,4},{8,6},{4,9},{5,6},{7,5},{12,4},{11,8},{3,7},{2,6},{9,8},{3,5},{13,4},{1,3},{10,2},{8,9},{6,6},{11,7},{11,1},{13,9},{12,7},{10,7},{8,2},{1,8},{7,3},{6,5},{2,1},{10,6},{4,8},{4,2},{9,7},{6,2},{3,6},{12,2},{10,3},{10,5},{9,5},{8,8},{8,7},{3,2},{13,6},{3,1},{5,1},{2,7},{8,3},{12,5},{11,2},{6,3},{1,4},{13,3},{4,1},{9,9},{7,7},{4,3},{12,1},{2,2},{7,6},{4,6},{7,9},{7,2},{3,8},{1,6},{11,3},{11,4},{5,9},{13,8},{1,9},{10,1},{9,1},{6,1},{10,9},{12,9},{11,5},{8,1},{13,5},{9,6},{13,2},{6,8},{2,8},{5,3},{3,3},{13,1},{11,9},{9,3},{2,4},{5,2},{8,5},{13,7},{12,8},{5,5},{7,1},{7,4},{2,5},{6,9},{4,7},{5,8},{1,5},{10,8},{8,4},{1,1},{3,9},{1,2},{7,8},{1,7},{6,7},{11,6},{4,5},{5,7},{2,3},{10,4},{5,4},{12,3}});
////        a.latestDayToCross(33, 3, new int[][]{{26,1},{1,2},{30,2},{10,1},{20,1},{23,3},{9,1},{30,1},{16,1},{2,2},{23,2},{31,3},{21,1},{21,3},{15,3},{28,2},{24,2},{5,1},{33,1},{18,3},{9,2},{16,2},{21,2},{14,3},{19,2},{1,1},{20,2},{2,1},{12,3},{2,3},{25,2},{26,3},{25,3},{13,2},{19,3},{29,1},{4,2},{27,1},{3,2},{17,2},{6,3},{17,3},{31,1},{27,3},{8,2},{24,3},{29,2},{16,3},{12,1},{9,3},{6,2},{10,3},{33,2},{22,3},{22,2},{7,1},{18,1},{32,1},{14,1},{32,2},{1,3},{18,2},{11,3},{12,2},{28,1},{19,1},{24,1},{30,3},{11,2},{4,1},{4,3},{20,3},{8,1},{23,1},{7,3},{27,2},{22,1},{26,2},{15,2},{14,2},{28,3},{13,1},{5,2},{10,2},{6,1},{33,3},{15,1},{13,3},{3,3},{3,1},{31,2},{11,1},{5,3},{8,3},{32,3},{17,1},{7,2},{29,3},{25,1}});
//        a.latestDayToCross(3, 3, new int[][]{{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}});
//    }
}
