package com.shuangpeng.Problem.p2301_2400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2399CheckDistancesBetweenSameLetters（检查相同字母间的距离）
 * @date 2023/4/9 7:23 PM
 */
public class Problem2399CheckDistancesBetweenSameLetters {

    public boolean checkDistances(String s, int[] distance) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int j = c - 'a';
            int prev = i - distance[j] - 1, next = i + distance[j] + 1;
            if ((prev < 0 || s.charAt(prev) != c) && (next >= n || s.charAt(next) != c)) {
                return false;
            }
        }
        return true;
    }
}
