package com.shuangpeng.Problem.p2401_2500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2488CountSubarraysWithMedianK（统计中位数为K的子数组）
 * @date 2022/12/15 12:07 PM
 */
public class Problem2488CountSubarraysWithMedianK {

    public int countSubarrays(int[] nums, int k) {
        int n = nums.length, idx = -1;
        int cnt = 0, ans = 0;
        Map<Integer, Integer> even = new HashMap<>(), odd = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (idx == -1) {
                int j = (cnt << 1) - i;
                Map<Integer, Integer> map = (i & 1) == 0 ? even : odd;
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
            cnt += nums[i] < k ? 1 : 0;
            if (nums[i] == k) {
                idx = i;
            }
            if (idx != -1) {
                int j = (cnt << 1) - i - 1;
                Map<Integer, Integer> m1 = (i & 1) == 0 ? odd : even;
                Map<Integer, Integer> m2 = (i & 1) == 0 ? even : odd;
                ans += m1.getOrDefault(j + 2, 0);
                ans += m2.getOrDefault(j + 1, 0);
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem2488CountSubarraysWithMedianK a = new Problem2488CountSubarraysWithMedianK();
//        int[] nums = {3, 2, 1, 4, 5};
//        a.countSubarrays(nums, 4);
//    }
}
