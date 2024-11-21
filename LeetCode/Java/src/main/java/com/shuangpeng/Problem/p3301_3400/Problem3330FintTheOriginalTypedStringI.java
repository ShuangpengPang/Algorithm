package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3330FintTheOriginalTypedStringI（找到初始输入字符串I）
 * @date 2024/11/21 4:46 PM
 */
public class Problem3330FintTheOriginalTypedStringI {

    public int possibleStringCount(String word) {
        char[] cs = word.toCharArray();
        int n = cs.length, ans = 1;
        for (int i = 1; i < n; i++) {
            if (cs[i] == cs[i - 1]) {
                ans++;
            }
        }
        return ans;
    }
}
