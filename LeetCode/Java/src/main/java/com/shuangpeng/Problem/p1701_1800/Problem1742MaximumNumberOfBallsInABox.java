package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1742MaximumNumberOfBallsInABox（盒子中小球的最大数量）
 * @date 2022/11/23 9:43 AM
 */
public class Problem1742MaximumNumberOfBallsInABox {

    private static int[] cnt = new int[55];

    public int countBalls(int lowLimit, int highLimit) {
        for (int i = 0; i < 55; i++) {
            cnt[i] = 0;
        }
        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = 0;
            int j = i;
            while (j > 0) {
                sum += j % 10;
                j /= 10;
            }
            cnt[sum]++;
        }
        return Arrays.stream(cnt).max().getAsInt();
    }
}
