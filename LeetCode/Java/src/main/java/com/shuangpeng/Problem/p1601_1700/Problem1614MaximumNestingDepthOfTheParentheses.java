package com.shuangpeng.Problem.p1601_1700;

public class Problem1614MaximumNestingDepthOfTheParentheses {

    public int maxDepth(String s) {
        int n = s.length();
        int ans = 0, count = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++count;
                ans = Math.max(ans, count);
            } else if (c == ')') {
                --count;
            }
        }
        return ans;
    }
}
