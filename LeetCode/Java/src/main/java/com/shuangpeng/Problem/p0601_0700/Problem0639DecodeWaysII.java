package com.shuangpeng.Problem.p0601_0700;

public class Problem0639DecodeWaysII {

    public int numDecodings0(String s) {
        final int MOD = (int) 1e9 + 7;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        char firstChar = s.charAt(0);
        if (firstChar == '*') {
            dp[1] = 9;
        } else if (firstChar == '0') {
            return 0;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            char p = s.charAt(i - 2);
            char c = s.charAt(i - 1);
            if (c == '*') {
                int count = 0;
                if (p == '1') {
                    count = 9;
                } else if (p == '2') {
                    count = 6;
                } else if (p == '*') {
                    count = 15;
                }
                dp[i] = (int) (((long) dp[i - 1] * 9 + (long) dp[i - 2] * count) % MOD);
                continue;
            }
            if (c == '0') {
                if (p == '1' || p == '2') {
                    dp[i] = dp[i - 2];
                } else if (p == '*') {
                    dp[i] = (dp[i - 2] << 1) % MOD;
                } else {
                    return 0;
                }
                continue;
            }
            dp[i] = dp[i - 1];
            if (c >= '1' && c <= '6') {
                if (p == '1' || p == '2') {
                    dp[i] += dp[i - 2];
                } else if (p == '*') {
                    dp[i] += (dp[i - 2] << 1) % MOD;
                }
                dp[i] -= (dp[i] >= MOD ? MOD : 0);
                continue;
            }
            if (p == '1' || p == '*') {
                dp[i] += dp[i - 2];
            }
            dp[i] -= (dp[i] >= MOD ? MOD : 0);
        }
        return dp[n];
    }

    public int numDecodings1(String s) {
        final int M = (int) 1e9 + 7;
        int n = s.length();
        char firstChar = s.charAt(0);
        if (firstChar == '0') {
            return 0;
        }
        long first = 1, second = firstChar == '*' ? 9 : 1;
        for (int i = 1; i < n; i++) {
            char p = s.charAt(i - 1), c = s.charAt(i);
            long temp = 0;
            if (c == '*') {
                temp = second * 9;
                if (p == '*') {
                    temp += first * 15;
                } else if (p == '1') {
                    temp += first * 9;
                } else if (p == '2') {
                    temp += first * 6;
                }
            } else {
                if (c == '0') {
                    if (p == '1' || p == '2') {
                        temp = first;
                    } else if (p == '*') {
                        temp = first << 1;
                    } else {
                        return 0;
                    }
                } else if (c <= '6' && (p == '1' || p == '2')) {
                    temp = second + first;
                } else if (c <= '6' && p == '*') {
                    temp = second + (first << 1);
                } else if (c > '6' && (p == '1' || p == '*')) {
                    temp = second + first;
                } else {
                    temp = second;
                }
            }
            temp %= M;
            first = second;
            second = temp;
        }
        return (int) second;
    }

    // 0 : 1, 2, *
    // 1 ~ 6 : 1, 2, *
    // 7 ~ 9 : 1, *
    // * : 1, 2, *
    public int numDecodings(String s) {
        final int M = (int) 1e9 + 7;
        int n = s.length();
        if (s.charAt(0) == '0') {
            return 0;
        }
        long first = s.charAt(0) == '*' ? 9 : 1;
        long second = 1;
        long current = first;
        for (int i = 1; i < n; ++i) {
            char c = s.charAt(i), c1 = s.charAt(i - 1);
            if (c == '*') {
                current = 9 * first;
                if (c1 == '*') {
                    current += 15 * second;
                } else if (c1 == '1') {
                    current += 9 * second;
                } else if (c1 == '2') {
                    current += 6 * second;
                }
            } else if (c == '0') {
                if (c1 == '1' || c1 == '2') {
                    current = second;
                } else if (c1 == '*') {
                    current = second << 1;
                } else {
                    return 0;
                }
            } else if (c >= '1' && c <= '6') {
                if (c1 == '*') {
                    current = first + (second << 1);
                } else if (c1 == '1' || c1 == '2') {
                    current = first + second;
                }
            } else if (c1 == '*' || c1 == '1') {
                current = first + second;
            }
            current %= M;
            second = first;
            first = current;
        }
        return (int) current;
    }
}
