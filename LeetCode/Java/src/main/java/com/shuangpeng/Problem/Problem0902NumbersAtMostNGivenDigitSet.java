package com.shuangpeng.Problem;

public class Problem0902NumbersAtMostNGivenDigitSet {

    public int atMostNGivenDigitSet0(String[] digits, int n) {
        int length = digits.length;
        int[] nums = new int[length];
        for (int i = 0; i < length; ++i) {
            nums[i] = Integer.valueOf(digits[i]);
        }
        int bits = 0;
        int j = 1;
        while (n >= j) {
            bits++;
            j *= 10;
        }
        int[] dp = new int[bits];
        dp[0] = 1;
        for (int i = 1; i < bits; ++i) {
            dp[i] = dp[i - 1] * length;
        }
        int sum = 0;
        for (int i = 1; i < bits; ++i) {
            sum += dp[i];
        }
        int unit = (int) Math.pow(10, bits - 1);
        int b = bits;
        while (n > 0) {
            int d = n / unit;
            int i = 0;
            while (i < length && nums[i] < d) {
                i++;
            }
            sum += i * dp[b - 1];
            if (i == length || d < nums[i]) {
                break;
            }
            if (n < 10) {
                sum++;
            }
            n -= d * unit;
            unit /= 10;
            b--;
        }
        return sum;
    }

    public int atMostNGivenDigitSet1(String[] digits, int n) {
        String str = String.valueOf(n);
        int length = digits.length;
        int N = str.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; --i) {
            int d = str.charAt(i) - '0';
            for (String s : digits) {
                int num = Integer.parseInt(s);
                if (num < d) {
                    dp[i] += Math.pow(length, N - i - 1);
                } else if (num == d) {
                    dp[i] += dp[i + 1];
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < N; ++i) {
            dp[0] += Math.pow(length, i);
        }
        return dp[0];
    }

    public int atMostNGivenDigitSet2(String[] digits, int n) {
        String str = Integer.toString(n);
        int N = digits.length;
        int length = str.length();
        int[] dp = new int[length];
        int s = 0;
        for (int i = 0; i < length; ++i) {
            int d = str.charAt(i) - '0';
            int j = 0;
            while (j < N && d >= Integer.parseInt(digits[j])) {
                j++;
            }
            if (j > 0) {
                s = i + 1;
                dp[i] = j;
                if (d > Integer.parseInt(digits[j - 1])) {
                    break;
                }
            } else {
                int k = i;
                while (k > 0 && dp[k] == 0) {
                    dp[k - 1]--;
                    k--;
                }
                s = k + 1;
                break;
            }
        }
        while (s < length) {
            dp[s] = N;
            s++;
        }
        int factor = 1;
        int ans = 0;
        for (int i = length - 1; i >= 0; --i) {
            ans += dp[i] * factor;
            factor *= N;
        }
        return ans;
    }

    public int atMostNGivenDigitSet(String[] digits, int n) {
        int length = digits.length;
        String str = Integer.toString(n);
        int N = str.length();
        int[] dp = new int[N];
        for (int i = 0; i < N; ++i) {
            int d = str.charAt(i) - '0';
            int index = 0;
            boolean match = false;
            for (int j = 0; j < length; ++j) {
                int num = Integer.parseInt(digits[j]);
                if (d >= num) {
                    index = j + 1;
                } else {
                    break;
                }
                if (d == num) {
                    match = true;
                }
            }
            dp[i] = index;
            if (match) {
                continue;
            }
            int j = i;
            while (j > 0 && dp[j] == 0) {
                dp[j] = length;
                dp[j - 1]--;
                j--;
            }
            for (j = i + 1; j < N; ++j) {
                dp[j] = length;
            }
            break;
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            ans = ans * length + dp[i];
        }
        return ans;
    }
}
