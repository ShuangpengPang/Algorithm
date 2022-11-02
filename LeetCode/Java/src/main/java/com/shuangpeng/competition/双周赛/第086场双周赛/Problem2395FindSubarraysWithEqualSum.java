package com.shuangpeng.competition.双周赛.第086场双周赛;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem2395FindSubarraysWithEqualSum（和相等的子数组）
 * @Date 2022/11/1 5:44 PM
 * @Version 1.0
 */
public class Problem2395FindSubarraysWithEqualSum {

    // 比赛时写法
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < n; i++) {
            int sum = nums[i] + nums[i - 1];
            if (!set.add(sum)) {
                return true;
            }
        }
        return false;
    }
}
