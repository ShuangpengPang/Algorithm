package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1513NumberOfSubstringsWithOnly1s（仅含1的子串数）
 * @date 2023/8/31 5:20 PM
 */
public class Problem1513NumberOfSubstringsWithOnly1s {

    public int numSub(String s) {
        int n = s.length(), N = (int) 1e9 + 7, ans = 0;
        for (int i = 0, p = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ans = (ans + ++p) % N;
            } else {
                p = 0;
            }
        }
        return ans;
    }
}
