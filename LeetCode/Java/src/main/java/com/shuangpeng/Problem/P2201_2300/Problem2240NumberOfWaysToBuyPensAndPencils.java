package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2240NumberOfWaysToBuyPensAndPencils（买钢笔和铅笔的方案数）
 * @date 2023/9/1 10:10 AM
 */
public class Problem2240NumberOfWaysToBuyPensAndPencils {

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        while (total >= 0) {
            ans += total / cost2 + 1;
            total -= cost1;
        }
        return ans;
    }
}
