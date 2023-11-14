package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2212MaximumPointsInAnArcheryCompetition（射箭比赛中的最大得分）
 * @date 2023/11/14 9:55 AM
 */
public class Problem2212MaximumPointsInAnArcheryCompetition {

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int maxScore = 0, n = aliceArrows.length, N = 1 << n, state = 0;
        for (int m = N - 1; m > 0; m--) {
            int num = numArrows, score = 0;
            for (int i = 0; i < n && num >= 0; i++) {
                if (((m >> i) & 1) == 1) {
                    num -= aliceArrows[i] + 1;
                    score += i;
                }
            }
            if (num >= 0 && score > maxScore) {
                maxScore = score;
                state = m;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
}

class Problem2212MaximumPointsInAnArcheryCompetition0 {

    int[] ans;
    int maxScore;

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        ans = aliceArrows;
        maxScore = 0;
        dfs(aliceArrows, new int[aliceArrows.length], 0, numArrows, 0);
        return ans;
    }

    private void dfs(int[] aliceArrows, int[] arr, int index, int num, int score) {
        if (index == aliceArrows.length - 1) {
            arr[index] = num;
            if (aliceArrows[index] < num) {
                score += index;
            }
            if (score > maxScore) {
                maxScore = score;
                ans = arr.clone();
            }
            return;
        }
        if (num > aliceArrows[index]) {
            arr[index] = aliceArrows[index] + 1;
            dfs(aliceArrows, arr, index + 1, num - arr[index], score + index);
        }
        arr[index] = 0;
        dfs(aliceArrows, arr, index + 1, num, score);
    }
}
