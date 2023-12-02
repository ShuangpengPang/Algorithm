package com.shuangpeng.Problem.p2501_2600;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2568MinimumImpossibleOR（最小无法得到的或值）
 * @date 2023/12/2 12:09 PM
 */
public class Problem2568MinimumImpossibleOR {

    public int minImpossibleOR0(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int or = 0;
        for (int num : nums) {
            or |= num;
            set.add(num);
        }
        int num = 1;
        while (or > 0) {
            if (!set.contains(num)) {
                return num;
            }
            num <<= 1;
            or >>= 1;
        }
        return num;
    }

    public int minImpossibleOR(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if ((num & (num - 1)) == 0) {
                ans |= num;
            }
        }
        ans++;
        return ans & -ans;
    }
}
