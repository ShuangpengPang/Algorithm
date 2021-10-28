package com.shuangpeng.Problem.p0001_0100;

public class Problem0091DecodeWays {

//    public static void main(String[] args) {
//        Problem0091DecodeWays a = new Problem0091DecodeWays();
//        a.numDecodings("207");
//    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        if (s.charAt(0) == '0') {
            return 0;
        }
        int pre = 1;
        int cur = 1;
        for (int i = 2; i <= length; i++) {
            int p = s.charAt(i - 2) - '0';
            int c = s.charAt(i - 1) - '0';
            int temp = cur;
            if (c == 0) {
                if (p == 0 || p > 2) {
                    return 0;
                }
                cur = pre;
            } else if (p == 1 || (p == 2 && c <= 6)) {
                cur = pre + cur;
            }
            pre = temp;
        }
        return cur;
    }

    public int numDecodings0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (length >= 2 && s.charAt(1) == '0') {
            dp[1] = 0;
        }
        for (int i = 2; i <= length; i++) {
            if (i < length && s.charAt(i) == '0') {
                continue;
            }
            int previous = s.charAt(i - 2) - '0';
            int number = s.charAt(i - 1) - '0';
            int data = previous * 10 + number;
            if (number == 0 && (previous == 0 || previous > 2)) {
                return 0;
            } else if (number == 0) {
                dp[i] = dp[i - 2];
            } else if (data <= 26) {
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[length];
    }

    int numDecodings1(String s) {
        if (s.charAt(0) == '0') return 0;
        int pre = 1, curr = 1;//dp[-1] = dp[0] = 1
        for (int i = 1; i < s.length(); i++) {
            int tmp = curr;
            if (s.charAt(i) == '0')
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') curr = pre;
                else return 0;
            else if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6'))
                curr = curr + pre;
            pre = tmp;
        }
        return curr;
    }
}
