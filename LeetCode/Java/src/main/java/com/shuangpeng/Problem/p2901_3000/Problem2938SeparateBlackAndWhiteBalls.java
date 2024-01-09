package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2938SeparateBlackAndWhiteBalls（区分黑球和白球）
 * @date 2024/1/9 9:39 AM
 */
public class Problem2938SeparateBlackAndWhiteBalls {

    public long minimumSteps(String s) {
        long ans = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '0') {
                ans += j - i++;
            }
        }
        return ans;
    }
}
