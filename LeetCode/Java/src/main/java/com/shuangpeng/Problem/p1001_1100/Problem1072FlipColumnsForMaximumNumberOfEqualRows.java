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

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        StringBuilder sb1 = new StringBuilder(n), sb2 = new StringBuilder(n);
        Map<String, Integer> map = new HashMap<>(m);
        int ans = 0;
        for (int[] r : matrix) {
            sb1.setLength(0);
            sb2.setLength(0);
            for (int b : r) {
                sb1.append(b);
                sb2.append(b ^ 1);
            }
            String s1 = sb1.toString(), s2 = sb2.toString();
            int count = 0;
            if (map.containsKey(s2)) {
                count = map.get(s2) + 1;
                map.put(s2, count);
            } else {
                count = map.getOrDefault(s1, 0) + 1;
                map.put(s1, count);
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
