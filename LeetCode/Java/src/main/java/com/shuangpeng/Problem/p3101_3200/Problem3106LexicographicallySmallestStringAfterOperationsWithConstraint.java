package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3106LexicographicallySmallestStringAfterOperationsWithConstraint（满足距离约束且字典序最小的字符串）
 * @date 2024/4/8 6:15 PM
 */
public class Problem3106LexicographicallySmallestStringAfterOperationsWithConstraint {

    public String getSmallestString(String s, int k) {
        char[] cs = s.toCharArray();
        for (int n = cs.length, i = 0; i < n && k > 0; i++) {
            if (cs[i] - 'a' <= k || 'z' + 1 - cs[i] <= k) {
                k -= Math.min(cs[i] - 'a', 'z' + 1 - cs[i]);
                cs[i] = 'a';
            } else {
                cs[i] -= k;
                k = 0;
            }
        }
        return new String(cs);
    }
}
