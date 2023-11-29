package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2554MaximumNumberOfIntegersToChooseFromARangeI（从一个范围内选择最多整数I）
 * @date 2023/11/29 2:21 PM
 */
public class Problem2554MaximumNumberOfIntegersToChooseFromARangeI {

    public int maxCount(int[] banned, int n, int maxSum) {
        boolean[] set = new boolean[n + 1];
        for (int b : banned) {
            if (b <= n) {
                set[b] = true;
            }
        }
        int sum = 0, cnt = 0;
        for (int i = 1; i <= n && sum <= maxSum; i++) {
            if (set[i]) {
                continue;
            }
            if (sum + i <= maxSum) {
                cnt++;
            }
            sum += i;
        }
        return cnt;
    }
}
