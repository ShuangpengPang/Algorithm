package com.shuangpeng.Problem.p2701_2800;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2744FindMaximumNumberOfStringPairs（最大字符串配对数目）
 * @date 2023/8/5 7:28 PM
 */
public class Problem2744FindMaximumNumberOfStringPairs {

    public int maximumNumberOfStringPairs(String[] words) {
        Set<String> set = new HashSet<>();
        int ans = 0;
        for (String w : words) {
            if (set.contains(reverse(w))) {
                ans++;
            } else {
                set.add(w);
            }
        }
        return ans;
    }

    private String reverse(String w) {
        char[] cs = w.toCharArray();
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
        }
        return new String(cs);
    }
}
