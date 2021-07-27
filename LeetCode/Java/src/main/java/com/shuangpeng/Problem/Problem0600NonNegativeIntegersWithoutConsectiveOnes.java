package com.shuangpeng.Problem;

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

    public int findIntegers(int n) {
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

//    public static void main(String[] args) {
//        Problem0600NonNegativeIntegersWithoutConsectiveOnes a = new Problem0600NonNegativeIntegersWithoutConsectiveOnes();
//        a.findIntegers(12);
//    }

    // 11, 110, 111, 1011, 1110, 1111, 1100
}
