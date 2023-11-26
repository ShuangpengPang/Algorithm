package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2527FindXorBeautyOfArray（查询数组Xor美丽值）
 * @date 2023/11/26 3:24 PM
 */
public class Problem2527FindXorBeautyOfArray {

    public int xorBeauty(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
