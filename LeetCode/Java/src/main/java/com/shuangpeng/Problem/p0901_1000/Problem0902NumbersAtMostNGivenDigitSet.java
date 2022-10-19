package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description:（最大为N的数字组合）
 * @Date 2022/10/20 12:32 AM
 **/
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

    public int atMostNGivenDigitSet3(String[] digits, int n) {
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

    public int atMostNGivenDigitSet4(String[] digits, int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int m = cs.length, len = digits.length;
        int[] arr = new int[m];
        arr[0] = 1;
        for (int i = 1; i < m; i++) {
            arr[i] = len * arr[i - 1];
        }
        int[] preCount = new int[10];
        for (int i = 1, j = 0; i < 10; i++) {
            while (j < len && i > digits[j].charAt(0) - '0') {
                j++;
            }
            preCount[i] = j;
        }
        int ans = 0;
        for (int i = 1; i < m; i++) {
            ans += arr[i];
        }
        int cnt = 1;
        for (int i = 0; i < m; i++) {
            int j = preCount[cs[i] - '0'];
            ans += j * arr[m - i - 1];
            if (j == len || cs[i] != digits[j].charAt(0)) {
                cnt = 0;
                break;
            }
        }
        return ans + cnt;
    }

    public int atMostNGivenDigitSet(String[] digits, int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int m = cs.length, len = digits.length;
        int[] preSum = new int[10];
        for (int i = 0, j = 0; i < 10; i++) {
            while (j < len && digits[j].charAt(0) - '0' < i) {
                j++;
            }
            preSum[i] = j;
        }
        int a = 0, b = 1;
        for (int i = 0; i < m; i++) {
            int j = preSum[cs[i] - '0'];
            a = (a + (i == 0 ? 0 : 1)) * len + b * j;
            b = b == 0 || j == len || digits[j].charAt(0) != cs[i] ? 0 : 1;
        }
        return a + b;
    }
}
