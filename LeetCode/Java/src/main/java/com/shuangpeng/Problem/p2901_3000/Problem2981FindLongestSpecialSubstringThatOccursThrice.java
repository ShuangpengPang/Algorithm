package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2981FindLongestSpecialSubstringThatOccursThrice（找出出现至少三次的最长特殊子字符串I）
 * @date 2024/1/12 4:40 PM
 */
public class Problem2981FindLongestSpecialSubstringThatOccursThrice {

    public int maximumLength(String s) {
        List<Integer>[] arr = new List[26];
        Arrays.setAll(arr, i -> new ArrayList<>());
        int n = s.length();
        for (int i = 0, j = 0; i < n; i = j) {
            char c = s.charAt(i);
            while (j < n && s.charAt(j) == c) {
                j++;
            }
            arr[c - 'a'].add(j - i);
        }
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(arr, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left > 1 ? left - 1 : -1;
    }

    private boolean check(List<Integer>[] arr, int len) {
        for (List<Integer> list : arr) {
            int cnt = 0;
            for (int i : list) {
                cnt += Math.max(0, i - len + 1);
            }
            if (cnt >= 3) {
                return true;
            }
        }
        return false;
    }
}
