package com.shuangpeng.competition.第301到310场周赛.第301场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2335MinimumAmountOfTimeToFillCups（装满杯子需要的最短总时长）
 * @Date 2022/7/18 7:55 PM
 * @Version 1.0
 */
public class Problem2335MinimumAmountOfTimeToFillCups {

    // 比赛时写法
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[0] == 0 && amount[1] == 0) {
            return amount[2];
        }
        amount[1]--;
        amount[2]--;
        return 1 + fillCups(amount);
    }

    public int fillCups0(int[] amount) {
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

    public int fillCups1(int[] amount) {
        Arrays.sort(amount);
        int x = amount[0], y = amount[1], z = amount[2];
        if (x + y <= z) {
            return z;
        } else {
            return (x + y - z + 1) / 2 + z;
        }
    }

    public int fillCups2(int[] amount) {
        int sum = amount[0] + amount[1] + amount[2];
        int max = Math.max(Math.max(amount[0], amount[1]), amount[2]);
        int half = sum + 1 >> 1;
        return max >= half ? max : half;
    }

    public int fillCups3(int[] amount) {
        return Math.max(Math.max(amount[0], amount[1]), Math.max(amount[2], amount[0] + amount[1] + amount[2] + 1 >> 1));
    }
}
