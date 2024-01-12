package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2976MinimumCostToConvertStringI（转换字符串的最小成本I）
 * @date 2024/1/12 1:51 PM
 */
public class Problem2976MinimumCostToConvertStringI {

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] map = new int[26][26];
        int N = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                map[i][j] = i == j ? 0 : N;
            }
        }
        for (int i = 0, n = original.length; i < n; i++) {
            int c1 = original[i] - 'a', c2 = changed[i] - 'a';
            map[c1][c2] = Math.min(map[c1][c2], cost[i]);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        long ans = 0;
        for (int i = 0, n = source.length(); i < n; i++) {
            int c1 = source.charAt(i) - 'a', c2 = target.charAt(i) - 'a';
            if (map[c1][c2] == N) {
                return -1;
            }
            ans += map[c1][c2];
        }
        return ans;
    }
}
