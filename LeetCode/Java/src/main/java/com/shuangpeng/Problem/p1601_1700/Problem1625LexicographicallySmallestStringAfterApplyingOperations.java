package com.shuangpeng.Problem.p1601_1700;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1625LexicographicallySmallestStringAfterApplyingOperations（执行操作后字典序最小的字符串）
 * @date 2023/4/18 6:04 PM
 */
public class Problem1625LexicographicallySmallestStringAfterApplyingOperations {

    public String findLexSmallestString(String s, int a, int b) {
        String ans = s;
        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(s);
        set.add(s);
        int n = s.length();
        while (!q.isEmpty()) {
            String str = q.poll();
            if (str.compareTo(ans) < 0) {
                ans = str;
            }
            char[] cs = str.toCharArray();
            for (int i = 1; i < n; i += 2) {
                cs[i] = (char) ('0' + (cs[i] - '0' + a) % 10);
            }
            String s1 = new String(cs), s2 = str.substring(n - b) + str.substring(0, n - b);
            if (set.add(s1)) {
                q.add(s1);
            }
            if (set.add(s2)) {
                q.add(s2);
            }
        }
        return ans;
    }
}
