package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3216LexicographicallySmallestStringAfterASwap（交换后字典序最小的字符串）
 * @date 2024/7/28 7:02 PM
 */
public class Problem3216LexicographicallySmallestStringAfterASwap {

    public String getSmallestString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 1; i < n; i++) {
            if (cs[i - 1] > cs[i] && ((cs[i - 1] ^ cs[i]) & 1) == 0) {
                char t = cs[i - 1];
                cs[i - 1] = cs[i];
                cs[i] = t;
                return new String(cs);
            }
        }
        return s;
    }
}
