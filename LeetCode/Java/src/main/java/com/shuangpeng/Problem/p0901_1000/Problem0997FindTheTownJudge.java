package com.shuangpeng.Problem.p0901_1000;

public class Problem0997FindTheTownJudge {

    public int findJudge0(int n, int[][] trust) {
        boolean[] hasTrust = new boolean[n];
        boolean[][] trustArray = new boolean[n][n];
        for (int[] pair : trust) {
            int x = pair[0] - 1, y = pair[1] - 1;
            hasTrust[x] = true;
            trustArray[y][x] = true;
        }
        for (int i = 0; i < n; ++i) {
            if (!hasTrust[i] && check(trustArray, i)) {
                return i + 1;
            }
        }
        return -1;
    }

    private boolean check(boolean[][] trustArray, int i) {
        int count = 0;
        int n = trustArray.length;
        for (int j = 0; j < n; ++j) {
            if (trustArray[i][j]) {
                ++count;
            }
        }
        return count == n - 1;
    }

    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];
        for (int[] pair : trust) {
            ++outDegree[pair[0]];
            ++inDegree[pair[1]];
        }
        for (int i = 1; i <= n; ++i) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
