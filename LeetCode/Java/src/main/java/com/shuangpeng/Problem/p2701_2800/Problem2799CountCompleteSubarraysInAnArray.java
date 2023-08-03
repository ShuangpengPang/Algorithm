package com.shuangpeng.Problem.p2701_2800;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2799CountCompleteSubarraysInAnArray（统计完全子数组的数目）
 * @date 2023/8/3 2:48 PM
 */
public class Problem2799CountCompleteSubarraysInAnArray {

    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int n = nums.length, m = set.size();
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
            while (map.size() == m) {
                if (map.merge(nums[j], -1, Integer::sum) == 0) {
                    map.remove(nums[j]);
                }
                j++;
            }
            ans += j;
        }
        return ans;
    }
}
