package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

public class Problem0391PerfectRectangle {

    public boolean isRectangleCover0(int[][] rectangles) {
        int n = rectangles.length;
        Set<Long>[] sets = new Set[4];
        for (int i = 0; i < 4; ++i) {
            sets[i] = new HashSet<>();
        }
        long area = 0;
        for (int i = 0; i < n; ++i) {
            int x1 = rectangles[i][0], y1 = rectangles[i][1];
            int x2 = rectangles[i][2], y2 = rectangles[i][3];
            area += (long) Math.abs(x1 - x2) * Math.abs(y1 - y2);
            int[][] pairs = {{x1, y2}, {x2, y2}, {x1, y1}, {x2, y1}};
            for (int j = 0; j < 4; ++j) {
                long value = hash(pairs[j]);
                if (sets[j].contains(value)) {
                    return false;
                }
                if (!check(sets, j, value)) {
                    sets[j].add(value);
                }
            }
        }
        for (int i = 0; i < 4; ++i) {
            if (sets[i].size() != 1) {
                return false;
            }
        }
        int[] pair1 = toPair(sets[1].toArray(new Long[0])[0]), pair2 = toPair(sets[2].toArray(new Long[0])[0]);
        return area == (long) Math.abs(pair1[0] - pair2[0]) * Math.abs(pair1[1] - pair2[1]);
    }

    private boolean check(Set<Long>[] sets, int idx, long value) {
        for (int i = 0; i < 4; ++i) {
            if (i == idx) {
                continue;
            }
            if (sets[i].contains(value)) {
                sets[i].remove(value);
                return true;
            }
        }
        return false;
    }

    private long hash(int[] pair) {
        long M = (long) 1e5;
        return ((long) pair[0] + M) * (2 * M + 10) + pair[1] + M;
    }

    private int[] toPair(long value) {
        long M = (long) 1e5;
        int y = (int) (value % (2 * M + 10) - M);
        int x = (int) (value / (2 * M + 10) - M);
        return new int[]{x, y};
    }

    // 参考： https://leetcode-cn.com/problems/perfect-rectangle/solution/gong-shui-san-xie-chang-gui-sao-miao-xia-p4q4/
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        int[][] lines = new int[n << 1][4];
        for (int i = 0; i < n; ++i) {
            int[] rectangle = rectangles[i];
            lines[i << 1] = new int[]{rectangle[0], rectangle[1], rectangle[3], 1};
            lines[(i << 1) + 1] = new int[]{rectangle[2], rectangle[1], rectangle[3], -1};
        }
        Arrays.sort(lines, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] > b[0] ? 1 : -1;
            }
            return a[1] > b[1] ? 1 : (a[1] < b[1] ? -1 : 0);
        });
        n <<= 1;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int l = 0, r = 0; l < n; l = r) {
            left.clear();
            right.clear();
            while (r < n && lines[r][0] == lines[l][0]) {
                int y1 = lines[r][1], y2 = lines[r][2], flag = lines[r][3];
                if (flag == 1 && !checkAndAdd(y1, y2, left)) {
                    return false;
                } else if (flag == -1 && !checkAndAdd(y1, y2, right)) {
                    return false;
                }
                ++r;
            }
            if (l > 0 && r < n) {
                if (left.size() != right.size()) {
                    return false;
                }
                int size = left.size();
                for (int i = 0; i < size; ++i) {
                    if (!left.get(i).equals(right.get(i))) {
                        return false;
                    }
                }
            } else if (left.size() + right.size() != 2) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAndAdd(int y1, int y2, List<Integer> list) {
        if (list.isEmpty()) {
            list.add(y1);
            list.add(y2);
        } else if (y1 < list.get(list.size() - 1)) {
            return false;
        } else if (y1 == list.get(list.size() - 1)) {
            list.set(list.size() - 1, y2);
        } else {
            list.add(y1);
            list.add(y2);
        }
        return true;
    }

//    public static void main(String[] args) {
//        try (FileReader fileReader = new FileReader("/Users/shuangpeng/Downloads/test.json")) {
//            JsonReader reader = new JsonReader(fileReader);
//            JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();
//            int m = jsonArray.size();
//            int n = jsonArray.get(0).getAsJsonArray().size();
//            int[][] grid = new int[m][n];
//            for (int i = 0; i < m; ++i) {
//                JsonArray array = jsonArray.get(i).getAsJsonArray();
//                for (int j = 0; j < n; ++j) {
//                    grid[i][j] = array.get(j).getAsInt();
//                }
//            }
//            Problem0391PerfectRectangle a = new Problem0391PerfectRectangle();
//            a.isRectangleCover(grid);
//        } catch (Exception e) {
//            int i = 1;
//        }
//    }
}
