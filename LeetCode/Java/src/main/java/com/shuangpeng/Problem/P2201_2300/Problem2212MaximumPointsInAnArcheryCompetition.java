package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2212MaximumPointsInAnArcheryCompetition（射箭比赛中的最大得分）
 * @date 2023/11/14 9:55 AM
 */
public class Problem2212MaximumPointsInAnArcheryCompetition {

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int maxScore = 0, n = aliceArrows.length, N = 1 << n;
        int[] ans = aliceArrows, arr = new int[n];
        for (int m = N - 1; m > 0; m--) {
            int num = numArrows, score = 0;
            for (int i = 0; i < n && num >= 0; i++) {
                if (((m >> i) & 1) == 1) {
                    arr[i] = aliceArrows[i] + 1;
                    num -= arr[i];
                    score += i;
                } else {
                    arr[i] = 0;
                }
            }
            if (num >= 0 && score > maxScore) {
                maxScore = score;
                arr[0] += num;
                ans = arr.clone();
            }
        }
        return ans;
    }
}
