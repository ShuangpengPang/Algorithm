package com.shuangpeng.Problem.p0601_0700;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem0678ValidParenthesisString {

    public boolean checkValidString0(String s) {
        int n = s.length();
        int count = 0, sCount = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            } else {
                sCount++;
            }
            if (count + sCount < 0) {
                return false;
            }
        }
        if (count > sCount) {
            return false;
        }
        count = 0;
        sCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                count++;
            } else if (c == '(') {
                count--;
            } else {
                sCount++;
            }
            if (count + sCount < 0) {
                return false;
            }
        }
        return count <= sCount;
    }

    public boolean checkValidString1(String s) {
        int n = s.length();
        int minLeft = 0, maxLeft = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minLeft++;
                maxLeft++;
            } else if (c == ')') {
                minLeft = Math.max(0, minLeft - 1);
                maxLeft--;
            } else {
                minLeft = Math.max(0, minLeft - 1);
                maxLeft++;
            }
            if (maxLeft < 0) {
                return false;
            }
        }
        return minLeft == 0;
    }

    public boolean checkValidString2(String s) {
        int n = s.length();
        Deque<Integer> starDeque = new ArrayDeque<>();
        Deque<Integer> leftDeque = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                leftDeque.offerLast(i);
            } else if (c == ')') {
                if (!leftDeque.isEmpty()) {
                    leftDeque.pollLast();
                } else if (!starDeque.isEmpty()) {
                    starDeque.pollLast();
                } else {
                    return false;
                }
            } else {
                starDeque.offerLast(i);
            }
        }
        while (!leftDeque.isEmpty()) {
            if (starDeque.isEmpty() || starDeque.peekLast() < leftDeque.peekLast()) {
                return false;
            }
            leftDeque.pollLast();
            starDeque.pollLast();
        }
        return true;
    }

    public boolean checkValidString3(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        dp[n - 1][n - 1] = s.charAt(n - 1) == '*';
        for (int i = n - 2; i >= 0; --i) {
            dp[i][i] = s.charAt(i) == '*';
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);
            dp[i][i + 1] = c1 != ')' && c2 != '(';
            for (int j = i + 2; j < n; ++j) {
                char c3 = s.charAt(j);
                if (dp[i + 1][j - 1] && c1 != ')' && c3 != '(') {
                    dp[i][j] = true;
                } else {
                    for (int k = j; k > i; --k) {
                        if (dp[i][k - 1] && dp[k][j]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    public boolean checkValidString4(String s) {
        int n = s.length();
        int minValue = 0, maxValue = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                minValue++;
                maxValue++;
            } else if (c == ')') {
                minValue--;
                maxValue--;
            } else {
                minValue--;
                maxValue++;
            }
            if (maxValue < 0) {
                return false;
            }
            minValue = Math.max(minValue, 0);
        }
        return minValue == 0;
    }

    public boolean checkValidString(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][(n >> 1) + 2];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            char c = s.charAt(i - 1);
            int k = Math.min(i, n - i);
            for (int j = 0; j <= k; ++j) {
                if (c == '(') {
                    if (j >= 1) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else if (c == ')') {
                    dp[i][j] = dp[i - 1][j + 1];
                } else {
                    dp[i][j] = dp[i - 1][j + 1] || dp[i - 1][j];
                    if (j >= 1) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][0];
    }
}
