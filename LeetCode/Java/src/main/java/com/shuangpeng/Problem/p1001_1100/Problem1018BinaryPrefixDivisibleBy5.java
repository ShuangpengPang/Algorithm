package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1018BinaryPrefixDivisibleBy5（可被5整除的二进制前缀）
 * @date 2024/1/6 6:03 PM
 */
public class Problem1018BinaryPrefixDivisibleBy5 {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0, num = 0; i < n; i++) {
            num = ((num << 1) + nums[i]) % 5;
            ans.add(num == 0);
        }
        return ans;
    }
}
