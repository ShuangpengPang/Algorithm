package com.shuangpeng.Problem;

public class Problem0978LongestTurbulentSubarray {

    public int maxTurbulenceSize0(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        // 0 : >
        // 1 : <
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int answer = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = 1;
                answer = Math.max(answer, dp[i][1]);
            } else if (arr[i - 1] > arr[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = 1;
                answer = Math.max(answer, dp[i][0]);
            } else {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
        }
        return answer;
    }

    public int maxTurbulenceSize1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // a: >; b : <
        int a = 1, b = 1;
        int answer = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                b = a + 1;
                a = 1;
                answer = Math.max(answer, b);
            } else if (arr[i - 1] > arr[i]) {
                a = b + 1;
                b = 1;
                answer = Math.max(answer, a);
            } else {
                a = 1;
                b = 1;
            }
        }
        return answer;
    }

    public int maxTurbulenceSize2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int left = 0, right = 1;
        int answer = 1;
        while (right < arr.length) {
            if (arr[right] == arr[right - 1]) {
                left = right;
            } else if (right - left > 1) {
                if ((arr[right - 2] > arr[right - 1] && arr[right - 1] > arr[right])
                        || (arr[right - 2] < arr[right - 1] && arr[right - 1] < arr[right])) {
                    left = right - 1;
                }
            }
            answer = Math.max(answer, right - left + 1);
            right++;
        }
        return answer;
    }

    public int maxTurbulenceSize3(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return len;
        }

        int left = 0;
        int right = 1;
        // 为 true 表示 arr[i - 1] < arr[i]
        boolean pre = false;
        int res = 1;
        while (right < len) {
            boolean current = arr[right - 1] < arr[right];
            if (current == pre) {
                left = right - 1;
            }
            if (arr[right - 1] == arr[right]) {
                left = right;
            }
            right++;
            res = Math.max(res, right - left);
            pre = current;
        }
        return res;
    }

    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int left = 0, right = 1, answer = 1;
        boolean previous = false;
        while (right < arr.length) {
            boolean current = arr[right] - arr[right - 1] < 0;
            if (previous == current) {
                left = right - 1;
            }
            if (arr[right] == arr[right - 1]) {
                left = right;
            }
            answer = Math.max(answer, right - left + 1);
            previous = current;
            right++;
        }
        return answer;
    }
}
