package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2496MaximumValueOfAStringInAnArray（数组中字符串的最大值）
 * @date 2023/7/4 2:36 PM
 */
public class Problem2496MaximumValueOfAStringInAnArray {

    public int maximumValue(String[] strs) {
        int ans = 0;
        for (String s : strs) {
            int n = s.length(), num = 0;
            for (int i = 0; i < n; i++) {
                int c = s.charAt(i) - '0';
                if (c >= 0 && c < 10) {
                    num = num * 10 + c;
                } else {
                    num = n;
                    break;
                }
            }
            ans = Math.max(ans, num);
        }
        return ans;
    }
}
