package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2156FindSubstringWithGivenHashValue（查找给定哈希值的子串）
 * @date 2022/11/19 2:25 PM
 */
public class Problem2156FindSubstringWithGivenHashValue {

    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int n = s.length();
        long hash = 0L, p = 1L;
        for (int i = n - k; i < n; i++) {
            hash = (hash + (s.charAt(i) - 'a' + 1) * p) % modulo;
            p = (p * power) % modulo;
        }
        String ans = hash == hashValue ? s.substring(n - k) : "";
        for (int i = n - k - 1; i >= 0; i--) {
            hash = ((hash * power - (s.charAt(i + k) - 'a' + 1) * p + s.charAt(i) - 'a' + 1) % modulo + modulo) % modulo;
            if (hash == hashValue) {
                ans = s.substring(i, i + k);
            }
        }
        return ans;
    }
}
