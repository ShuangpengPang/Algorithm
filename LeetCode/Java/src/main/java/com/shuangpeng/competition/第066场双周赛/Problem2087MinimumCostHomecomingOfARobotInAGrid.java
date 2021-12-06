package com.shuangpeng.competition.第066场双周赛;

public class Problem2087MinimumCostHomecomingOfARobotInAGrid {

    // 比赛时写法
    public int minCost0(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int m = rowCosts.length, n = colCosts.length;
        int x1 = startPos[0], y1 = startPos[1];
        int x2 = homePos[0], y2 = homePos[1];
        int flag1 = x2 > x1 ? 1 : (x2 < x1 ? -1 : 0);
        int flag2 = y2 > y1 ? 1 : (y2 < y1 ? -1 : 0);
        if (flag1 == 0 && flag2 == 0) {
            return 0;
        }
        if (flag1 == 0) {
            int ans = 0;
            for (int i = y1; i != y2; i += flag2) {
                ans += colCosts[i + flag2];
            }
            return ans;
        }
        if (flag2 == 0) {
            int ans = 0;
            for (int i = x1; i != x2; i += flag1) {
                ans += rowCosts[i + flag1];
            }
            return ans;
        }
        int ans1 = getCost(x1, x2, flag1, rowCosts, y1, y2, flag2, colCosts);
        int ans2 = getCost(y1, y2, flag2, colCosts, x1, x2, flag1, rowCosts);
        return Math.min(ans1, ans2);
    }

    private int getCost(int x1, int x2, int flag1, int[] cost1, int y1, int y2, int flag2, int[] cost2) {
        int ans = 0;
        for (int i = x1; i != x2; i += flag1) {
            ans += cost1[i + flag1];
        }
        for (int i = y1; i != y2; i += flag2) {
            ans += cost2[i + flag2];
        }
        return ans;
    }

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int x1 = startPos[0], y1 = startPos[1];
        int x2 = homePos[0], y2 = homePos[1];
        int flag1 = x2 >= x1 ? 1 : -1;
        int cost = 0;
        for (int i = x1; i != x2; i += flag1) {
            cost += rowCosts[i + flag1];
        }
        int flag2 = y2 >= y1 ? 1 : -1;
        for (int i = y1; i != y2; i += flag2) {
            cost += colCosts[i + flag2];
        }
        return cost;
    }
}
