package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2564SubstringXORQueries（子字符串异或查询）
 * @date 2023/12/1 1:31 PM
 */
public class Problem2564SubstringXORQueries {

    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int m = s.length(), n = queries.length;
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(queries[i][0] ^ queries[i][1], k -> new ArrayList<>()).add(i);
            ans[i][0] = ans[i][1] = -1;
        }
        for (int len = 1; len < 31 && len <= m; len++) {
            int num = 0;
            for (int i = m - 1, u = 1; i >= m - len; i--, u <<= 1) {
                num += u * (s.charAt(i) - '0');
            }
            for (int i = m - len, j = m - 1; i >= 0; i--, j--) {
                if (num >= 1 << len - 1 && indexMap.containsKey(num)) {
                    for (int idx : indexMap.get(num)) {
                        ans[idx][0] = i;
                        ans[idx][1] = j;
                    }
                }
                if (i > 0) {
                    num = num + (1 << len) * (s.charAt(i - 1) - '0') >> 1;
                }
            }
        }
        if (indexMap.containsKey(0)) {
            int index = s.indexOf('0');
            for (int idx : indexMap.get(0)) {
                ans[idx][0] = ans[idx][1] = index;
            }
        }
        return ans;
    }
}
