package com.shuangpeng.competition.双周赛.第086场双周赛;

/**
 * @Description: Problem2397MaximumRowsCoveredByColumns（被列覆盖的最多行数）
 * @Date 2022/11/1 6:12 PM
 * @Version 1.0
 */
public class Problem2397MaximumRowsCoveredByColumns {

    // 比赛时写法
    public int maximumRows0(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length, N = 1 << n;
        int ans = 0;
        for (int mask = 0; mask < N; mask++) {
            if (Integer.bitCount(mask) == numSelect) {
                int cnt = 0;
                for (int i = 0; i < m; i++) {
                    boolean covered = true;
                    for (int j = 0; j < n; j++) {
                        if (matrix[i][j] == 1 && ((mask >> j) & 1) == 0) {
                            covered = false;
                            break;
                        }
                    }
                    if (covered) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length, N = 1 << n;
        int ans = 0;
        for (int mask = (1 << numSelect) - 1; mask < N; mask = gospersHack(mask)) {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                boolean isCovered = true;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 1 && ((mask >> j) & 1) == 0) {
                        isCovered = false;
                        break;
                    }
                }
                if (isCovered) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    private int gospersHack(int x) {
        int c = x + (x & -x);
        return c | (c ^ x) >> (Integer.numberOfTrailingZeros(x) + 2);
    }
}
