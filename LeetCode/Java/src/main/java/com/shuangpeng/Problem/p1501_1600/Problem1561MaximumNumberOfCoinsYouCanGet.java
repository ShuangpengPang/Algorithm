package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1561MaximumNumberOfCoinsYouCanGet（你可以获得的最大硬币数目）
 * @date 2023/9/6 5:14 PM
 */
public class Problem1561MaximumNumberOfCoinsYouCanGet {

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int sum = 0, n = piles.length;
        for (int i = n / 3; i < n; i += 2) {
            sum += piles[i];
        }
        return sum;
    }
}
