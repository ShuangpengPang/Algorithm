package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2982FindLongestSpecialSubstringThatOccursThriceII（找出出现至少三次的最长特殊子字符串II）
 * @date 2024/1/13 11:48 PM
 */
public class Problem2982FindLongestSpecialSubstringThatOccursThriceII {

    public int maximumLength(String s) {
        List<Integer>[] arr = new List[26];
        Arrays.setAll(arr, i -> new ArrayList<>());
        char[] cs = s.toCharArray();
        for (int i = 0, n = cs.length, cnt = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || cs[i] != cs[i + 1]) {
                arr[cs[i] - 'a'].add(cnt);
                cnt = 0;
            }
        }
        int maxLength = 0;
        for (List<Integer> list : arr) {
            if (!list.isEmpty()) {
                list.add(0);
                list.add(0);
                list.sort(Collections.reverseOrder());
                int m1 = list.get(0), m2 = list.get(1), m3 = list.get(2);
                maxLength = Math.max(maxLength, Math.max(Math.max(m1 - 2, Math.min(m1 - 1, m2)), m3));
            }
        }
        return maxLength > 0 ? maxLength : -1;
    }
}
