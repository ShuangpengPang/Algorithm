package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1710MaximumUnitsOnATruck（卡车上的最大单元数）
 * @date 2022/11/15 10:04 AM
 */
public class Problem1710MaximumUnitsOnATruck {

    public int maximumUnits0(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int n = boxTypes.length, ans = 0;
        for (int i = 0; i < n && truckSize > 0; i++) {
            int cnt = Math.min(boxTypes[i][0], truckSize);
            ans += cnt * boxTypes[i][1];
            truckSize -= cnt;
        }
        return ans;
    }

    public int maximumUnits1(int[][] boxTypes, int truckSize) {
        int n = boxTypes.length, l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int[] b : boxTypes) {
            l = Math.min(l, b[1]);
            r = Math.max(r, b[1]);
        }
        int pl = 0, pr = n, sum = 0;
        while (l <= r) {
            int m = l + (r - l >> 1), cnt = 0, pt = pl;
            for (int i = pt; i < pr; i++) {
                if (boxTypes[i][1] >= m) {
                    int[] t = boxTypes[pt];
                    boxTypes[pt] = boxTypes[i];
                    boxTypes[i] = t;
                    pt++;
                }
            }
            for (int i = pl; i < pt; i++) {
                cnt += boxTypes[i][0];
            }
            if (sum + cnt <= truckSize) {
                r = m - 1;
                sum += cnt;
                pl = pt;
            } else {
                l = m + 1;
                pr = pt;
            }
        }
        int ans = 0;
        for (int i = 0; i < pr && truckSize > 0; i++) {
            int cnt = Math.min(boxTypes[i][0], truckSize);
            ans += cnt * boxTypes[i][1];
            truckSize -= cnt;
        }
        return ans;
    }
}
