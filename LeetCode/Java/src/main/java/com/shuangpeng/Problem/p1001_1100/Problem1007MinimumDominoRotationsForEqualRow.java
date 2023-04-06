package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1007MinimumDominoRotationsForEqualRow（行相等的最少多米诺旋转）
 * @date 2023/4/6 4:10 PM
 */
public class Problem1007MinimumDominoRotationsForEqualRow {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = calculate(tops, bottoms, tops[0]);
        ans = Math.min(ans, calculate(bottoms, tops, tops[0]));
        if (tops[0] != bottoms[0]) {
            ans = Math.min(ans, calculate(tops, bottoms, bottoms[0]));
            ans = Math.min(ans, calculate(bottoms, tops, bottoms[0]));
        }
        return ans <= tops.length ? ans : -1;
    }

    private int calculate(int[] t, int[] b, int target) {
        int n = t.length, N = n + 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (t[i] != target && b[i] != target) {
                return N;
            }
            if (t[i] != target) {
                ans++;
            }
        }
        return ans;
    }
}

class Problem1007MinimumDominoRotationsForEqualRow0 {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = check(tops, bottoms, tops[0]);
        if (tops[0] != bottoms[0]) {
            ans = Math.min(ans, check(tops, bottoms, bottoms[0]));
        }
        return ans == tops.length + 1 ? -1 : ans;
    }

    private int check(int[] t, int[] b, int target) {
        int n = t.length, N = n + 1;
        int c1 = 0, c2 = 0;
        for (int i = 0; i < n; i++) {
            if (t[i] != target && b[i] != target) {
                return N;
            }
            if (t[i] != target) {
                c1++;
            }
            if (b[i] != target) {
                c2++;
            }
        }
        return Math.min(c1, c2);
    }
}

class Problem1007MinimumDominoRotationsForEqualRow1 {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;
        int[] cnt1 = new int[7], cnt2 = new int[7], total = new int[7];
        Arrays.fill(total, n);
        for (int i = 0; i < n; i++) {
            int x = tops[i], y = bottoms[i];
            if (x == y) {
                total[x]--;
            } else {
                cnt1[x]++;
                cnt2[y]++;
            }
        }
        int ans = n + 1;
        for (int i = 0; i < 7; i++) {
            if (cnt1[i] + cnt2[i] == total[i]) {
                ans = Math.min(ans, Math.min(cnt1[i], cnt2[i]));
            }
        }
        return ans == n + 1 ? -1 : ans;
    }
}

class Problem1007MinimumDominoRotationsForEqualRow2 {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length, a = tops[0], b = bottoms[0];
        int[][] cnt = new int[2][2];
        int[] total = new int[2], nums = new int[2], t = {a, b};
        total[0] = total[1] = n;
        for (int i = 0; i < n; i++) {
            int x = tops[i], y = bottoms[i];
            if (x != a && x != b && y != a && y != b) {
                return -1;
            }
            if (x == y) {
                if (x == a) {
                    total[0]--;
                } else {
                    total[1]--;
                }
            } else {
                nums[0] = x;
                nums[1] = y;
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (nums[j] == t[k]) {
                            cnt[j][k]++;
                        }
                    }
                }
            }
        }
        int ans = n + 1;
        if (cnt[0][0] + cnt[1][0] == total[0]) {
            ans = Math.min(cnt[0][0], cnt[1][0]);
        }
        if (cnt[0][1] + cnt[1][1] == total[1]) {
            ans = Math.min(ans, Math.min(cnt[0][1], cnt[1][1]));
        }
        return ans == n + 1 ? -1 : ans;
    }
}
