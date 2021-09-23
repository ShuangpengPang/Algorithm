package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem1027LongestArithmeticSubsequence {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        List<Map<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(new HashMap<>());
        }
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int d = nums[i] - nums[j];
                int c = list.get(j).getOrDefault(d, 1) + 1;
                int count = list.get(i).getOrDefault(d, 0);
                if (c > count) {
                    list.get(i).put(d, c);
                    ans = Math.max(ans, c);
                }
            }
        }
        return ans;
    }
}
