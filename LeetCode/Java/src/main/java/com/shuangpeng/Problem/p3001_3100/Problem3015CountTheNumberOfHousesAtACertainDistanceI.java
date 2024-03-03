package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3015CountTheNumberOfHousesAtACertainDistanceI（按距离统计房屋对数目I）
 * @date 2024/3/2 3:48 PM
 */
public class Problem3015CountTheNumberOfHousesAtACertainDistanceI {

    public int[] countOfPairs(int n, int x, int y) {
        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }
        int[] diff = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            diff[1] += 2;
            diff[i]--;
            diff[n - i + 1]--;
        }
        if (x + 1 < y) {
            helper(diff, n, x, y);
            helper(diff, n, n - y + 1, n - x + 1);
        }
        int[] ans = new int[n];
        for (int i = 1, sum = 0; i <= n; i++) {
            sum += diff[i];
            ans[i - 1] = sum;
        }
        return ans;
    }

    private void helper(int[] diff, int n, int x, int y) {
        int k = (x + y + 1 >> 1) + 1;
        for (int i = 1; i < x; i++) {
            diff[y - i]--;
            diff[n - i + 1]++;
            diff[x - i + 1]++;
            diff[x - i + n - y + 2]--;
            diff[k - i]--;
            diff[y - i]++;
            diff[x - i + 2]++;
            diff[x - i + y - k + 2]--;
        }
        int m = x + y + 1 >> 1;
        for (int i = x; i < m; i++) {
            diff[y - i]--;
            diff[n - i + 1]++;
            diff[i - x + 1]++;
            diff[i - x + n - y + 2]--;
            int j = (2 * i - x + 1 + y) / 2 + 1;
            if (j < y) {
                diff[j - i]--;
                diff[y - i]++;
                diff[i - x + 2]++;
                diff[i - x + y - j + 2]--;
            }
        }
    }
}

class Problem3015CountTheNumberOfHousesAtACertainDistanceI0 {
    public long[] countOfPairs(int n, int x, int y) {
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        diff = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            add(1, i - 1, 1);
            add(1, n - i, 1);
            if (x + 1 >= y) {
                continue;
            }
            if (i <= x) {
                update(i, x, y, n);
            } else if (i >= y) {
                update(n + 1 - i, n + 1 - y, n + 1 - x, n);
            } else if (i < (x + y) / 2) {
                update2(i, x, y, n);
            } else if (i > (x + y + 1) / 2) {
                update2(n + 1 - i, n + 1 - y, n + 1 - x, n);
            }
        }

        long[] ans = new long[n];
        long sumD = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i + 1];
            ans[i] = sumD;
        }
        return ans;
    }

    private int[] diff;

    private void add(int l, int r, int v) {
        if (l > r) return;
        diff[l] += v;
        diff[r + 1] -= v;
    }

    private void update(int i, int x, int y, int n) {
        add(y - i, n - i, -1); // 撤销 [y,n]
        int dec = y - x - 1; // 缩短的距离
        add(y - i - dec, n - i - dec, 1);

        int j = (x + y + 1) / 2 + 1;
        add(j - i, y - 1 - i, -1); // 撤销 [j, y-1]
        add(x - i + 2, x - i + y - j + 1, 1);
    }

    private void update2(int i, int x, int y, int n) {
        add(y - i, n - i, -1); // 撤销 [y,n]
        int dec = (y - i) - (i - x + 1); // 缩短的距离
        add(y - i - dec, n - i - dec, 1);

        int j = i + (y - x + 1) / 2 + 1;
        add(j - i, y - 1 - i, -1); // 撤销 [j, y-1]
        add(i - x + 2, i - x + y - j + 1, 1);
    }
}
