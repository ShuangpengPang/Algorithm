package com.shuangpeng.Problem;

public class Problem0541ReverseStringII {

    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i += (k << 1)) {
            int j = Math.min(n - 1, i + k - 1);
            int l = i, r = j;
            while (l < r) {
                char t = chars[l];
                chars[l] = chars[r];
                chars[r] = t;
                l++;
                r--;
            }
        }
        return new String(chars);
    }
}
