package com.shuangpeng.Problem.p0501_0600;

public class Problem0600NonNegativeIntegersWithoutConsectiveOnes {

    public int findIntegers0(int n) {
        return n - consecutiveCount(n) + 1;
    }

    private int consecutiveCount(int n) {
        if (n <= 2) {
            return 0;
        }
        int count = 0;
        int k = 2;
        int i = 0;
        while ((k << 1) <= n) {
            int t = count;
            count += (k >> 1) + i;
            i = t;
            k <<= 1;
        }
        if (n >= k + (k >> 1)) {
            count += n - k - (k >> 1) + 1;
            return count + consecutiveCount((k >> 1) - 1);
        }
        return count + consecutiveCount(n - k);
    }

    public int findIntegers1(int n) {
        if (n <= 2) {
            return n + 1;
        }
        if (n == 3) {
            return 3;
        }
        int b = 1;
        int c = 0;
        while (n >= b) {
            c++;
            b <<= 1;
        }
        int[] dp = new int[c + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i <= c; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int answer = 0;
        boolean flag = false;
        int i = c;
        for (; i > 0; i--) {
            boolean isOne = (n & (1 << (i - 1))) > 0;
            if (isOne) {
                answer += dp[i - 1];
            }
            if (flag && isOne) {
                break;
            }
            flag = isOne;
        }
        if (i == 0 && ((n & 3) != 3)) {
            answer++;
        }
        return answer;
    }

    public int findIntegers2(int n) {
        if (n <= 2) {
            return n + 1;
        }
        int b = 0;
        while (n >= (1 << b)) {
            b++;
        }
        int[] dp = new int[b + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i <= b; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int i = b - 1;
        int count = 0, previousBit = 0;
        while (i >= 0) {
            if ((n & (1 << i)) > 0) {
                count += dp[i];
                if (previousBit == 1) {
                    count--;
                    break;
                }
                previousBit = 1;
            } else {
                previousBit = 0;
            }
            i--;
        }
        return count + 1;
    }

    public int findIntegers3(int n) {
        if (n == 1) {
            return 2;
        }
        int bits = 32 - Integer.numberOfLeadingZeros(n);
        int[] dp = new int[bits];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < bits; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int ans = 0;
        int preBit = 33;
        int i = bits - 1;
        for (; i >= 0; --i) {
            if ((n & (1 << i)) != 0) {
                ans += dp[i];
                if (preBit == i + 1) {
                    break;
                }
                preBit = i;
            }
        }
        if (((1 << preBit) == (((n ^ (n - 1)) + 1) >> 1)) && ((n & (1 << (preBit + 1))) == 0)) {
            ans++;
        }
        return ans;
    }

    public int findIntegers(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }

        return res;
    }

//    public static void main(String[] args) {
//        Problem0600NonNegativeIntegersWithoutConsectiveOnes a = new Problem0600NonNegativeIntegersWithoutConsectiveOnes();
//        a.findIntegers(12);
//    }

    // 11, 110, 111, 1011, 1110, 1111, 1100
}
