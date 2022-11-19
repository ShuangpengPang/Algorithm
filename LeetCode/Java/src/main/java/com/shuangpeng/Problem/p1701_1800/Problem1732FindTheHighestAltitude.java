package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1732FindTheHighestAltitude（找到最高海拔）
 * @date 2022/11/19 10:31 AM
 */
public class Problem1732FindTheHighestAltitude {

    public int largestAltitude(int[] gain) {
        int ans = 0, h = 0;
        for (int g : gain) {
            h += g;
            ans = Math.max(ans, h);
        }
        return ans;
    }
}
