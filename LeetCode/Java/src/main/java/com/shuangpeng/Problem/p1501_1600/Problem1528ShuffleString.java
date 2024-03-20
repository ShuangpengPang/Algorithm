package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1528ShuffleString（重新排列字符串）
 * @date 2024/3/20 4:27 PM
 */
public class Problem1528ShuffleString {

    public String restoreString0(String s, int[] indices) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            int j = indices[i];
            char c = cs[i];
            while (j != i) {
                char t = cs[j];
                cs[j] = c;
                c = t;
                int k = indices[j];
                indices[j] = j;
                j = k;
            }
            cs[i] = c;
        }
        return new String(cs);
    }

    public String restoreString(String s, int[] indices) {
        int n = indices.length;
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            cs[indices[i]] = s.charAt(i);
        }
        return new String(cs);
    }
}
