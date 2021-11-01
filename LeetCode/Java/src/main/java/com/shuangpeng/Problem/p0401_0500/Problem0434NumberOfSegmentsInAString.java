package com.shuangpeng.Problem.p0401_0500;

public class Problem0434NumberOfSegmentsInAString {

    public int countSegments(String s) {
        int n = s.length();
        int ans = 0;
        boolean isWord = false;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == ' ') {
                isWord = false;
            } else if (!isWord) {
                isWord = true;
                ++ans;
            }
        }
        return ans;
    }
}
