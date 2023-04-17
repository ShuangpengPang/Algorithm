package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1023CamelcaseMatching（驼峰式匹配）
 * @date 2023/4/14 5:43 PM
 */
public class Problem1023CamelcaseMatching {

    public List<Boolean> camelMatch0(String[] queries, String pattern) {
        int n = pattern.length();
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (String q : queries) {
            int m = q.length();
            int i = 0, j = 0;
            boolean match = true;
            while (i < m && match && j < n && m - i >= n - j) {
                char c = pattern.charAt(j);
                while (i < m && q.charAt(i) != c) {
                    if (Character.isUpperCase(q.charAt(i))) {
                        match = false;
                        break;
                    }
                    i++;
                }
                if (i < m) {
                    i++;
                    j++;
                }
            }
            if (!match || j < n) {
                ans.add(false);
                continue;
            }
            while (i < m && Character.isLowerCase(q.charAt(i))) {
                i++;
            }
            ans.add(i == m);
        }
        return ans;
    }

    public List<Boolean> camelMatch1(String[] queries, String pattern) {
        StringBuilder regex = new StringBuilder("[a-z]*");
        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            regex.append(pattern.charAt(i)).append("[a-z]*");
        }
        return Arrays.stream(queries).map(q -> q.matches(regex.toString())).collect(Collectors.toList());
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length, m = pattern.length();
        List<Boolean> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String q = queries[i];
            int len = q.length(), p = 0;
            boolean match = true;
            for (int j = 0; j < len; j++) {
                char c = q.charAt(j);
                if (p < m && pattern.charAt(p) == c) {
                    p++;
                } else if (Character.isUpperCase(c)) {
                    match = false;
                    break;
                }
            }
            ans.add(p == m && match);
        }
        return ans;
    }
}
