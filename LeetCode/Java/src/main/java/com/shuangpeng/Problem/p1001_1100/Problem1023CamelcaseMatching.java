package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1023CamelcaseMatching（驼峰式匹配）
 * @date 2023/4/14 5:43 PM
 */
public class Problem1023CamelcaseMatching {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
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
}
