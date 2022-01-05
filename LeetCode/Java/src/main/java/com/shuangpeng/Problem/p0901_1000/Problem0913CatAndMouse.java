package com.shuangpeng.Problem.p0901_1000;

import java.util.*;

public class Problem0913CatAndMouse {

    public int catMouseGame0(int[][] graph) {
        int n = graph.length;
        final int DRAW = 0, MOUSE = 1, CAT = 2;
        int[][][] color = new int[n][n][3];
        int[][][] degree = new int[n][n][3];
        for (int m = 0; m < n; ++m) {
            for (int c = 0; c < n; ++c) {
                degree[m][c][MOUSE] = graph[m].length;
                for (int i : graph[c]) {
                    if (i != 0) {
                        degree[m][c][CAT]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int c = 0; c < n; ++c) {
            for (int t = 1; t <= 2; ++t) {
                color[0][c][t] = MOUSE;
                queue.offer(new int[]{0, c, t, MOUSE});
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int t = 1; t <= 2; ++t) {
                color[i][i][t] = CAT;
                queue.offer(new int[]{i, i, t, CAT});
            }
        }
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int d = node[3];
            List<int[]> nodes = parents(node, graph);
            for (int[] parent : nodes) {
                int m1 = parent[0], c1 = parent[1], t1 = parent[2];
                if (color[m1][c1][t1] == DRAW) {
                    if (t1 == d) {
                        color[m1][c1][t1] = d;
                        queue.offer(new int[]{m1, c1, t1, d});
                    } else {
                        degree[m1][c1][t1]--;
                        if (degree[m1][c1][t1] == 0) {
                            color[m1][c1][t1] = d;
                            queue.offer(new int[]{m1, c1, t1, d});
                        }
                    }
                }
            }
        }
        return color[1][2][1];
    }

    private List<int[]> parents(int[] node, int[][] graph) {
        int m = node[0], c = node[1], t = node[2];
        List<int[]> ans = new ArrayList<>();
        if (t == 1) {
            for (int i : graph[c]) {
                if (i != 0) {
                    ans.add(new int[]{m, i, 3 - t});
                }
            }
        } else {
            for (int i : graph[m]) {
                ans.add(new int[]{i, c, 3 - t});
            }
        }
        return ans;
    }

    public int catMouseGame1(int[][] graph) {
        return dfs(graph, 52, new HashMap<>(), new HashSet<>());
    }

    private int dfs(int[][] graph, int state, Map<Integer, Integer> memo, Set<Integer> visited) {
        int result = memo.getOrDefault(state, -1);
        if (result != -1) {
            return result;
        }
        final int M = 50;
        int turn = state >= M * M ? 1 : 0;
        int m = state / M % M;
        int c = state % M;
        if (m == 0) {
            return 1;
        }
        if (m == c) {
            return 2;
        }
        visited.add(state);
        int[] neighbor = turn == 0 ? graph[m] : graph[c];
        int target = turn == 0 ? 1 : 2;
        boolean draw = false;
        boolean win = false;
        boolean circle = false;
        for (int n : neighbor) {
            if (turn == 1 && n == 0) {
                continue;
            }
            int nextState = turn == 0 ? n * M + c + M * M : m * M + n;
            if (visited.contains(nextState)) {
                circle = true;
                continue;
            }
            int ans = dfs(graph, nextState, memo, visited);
            if (ans == target) {
                win = true;
                break;
            } else if (ans == 0) {
                draw = true;
            }
        }
        visited.remove(state);
        int ans = win ? target : (draw || circle ? 0 : 3 - target);
        memo.put(state, ans);
        return ans;
    }

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] dp = new int[n][n][n << 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getResult(graph, dp, 1, 2, 0);
    }

    private int getResult(int[][] graph, int[][][] dp, int mouse, int cat, int turns) {
        final int MOUSE_WIN = 1, CAT_WIN = 2, DRAW = 0;
        int n = graph.length;
        if (turns == n << 1) {
            return DRAW;
        }
        final int MOUSE_MOVE = 0, CAT_MOVE = 1;
        if (dp[mouse][cat][turns] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turns] = MOUSE_WIN;
            } else if (mouse == cat) {
                dp[mouse][cat][turns] = CAT_WIN;
            } else {
                int move = turns & 1;
                int target = move == MOUSE_MOVE ? MOUSE_WIN : CAT_WIN;
                int ans = move == MOUSE_MOVE ? CAT_WIN : MOUSE_WIN;
                int[] neighbors = graph[move == MOUSE_MOVE ? mouse : cat];
                for (int neighbor : neighbors) {
                    int nextMouse = move == MOUSE_MOVE ? neighbor : mouse;
                    int nextCat = move == CAT_MOVE ? neighbor : cat;
                    if (move == CAT_MOVE && nextCat == 0) {
                        continue;
                    }
                    int result = getResult(graph, dp, nextMouse, nextCat, turns + 1);
                    if (result == target) {
                        ans = target;
                        break;
                    } else if (result == DRAW) {
                        ans = DRAW;
                    }
                }
                dp[mouse][cat][turns] = ans;
            }
        }
        return dp[mouse][cat][turns];
    }

//    public static void main(String[] args) {
//        Problem0913CatAndMouse a = new Problem0913CatAndMouse();
////        int[][] graph = {{2,6},{2,4,5,6},{0,1,3,5,6},{2},{1,5,6},{1,2,4},{0,1,2,4}};
////        int[][] graph = {{5,6},{3,4},{6},{1,4,5},{1,3,5},{0,3,4,6},{0,2,5}};
////        int[][] graph = {{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
//        int[][] graph = {{1,3},{0},{3},{0,2}};
//        int ans = a.catMouseGame(graph);
//        int i = 1;
//    }
}
