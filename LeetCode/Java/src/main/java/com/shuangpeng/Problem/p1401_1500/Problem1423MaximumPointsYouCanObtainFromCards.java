package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1423MaximumPointsYouCanObtainFromCards（可获得的最大点数）
 * @date 2023/8/23 6:12 PM
 */
public class Problem1423MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length, m = n - k;
        int sum = 0, minSum = Integer.MAX_VALUE;
        for (int i = 0, s = 0; i < n; i++) {
            sum += cardPoints[i];
            s += cardPoints[i];
            if (i >= m) {
                s -= cardPoints[i - m];
            }
            if (i >= m - 1) {
                minSum = Math.min(minSum, s);
            }
        }
        return sum - minSum;
    }
}
