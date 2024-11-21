package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3271HashDividedString（哈希分割字符串）
 * @date 2024/11/21 3:20 PM
 */
public class Problem3271HashDividedString {

    public String stringHash(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        char[] hash = new char[n / k];
        for (int i = 0; i < n; i += k) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += cs[j] - 'a';
            }
            hash[i / k] = (char) (sum % 26 + 'a');
        }
        return new String(hash);
    }
}
