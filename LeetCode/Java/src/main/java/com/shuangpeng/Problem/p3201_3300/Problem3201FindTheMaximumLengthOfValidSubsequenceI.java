package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3201FindTheMaximumLengthOfValidSubsequenceI（找出有效子序列的最大长度I）
 * @date 2025/2/24 3:34 PM
 */
public class Problem3201FindTheMaximumLengthOfValidSubsequenceI {

    public int maximumLength(int[] nums) {
        int cnt00 = 0, cnt11 = 0, cnt01 = 0, cnt10 = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                cnt00++;
                cnt10 = Math.max(cnt10, cnt01 + 1);
            } else {
                cnt11++;
                cnt01 = Math.max(cnt01, cnt10 + 1);
            }
        }
        return Math.max(Math.max(cnt00, cnt11), Math.max(cnt01, cnt10));
    }
}
