package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR073MinEatingSpeed（爱吃香蕉的狒狒）
 * @date 2024/6/30 7:49 PM
 */
public class LCR073MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int p : piles) {
            right = Math.max(right, p);
        }
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(piles, h, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] piles, int h, int k) {
        int t = 0;
        for (int i = 0, n = piles.length; i < n && t <= h; i++) {
            t += (piles[i] + k - 1) / k;
        }
        return t <= h;
    }
}
