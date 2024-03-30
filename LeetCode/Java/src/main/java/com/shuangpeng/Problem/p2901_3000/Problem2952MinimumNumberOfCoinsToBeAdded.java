package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2952MinimumNumberOfCoinsToBeAdded（需要添加的硬币的最小数量）
 * @date 2024/1/10 3:41 PM
 */
public class Problem2952MinimumNumberOfCoinsToBeAdded {

    public int minimumAddedCoins0(int[] coins, int target) {
        Arrays.sort(coins);
        int n = coins.length, x = 1, cnt = 0;
        int i = 0;
        while (x <= target) {
            if (i == n || coins[i] > x) {
                cnt++;
                x <<= 1;
            } else {
                x += coins[i];
                i++;
            }
        }
        return cnt;
    }

    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int n = coins.length, cnt = 0;
        for (int i = 0, j = 1; j <= target;) {
            if (i >= n || coins[i] > j) {
                cnt++;
                j <<= 1;
            } else {
                j += coins[i];
                i++;
            }
        }
        return cnt;
    }
}
