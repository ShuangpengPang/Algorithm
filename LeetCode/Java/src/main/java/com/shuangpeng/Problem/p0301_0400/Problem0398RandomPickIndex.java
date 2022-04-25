package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

/**
 * @Description: Problem0398RandomPickIndex
 * @Date 2022/4/25 11:23 AM
 * @Version 1.0
 */
public class Problem0398RandomPickIndex {

    class Solution {

        Map<Integer, List<Integer>> map;
        Random random;

        public Solution(int[] nums) {
            random = new Random();
            map = new HashMap<>();
            int n = nums.length;
            for (int i = 0; i < n; ++i) {
                map.putIfAbsent(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            return list.get(random.nextInt(list.size()));
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
}

class Problem0398RandomPickIndex0 {

    class Solution {

        Random random;
        int[] nums;

        public Solution(int[] nums) {
            this.random = new Random();
            this.nums = nums;
        }

        public int pick(int target) {
            int n = nums.length;
            int count = 0;
            int ans = -1;
            for (int i = 0; i < n; ++i) {
                if (nums[i] == target) {
                    ++count;
                    if (random.nextInt(count) == 0) {
                        ans = i;
                    }
                }
            }
            return ans;
        }
    }
}
