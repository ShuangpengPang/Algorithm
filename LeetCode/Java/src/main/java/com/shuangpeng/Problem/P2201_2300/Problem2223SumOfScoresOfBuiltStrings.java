package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2223SumOfScoresOfBuiltStrings（构造字符串的总得分和）
 * @date 2022/12/2 3:00 PM
 */
public class Problem2223SumOfScoresOfBuiltStrings {

    private int b1 = 101, b2 = 103, m1 = (int) 1e9 + 7, m2 = (int) 1e9 + 9;
    private int[] dp1, dp2, e1, e2;

    public long sumScores(String s) {
        int n = s.length();
        dp1 = new int[n];
        dp2 = new int[n];
        e1 = new int[n];
        e2 = new int[n];
        for (int i = 0, v1 = 0, v2 = 0, p1 = 1, p2 = 1; i < n; i++) {
            v1 = (int) (((long) v1 * b1 + s.charAt(i)) % m1);
            v2 = (int) (((long) v2 * b2 + s.charAt(i)) % m2);
            dp1[i] = v1;
            dp2[i] = v2;
            e1[i] = p1;
            e2[i] = p2;
            p1 = (int) ((long) p1 * b1 % m1);
            p2 = (int) ((long) p2 * b2 % m2);
        }
        long ans = 0L;
        for (int i = 0; i < n; i++) {
            int left = 1, right = n - i;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                int j = i + mid - 1;
                if (check(i, j)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans += left - 1;
        }
        return ans;
    }

    private boolean check(int i, int j) {
        int h1 = dp1[j - i], h2 = dp2[j - i];
        int c1 = (int) (((dp1[j] - (i == 0 ? 0 : (long) dp1[i - 1] * e1[j - i + 1])) % m1 + m1) % m1);
        int c2 = (int) (((dp2[j] - (i == 0 ? 0 : (long) dp2[i - 1] * e2[j - i + 1])) % m2 + m2) % m2);
        return h1 == c1 && h2 == c2;
    }

    // C++ Version
    vector<int> z_function(string s) {
        int n = (int)s.length();
        vector<int> z(n);
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r && z[i - l] < r - i + 1) {
                z[i] = z[i - l];
            } else {
                z[i] = max(0, r - i + 1);
                while (i + z[i] < n && s[z[i]] == s[i + z[i]]) ++z[i];
            }
            if (i + z[i] - 1 > r) l = i, r = i + z[i] - 1;
        }
        return z;
    }
}

class Solution {
    public long sumScores(String s) {
        var n = s.length();
        var z = new int[n];
        long ans = n;
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            z[i] = Math.max(Math.min(z[i - l], r - i + 1), 0);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
            ans += z[i];
        }
        return ans;
    }
}
