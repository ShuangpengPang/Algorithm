package com.shuangpeng.Problem.p1001_1100;

import java.util.*;

public class Problem1036EscapeALargeMaze {

    public boolean isEscapePossible0(int[][] blocked, int[] source, int[] target) {
        int n = blocked.length;
        if (n < 2) {
            return true;
        }
        final long N = (long) 1e6;
        Set<Long> set = new HashSet<>();
        for (int[] b : blocked) {
            set.add(b[0] * N + b[1]);
        }
        return check(set, source[0], source[1], target[0], target[1]) && check(set, target[0], target[1], source[0], source[1]);
    }

    private boolean check(Set<Long> set, int sx, int sy, int tx, int ty) {
        final long N = (long) 1e6;
        final int M = set.size() * (set.size() - 1) >> 1;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        queue.offer(sx * N + sy);
        visited.add(sx * N + sy);
        int count = 1;
        while (!queue.isEmpty() && count <= M) {
            long hash = queue.poll();
            long x = hash / N, y = hash % N;
            for (int[] dir : dirs) {
                long x1 = x + dir[0], y1 = y + dir[1];
                if (x1 == tx && y1 == ty) {
                    return true;
                }
                long h = x1 * N + y1;
                if (x1 < 0 || x1 >= N || y1 < 0 || y1 >= N || visited.contains(h) || set.contains(h)) {
                    continue;
                }
                ++count;
                queue.offer(h);
                visited.add(h);
            }
        }
        return count > M;
    }

    public boolean isEscapePossible1(int[][] blocked, int[] source, int[] target) {
        int n = blocked.length;
        if (n < 2) {
            return true;
        }
        Set<Pair> blockedSet = new HashSet<>();
        for (int[] b : blocked) {
            blockedSet.add(new Pair(b[0], b[1]));
        }
        final int BLOCKED = -1, VALID = 0, FOUND = 1;
        int result = check(n, source, target, blockedSet);
        if (result == BLOCKED) {
            return false;
        } else if (result == FOUND) {
            return true;
        }
        return check(n, target, source, blockedSet) != BLOCKED;
    }

    private int check(int n, int[] source, int[] target, Set<Pair> blockedSet) {
        final int BLOCKED = -1, VALID = 0, FOUND = 1;
        final int N = (int) 1e6;
        int m = n * (n - 1) >> 1;
        int sx = source[0], sy = source[1], tx = target[0], ty = target[1];
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<Pair> visited = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(sx, sy));
        visited.add(new Pair(sx, sy));
        while (!queue.isEmpty() && m > 0) {
            Pair pair = queue.poll();
            for (int[] dir : dirs) {
                int x = pair.x + dir[0], y = pair.y + dir[1];
                if (x == tx && y == ty) {
                    return FOUND;
                }
                Pair p = new Pair(x, y);
                if (x >= 0 && x < N && y >= 0 && y < N && !visited.contains(p) && !blockedSet.contains(p)) {
                    visited.add(p);
                    queue.offer(p);
                    --m;
                }
            }
        }
        return m > 0 ? BLOCKED : VALID;
    }

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return (x << 20) | y;
        }

        @Override
        public boolean equals(Object obj) {
            Pair other = (Pair) obj;
            return x == other.x && y == other.y;
        }
    }

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        List<int[]> list = new ArrayList<>(blocked.length + 4);
        final int M = (int) 1e6;
        list.add(source);
        list.add(target);
        list.add(new int[]{0, 0});
        list.add(new int[]{M - 1, M - 1});
        for (int[] b : blocked) {
            list.add(b);
        }
        int n = list.size();
        list.sort(Comparator.comparingInt(a -> a[0]));
        List<int[]> copy = copyList(list);
        list.get(0)[0] = 0;
        for (int i = 1; i < n; ++i) {
            int curr = copy.get(i)[0], prev = copy.get(i - 1)[0];
            int[] pair = list.get(i);
            if (curr == prev) {
                pair[0] = list.get(i - 1)[0];
            } else if (curr == prev + 1) {
                pair[0] = list.get(i - 1)[0] + 1;
            } else {
                pair[0] = list.get(i - 1)[0] + 2;
            }
        }
        int minX = list.get(0)[0], maxX = list.get(n - 1)[0];
        list.sort(Comparator.comparingInt(a -> a[1]));
        copy = copyList(list);
        list.get(0)[1] = 0;
        for (int i = 1; i < n; ++i) {
            int curr = copy.get(i)[1], prev = copy.get(i - 1)[1];
            int[] pair = list.get(i);
            if (curr == prev) {
                pair[1] = list.get(i - 1)[1];
            } else if (curr == prev + 1) {
                pair[1] = list.get(i - 1)[1] + 1;
            } else {
                pair[1] = list.get(i - 1)[1] + 2;
            }
        }
        int minY = list.get(0)[1], maxY = list.get(n - 1)[1];
        int N = Math.max(maxX, maxY) + 1;
        Set<Integer> blockedSet = new HashSet<>();
        for (int[] pair : blocked) {
            blockedSet.add(pair[0] * N + pair[1]);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        queue.offer(source[0] * N + source[1]);
        visited.add(source[0] * N + source[1]);
        while (!queue.isEmpty()) {
            int hash = queue.poll();
            int x = hash / N, y = hash % N;
            for (int[] dir : dirs) {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (x1 == target[0] && y1 == target[1]) {
                    return true;
                }
                int h = x1 * N + y1;
                if (x1 >= minX && x1 <= maxX && y1 >= minY && y1 <= maxY && !blockedSet.contains(h) && !visited.contains(h)) {
                    visited.add(h);
                    queue.offer(h);
                }
            }
        }
        return false;
    }

    private List<int[]> copyList(List<int[]> list) {
        int n = list.size();
        List<int[]> ans = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            int[] pair = list.get(i);
            ans.add(new int[]{pair[0], pair[1]});
        }
        return ans;
    }

    static final int BOUNDARY = 1000000;
    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return true;
        }
        // 离散化
        TreeSet<Integer> rows = new TreeSet<Integer>();
        TreeSet<Integer> columns = new TreeSet<Integer>();
        for (int[] pos : blocked) {
            rows.add(pos[0]);
            columns.add(pos[1]);
        }
        rows.add(source[0]);
        rows.add(target[0]);
        columns.add(source[1]);
        columns.add(target[1]);

        Map<Integer, Integer> rMapping = new HashMap<Integer, Integer>();
        Map<Integer, Integer> cMapping = new HashMap<Integer, Integer>();

        int firstRow = rows.first();
        int rId = (firstRow == 0 ? 0 : 1);
        rMapping.put(firstRow, rId);
        int prevRow = firstRow;
        for (int row : rows) {
            if (row == firstRow) {
                continue;
            }
            rId += (row == prevRow + 1 ? 1 : 2);
            rMapping.put(row, rId);
            prevRow = row;
        }
        if (prevRow != BOUNDARY - 1) {
            ++rId;
        }

        int firstColumn = columns.first();
        int cId = (firstColumn == 0 ? 0 : 1);
        cMapping.put(firstColumn, cId);
        int prevColumn = firstColumn;
        for (int column : columns) {
            if (column == firstColumn) {
                continue;
            }
            cId += (column == prevColumn + 1 ? 1 : 2);
            cMapping.put(column, cId);
            prevColumn = column;
        }
        if (prevColumn != BOUNDARY - 1) {
            ++cId;
        }

        int[][] grid = new int[rId + 1][cId + 1];
        for (int[] pos : blocked) {
            int x = pos[0], y = pos[1];
            grid[rMapping.get(x)][cMapping.get(y)] = 1;
        }

        int sx = rMapping.get(source[0]), sy = cMapping.get(source[1]);
        int tx = rMapping.get(target[0]), ty = cMapping.get(target[1]);

        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{sx, sy});
        grid[sx][sy] = 1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            for (int d = 0; d < 4; ++d) {
                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                if (nx >= 0 && nx <= rId && ny >= 0 && ny <= cId && grid[nx][ny] != 1) {
                    if (nx == tx && ny == ty) {
                        return true;
                    }
                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = 1;
                }
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        Problem1036EscapeALargeMaze a = new Problem1036EscapeALargeMaze();
//        int[][] blocked = {};
//        int[] source = {100079,100063};
//        int[] target = {999948,999967};
//        a.isEscapePossible(blocked, source, target);
//        int i = 1;
//    }
}

