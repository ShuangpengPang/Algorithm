package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3228MaximumNumberOfOperationsToMoveOnesToTheEnd（将 1 移动到末尾的最大操作次数）
 * @date 2024/7/30 12:31 AM
 */
public class Problem3228MaximumNumberOfOperationsToMoveOnesToTheEnd {

    public int maxOperations(String s) {
        char[] cs = s.toCharArray();
        int ans = 0, cnt = 0, n = cs.length;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1') {
                cnt++;
            } else if (i > 0 && cs[i - 1] == '1') {
                ans += cnt;
            }
        }
        return ans;
    }
}
