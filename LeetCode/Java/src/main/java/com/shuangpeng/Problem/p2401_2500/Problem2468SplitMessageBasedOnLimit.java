package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2468SplitMessageBasedOnLimit（根据限制分割消息）
 * @date 2022/12/12 5:22 PM
 */
public class Problem2468SplitMessageBasedOnLimit {

    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        for (int i = 1, c = 0, lastLength = 0; ; i++) {
            if (i < 10) {
                lastLength = 5;
            } else if (i < 100) {
                if (i == 10) {
                    c -= 9;
                }
                lastLength = 7;
            } else if (i < 1000) {
                if (i == 100) {
                    c -= 99;
                }
                lastLength = 9;
            } else if (i < 10000) {
                if (i == 1000) {
                    c -= 999;
                }
                lastLength = 11;
            }
            if (lastLength >= limit) {
                return new String[0];
            }
            c += limit - lastLength;
            if (c < n) {
                continue;
            }
            String[] ans = new String[i];
            int l = Integer.toString(i).length();
            for (int j = 1, k = 0; j <= i; j++) {
                int len = Math.min(n - k, limit - Integer.toString(j).length() - l - 3);
                ans[j - 1] = message.substring(k, k + len) + '<' + j + '/' + i + '>';
                k += len;
            }
            return ans;
        }
    }
}

class Problem2468SplitMessageBasedOnLimit0 {

    static final int N = (int) 1e4;
    static final int[] preSum = new int[N + 1];
    static {
        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + Integer.toString(i).length() + 3;
        }
    }

    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        for (int i = 1; ; i++) {
            int len = Integer.toString(i).length();
            if ((len << 1) + 3 >= limit) {
                return new String[0];
            }
            if (limit * i - preSum[i] - len * i < n) {
                continue;
            }
            String[] ans = new String[i];
            for (int j = 1, k = 0; j <= i; j++) {
                String tail = "<" + j + '/' + i + '>';
                if (j == i) {
                    ans[j - 1] = message.substring(k) + tail;
                } else {
                    ans[j - 1] = message.substring(k, k + limit - tail.length()) + tail;
                    k += limit - tail.length();
                }
            }
            return ans;
        }
    }
}
