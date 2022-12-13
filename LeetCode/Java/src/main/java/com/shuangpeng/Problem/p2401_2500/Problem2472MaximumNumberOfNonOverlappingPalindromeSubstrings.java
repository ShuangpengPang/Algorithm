package com.shuangpeng.Problem.p2401_2500;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2472MaximumNumberOfNonOverlappingPalindromeSubstrings（不重叠子字符串的最大数目）
 * @date 2022/12/13 5:21 PM
 */
public class Problem2472MaximumNumberOfNonOverlappingPalindromeSubstrings {

    public int maxPalindromes(String s, int k) {
        int n = s.length(), ans = 0;
        if (k == 1) {
            return n;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0, p = 0; i < n; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i + 1);
            tmp.add(i);
            char c = s.charAt(i);
            for (int j : list) {
                if (j - 1 >= p && s.charAt(j - 1) == c) {
                    if (i - j + 2 >= k) {
                        tmp.clear();
                        p = i + 1;
                        ans++;
                        break;
                    }
                    tmp.add(j - 1);
                }
            }
            list = tmp;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem2472MaximumNumberOfNonOverlappingPalindromeSubstrings a = new Problem2472MaximumNumberOfNonOverlappingPalindromeSubstrings();
//        String s = "abaccdbbd";
//        a.maxPalindromes(s, 3);
//    }
}
