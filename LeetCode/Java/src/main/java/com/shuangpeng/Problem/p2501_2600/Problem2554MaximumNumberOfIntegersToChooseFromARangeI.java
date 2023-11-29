package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2554MaximumNumberOfIntegersToChooseFromARangeI（从一个范围内选择最多整数I）
 * @date 2023/11/29 2:21 PM
 */
public class Problem2554MaximumNumberOfIntegersToChooseFromARangeI {

    public int maxCount0(int[] banned, int n, int maxSum) {
        boolean[] set = new boolean[n + 1];
        for (int b : banned) {
            if (b <= n) {
                set[b] = true;
            }
        }
        int sum = 0, cnt = 0;
        for (int i = 1; i <= n && sum <= maxSum; i++) {
            if (set[i]) {
                continue;
            }
            if (sum + i <= maxSum) {
                cnt++;
            }
            sum += i;
        }
        return cnt;
    }

    public int maxCount(int[] banned, int n, int maxSum) {
        boolean[] visited = new boolean[n + 1];
        int[] bit = new int[n + 1], cnt = new int[n + 1];
        for (int b : banned) {
            if (b > n || visited[b]) {
                continue;
            }
            visited[b] = true;
            int x = b;
            while (x <= n) {
                bit[x] += b;
                cnt[x]++;
                x += x & -x;
            }
        }
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (((mid + 1) * mid >> 1) - getResult(bit, mid) <= maxSum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1 - getResult(cnt, left - 1);
    }

    private int getResult(int[] bit, int x) {
        int sum = 0;
        while (x > 0) {
            sum += bit[x];
            x -= x & -x;
        }
        return sum;
    }
}
