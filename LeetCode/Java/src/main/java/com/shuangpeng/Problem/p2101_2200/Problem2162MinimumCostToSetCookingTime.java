package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2162MinimumCostToSetCookingTime（设置时间的最小代价）
 * @date 2023/11/13 2:45 PM
 */
public class Problem2162MinimumCostToSetCookingTime {

    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minute = targetSeconds / 60, second = targetSeconds % 60;
        int cost = Integer.MAX_VALUE;
        if (minute <= 99) {
            cost = getCost(startAt, minute, second, moveCost, pushCost);
        }
        if (minute > 0 && second + 60 <= 99) {
            cost = Math.min(cost, getCost(startAt, minute - 1, second + 60, moveCost, pushCost));
        }
        return cost;
    }

    private int getCost(int startAt, int minute, int second, int moveCost, int pushCost) {
        String s = "" + minute + (second >= 10 ? second : "0" + second);
        int n = s.length(), i = 0, ans = 0;
        char c = (char) (startAt + '0');
        while (i < n && s.charAt(i) == '0') {
            i++;
        }
        while (i < n) {
            char ch = s.charAt(i);
            ans += pushCost;
            if (ch != c) {
                ans += moveCost;
                c = ch;
            }
            i++;
        }
        return ans;
    }
}
