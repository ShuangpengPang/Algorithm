package com.shuangpeng.competition.第301场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2335MinimumAmountOfTimeToFillCups（装满杯子需要的最短总时长）
 * @Date 2022/7/18 7:55 PM
 * @Version 1.0
 */
public class Problem2335MinimumAmountOfTimeToFillCups {

    public int fillCups(int[] amount) {
        int ans = 0;
        Arrays.sort(amount);
        while (amount[1] > 0 && amount[2] > 0) {
            amount[1]--;
            amount[2]--;
            ans++;
            Arrays.sort(amount);
        }
        return ans + amount[2];
    }
}
