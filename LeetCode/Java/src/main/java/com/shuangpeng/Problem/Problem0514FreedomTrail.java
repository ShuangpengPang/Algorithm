package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0514FreedomTrail {

    public int findRotateSteps0(String ring, String key) {
        int nr = ring.length(), nk = key.length();
        int[][] dp = new int[nr][nr];
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nr; j++) {
                dp[i][j] = Math.min(Math.abs(i - j), nr - Math.abs(i - j));
            }
        }
        int N = 26;
        List<Integer>[] positionList = new List[N];
        for (int i = 0; i < N; i++) {
            positionList[i] = new ArrayList<>();
        }
        for (int i = 0; i < nr; i++) {
            positionList[ring.charAt(i) - 'a'].add(i);
        }
        List<int[]> stepList = new ArrayList<>();
        stepList.add(new int[]{0, 0});
        int lastMin = 0;
        for (int i = 0; i < nk; i++) {
            List<Integer> positions = positionList[key.charAt(i) - 'a'];
            int size = positions.size();
            List<int[]> temp = new ArrayList<>(size);
            int tempMin = Integer.MAX_VALUE;
            for (int p : positions) {
                int min = Integer.MAX_VALUE;
                for (int[] s : stepList) {
                    min = Math.min(min, s[1] + dp[s[0]][p]);
                }
                if (min <= lastMin + nr) {
                    temp.add(new int[]{p, min});
                    tempMin = Math.min(tempMin, min);
                }
            }
            stepList = temp;
            lastMin = tempMin;
        }
        int step = Integer.MAX_VALUE;
        for (int[] s : stepList) {
            step = Math.min(step, s[1]);
        }
        return step + nk;
    }

    public int findRotateSteps1(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }

    public int findRotateSteps2(String ring, String key) {
        int nr = ring.length(), nk = key.length();
        int N = 26;
        List<Integer>[] pos = new List[N];
        for (int i = 0; i < N; i++) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < nr; i++) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[nk][nr];
        for (int i = 0; i < nk; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int p : pos[key.charAt(0) - 'a']) {
            dp[0][p] = Math.min(p, nr - p) + 1;
        }
        for (int i = 1; i < nk; i++) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), nr - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[nk - 1]).min().getAsInt();
    }

    public int findRotateSteps(String ring, String key) {
        int nr = ring.length(), nk = key.length();
        int N = 26;
        List<Integer>[] pos = new List[N];
        for (int i = 0; i < N; i++) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < nr; i++) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[] dp = new int[nr];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int p : pos[key.charAt(0) - 'a']) {
            dp[p] = Math.min(p, nr - p) + 1;
        }
        for (int i = 1; i < nk; i++) {
            for (int j : pos[key.charAt(i) - 'a']) {
                int temp = Integer.MAX_VALUE;
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    if (j == k) {
                        temp = dp[k] + 1;
                        break;
                    }
                    temp = Math.min(temp, dp[k] + Math.min(Math.abs(j - k), nr - Math.abs(j - k)) + 1);
                }
                dp[j] = temp;
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int p : pos[key.charAt(nk - 1) - 'a']) {
            answer = Math.min(answer, dp[p]);
        }
        return answer;
    }
}
