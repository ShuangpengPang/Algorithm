package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1467ProbabilityOfATwoBoxesHavingTheSameNumberOfDistinctBalls（两个盒子中球的颜色数相同的概率）
 * @Date 2022/8/18 6:16 PM
 * @Version 1.0
 */
public class Problem1467ProbabilityOfATwoBoxesHavingTheSameNumberOfDistinctBalls {

    private static long[][] c = new long[49][25];

    static {
        c[0][0] = 1;
        for (int i = 1; i <= 48; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= Math.min(i, 24); j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
    }

    private long cnt;

    public double getProbability(int[] balls) {
        int sum = 0;
        for (int b : balls) {
            sum += b;
        }
        cnt = 0;
        dfs(balls, 0, sum >> 1, balls.length, 0, 1);
        return (double) cnt / c[sum][sum >> 1];
    }

    private void dfs(int[] balls, int pos, int remain, int t1, int t2, long count) {
        int n = balls.length;
        if (remain == 0) {
            if (t1 == t2) {
                cnt += count;
            }
            return;
        }
        if (pos == n) {
            return;
        }
        dfs(balls, pos + 1, remain, t1, t2, count);
        for (int i = 1; i <= Math.min(remain, balls[pos]); i++) {
            dfs(balls, pos + 1, remain - i, t1 - (i == balls[pos] ? 1 : 0), t2 + 1, count * c[balls[pos]][i]);
        }
    }
}
