package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0942DIStringMatch
 * @Date 2022/5/9 3:56 PM
 * @Version 1.0
 */
public class Problem0942DIStringMatch {

    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int left = 0, right = n;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'I') {
                ans[i] = left++;
            } else {
                ans[i] = right--;
            }
        }
        ans[n] = left;
        return ans;
    }
}
