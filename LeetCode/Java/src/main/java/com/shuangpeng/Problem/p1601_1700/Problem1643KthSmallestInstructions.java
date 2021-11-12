package com.shuangpeng.Problem.p1601_1700;

public class Problem1643KthSmallestInstructions {

    public String kthSmallestPath0(int[] destination, int k) {
        int vCount = destination[0];
        int count = destination[0] + destination[1];
        return recurse(count, vCount, k).toString();
    }

    private StringBuilder recurse(int count, int vCount, int k) {
        StringBuilder builder = new StringBuilder();
        int hCount = count - vCount;
        if (k == 1) {
            for (int i = 0; i < count; i++) {
                if (i < hCount) {
                    builder.append('H');
                } else{
                    builder.append('V');
                }
            }
            return builder;
        }
        int position = highPosition(count, vCount, k);
        int c = 1;
        for (int i = 0; i < vCount; i++) {
            c = c * (position - i - 1) / (i + 1);
        }
        int highCount = count - position;
        for (int i = 0; i < highCount; i++) {
            builder.append('H');
        }
        builder.append('V').append(recurse(position - 1, vCount - 1, k - c));
        return builder;
    }

    private int highPosition(int right, int vCount, int k) {
        int left = vCount;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int c = 1;
            for (int i = 0; i < vCount; i++) {
                c = c * (mid - i - 1) / (i + 1);
            }
            if (c > k) {
                right = mid - 1;
            } else if (c < k) {
                left = mid + 1;
            } else {
                return mid - 1;
            }
        }
        return left - 1;
    }

    public String kthSmallestPath1(int[] destination, int k) {
        int vCount = destination[0];
        int count = destination[0] + destination[1];
        StringBuilder builder = new StringBuilder();
        while (k > 1) {
            int left = vCount, right = count;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int m = 1;
                for (int i = 0; i < vCount; i++) {
                    m = m * (mid - i - 1) / (i + 1);
                }
                if (m < k) {
                    left = mid + 1;
                } else if (m > k) {
                    right = mid - 1;
                } else {
                    left = mid;
                    break;
                }
            }
            int position = left - 1;
            for (int i = 0; i < count - position; i++) {
                builder.append('H');
            }
            builder.append('V');
            int m = 1;
            for (int i = 0; i < vCount; i++) {
                m = m * (position - i - 1) / (i + 1);
            }
            k -= m;
            count = position - 1;
            vCount--;
        }
        for (int i = 0; i < count; i++) {
            if (i < count - vCount) {
                builder.append('H');
            } else {
                builder.append('V');
            }
        }
        return builder.toString();
    }

    public String kthSmallestPath2(int[] destination, int k) {
        int vCount = destination[0];
        int count = destination[0] + destination[1];
        int[][] dp = new int[vCount + 1][count + 1];
        for (int i = 0; i <= count; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= vCount; i++) {
            for (int j = i; j <= count; j++) {
                dp[i][j] = dp[i - 1][j - 1] * j / i;
            }
        }
        StringBuilder builder = new StringBuilder();
        while (k > 1) {
            int left = vCount, right = count;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int m = dp[vCount][mid - 1];
                if (m < k) {
                    left = mid + 1;
                } else if (m > k) {
                    right = mid - 1;
                } else {
                    left = mid;
                    break;
                }
            }
            int position = left - 1;
            for (int i = 0; i < count - position; i++) {
                builder.append('H');
            }
            builder.append('V');
            int m = dp[vCount][position - 1];
            k -= m;
            count = position - 1;
            vCount--;
        }
        for (int i = 0; i < count; i++) {
            if (i < count - vCount) {
                builder.append('H');
            } else {
                builder.append('V');
            }
        }
        return builder.toString();
    }

    public String kthSmallestPath(int[] destination, int k) {
        int v = destination[0];
        int t = destination[0] + destination[1];
        int[][] dp = new int[t + 1][v + 1];
        for (int i = 0; i <= t; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= t; i++) {
            for (int j = 1; j <= i && j <= v; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            if (k > dp[t - i][v]) {
                builder.append('V');
                k -= dp[t - i][v];
                v--;
            } else {
                builder.append('H');
            }
        }
        return builder.toString();
    }
}
