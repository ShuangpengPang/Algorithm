package com.shuangpeng.competition.第251到260场周赛.第256场周赛;

import java.util.Arrays;

public class Problem1985 {

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            int length = a.length();
            for (int i = 0; i < length; ++i) {
                if (a.charAt(i) != b.charAt(i)) {
                    return a.charAt(i) - b.charAt(i);
                }
            }
            return 0;
        });
        return nums[nums.length - k];
    }
}
