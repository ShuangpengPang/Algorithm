package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0771JewelsAndStones（宝石和石头）
 * @date 2023/7/27 2:47 PM
 */
public class Problem0771JewelsAndStones {

    public int numJewelsInStones(String jewels, String stones) {
        boolean[] set = new boolean[58];
        for (char c : jewels.toCharArray()) {
            set[c - 'A'] = true;
        }
        int ans = 0;
        for (char c : stones.toCharArray()) {
            if (set[c - 'A']) {
                ans++;
            }
        }
        return ans;
    }
}
