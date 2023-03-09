package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: Problem1326MinimumNumberOfTapsToOpenToWaterAGarden（灌溉花园的最少水龙头数目）
 * @Date 2022/8/2 6:13 PM
 * @Version 1.0
 */
public class Problem1326MinimumNumberOfTapsToOpenToWaterAGarden {

    public int minTaps0(int n, int[] ranges) {
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            int s = i - ranges[i], e = i + ranges[i];
            if (stack.isEmpty()) {
                if (s <= 0) {
                    stack.add(e);
                }
            } else if (e > stack.get(stack.size() - 1) && s <= stack.get(stack.size() - 1)) {
                while ((stack.size() > 1 && stack.get(stack.size() - 2) >= s) || (stack.size() == 1 && s <= 0)) {
                    stack.remove(stack.size() - 1);
                }
                stack.add(e);
            }
        }
        if (stack.isEmpty()) {
            return -1;
        }
        while (stack.size() > 1 && stack.get(stack.size() - 2) >= n) {
            stack.remove(stack.size() - 1);
        }
        return stack.get(stack.size() - 1) >= n ? stack.size() : -1;
    }

    public int minTaps1(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        int INF = Integer.MAX_VALUE >> 1;
        Arrays.fill(prev, INF);
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i + ranges[i]);
            prev[r] = Math.min(prev[r], l);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = prev[i]; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[n] == INF ? -1 : dp[n];
    }

    public int minTaps2(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        int INF = Integer.MAX_VALUE >> 1;
        Arrays.fill(prev, INF);
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i + ranges[i]);
            prev[r] = Math.min(prev[r], l);
        }
        int l = prev[n], r = n;
        int ans = 1;
        while (l > 0) {
            int left = l;
            for (int i = r - 1; i >= l; i--) {
                left = Math.min(left, prev[i]);
            }
            if (left < l) {
                ans++;
                r = l;
                l = left;
            } else {
                break;
            }
        }
        return l <= 0 ? ans : -1;
    }

    public int minTaps3(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        int INF = Integer.MAX_VALUE >> 1;
        Arrays.fill(prev, INF);
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i +ranges[i]);
            prev[r] = Math.min(prev[r], l);
        }
        int ans = 0, last = n;
        for (int i = n, min = n; i > 0; i--) {
            min = Math.min(min, prev[i]);
            if (i == last) {
                if (min >= i) {
                    return -1;
                }
                ans++;
                last = min;
            }
        }
        return ans;
    }

    public int minTaps4(int n, int[] ranges) {
        int[] next = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]), r = Math.min(n, i + ranges[i]);
            next[l] = Math.max(next[l], r);
        }
        int ans = 0, right = 0;
        for (int i = 0, max = 0; i < n; i++) {
            max = Math.max(max, next[i]);
            if (i == right) {
                if (max <= right) {
                    return -1;
                }
                ans++;
                right = max;
            }
        }
        return ans;
    }

    public int minTaps5(int n, int[] ranges) {
        int N = 0, INF = Integer.MAX_VALUE >> 1;
        for (int r : ranges) {
            if (r << 1 > N) {
                N = r << 1;
            }
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 1;
        int ans = INF;
        for (int i = 1; i <= n; i++) {
            if (ranges[i] >= i) {
                dp[i] = 1;
            } else {
                for (int j = i - 1; j >= Math.max(0, i - N); j--) {
                    if (ranges[i] + ranges[j] >= i - j) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
            if (i + ranges[i] >= n) {
                ans = Math.min(ans, dp[i]);
            }
        }
        return ans == INF ? -1 : ans;
    }

    public int minTaps6(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            intervals[i][0] = Math.max(0, i - ranges[i]);
            intervals[i][1] = Math.min(n, i + ranges[i]);
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (dp[start] == Integer.MAX_VALUE) {
                return -1;
            }
            for (int i = start + 1; i <= end; i++) {
                dp[i] = Math.min(dp[i], dp[start] + 1);
            }
        }
        return dp[n];
    }

    public int minTaps7(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            rightMost[start] = Math.max(rightMost[start], i + ranges[i]);
        }
        int pre = 0, last = rightMost[0], ans = 1;
        for (int i = 1; i <= n && last < n; i++) {
            if (last < i) {
                return -1;
            }
            if (i > pre) {
                pre = last;
                ans++;
            }
            last = Math.max(last, rightMost[i]);
        }
        return last >= n ? ans : -1;
    }

    public int minTaps(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            rightMost[start] = Math.max(rightMost[start], i + ranges[i]);
        }
        int pre = 0, last = 0, ans = 0;
        for (int i = 0; i <= n && pre < n; i++) {
            if (last < i) {
                return -1;
            }
            last = Math.max(last, rightMost[i]);
            if (i == pre) {
                pre = last;
                ans++;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1326MinimumNumberOfTapsToOpenToWaterAGarden a = new Problem1326MinimumNumberOfTapsToOpenToWaterAGarden();
//        int[] ranges = {0, 0, 0, 0};
//        a.minTaps(ranges.length - 1, ranges);
//    }
}
