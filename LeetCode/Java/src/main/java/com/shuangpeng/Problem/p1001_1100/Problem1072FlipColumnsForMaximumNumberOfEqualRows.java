package com.shuangpeng.Problem.p1001_1100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1072FlipColumnsForMaximumNumberOfEqualRows（按列翻转得到最大值等行数）
 * @date 2023/5/15 2:19 PM
 */
public class Problem1072FlipColumnsForMaximumNumberOfEqualRows {

    public int maxEqualRowsAfterFlips0(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>(m);
        StringBuilder sb = new StringBuilder(n);
        int ans = 0;
        for (int[] r : matrix) {
            sb.setLength(0);
            if (r[0] == 0) {
                for (int b : r) {
                    sb.append(b);
                }
            } else {
                for (int b : r) {
                    sb.append(b ^ 1);
                }
            }
            String s = sb.toString();
            int count = map.getOrDefault(s, 0) + 1;
            map.put(s, count);
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
