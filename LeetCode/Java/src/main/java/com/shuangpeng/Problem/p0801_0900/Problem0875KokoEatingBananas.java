package com.shuangpeng.Problem.p0801_0900;

/**
 * @Description: Problem0875KokoEatingBananas（爱吃香蕉的珂珂）
 * @Date 2022/6/7 10:00 AM
 * @Version 1.0
 */
public class Problem0875KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        long sum = 0;
        for (int p : piles) {
            sum += p;
        }
        int left = Math.max(1, (int) (sum / h)), right = (int) (piles.length - 1 + (sum / (h - piles.length + 1)) + 1);
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(piles, mid, h)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] piles, int num, int h) {
        long count = 0;
        for (int p : piles) {
            count += (p - 1) / num + 1;
        }
        return count <= h;
    }
}