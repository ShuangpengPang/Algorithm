package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0228SummaryRanges（汇总区间）
 * @Date 2022/10/31 3:29 PM
 * @Version 1.0
 */
public class Problem0228SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        for (int l = 0, r = 0; r < n; r++) {
            if (r == n - 1 || nums[r] + 1 < nums[r + 1]) {
                if (l < r) {
                    ans.add("" + nums[l] + "->" + nums[r]);
                } else {
                    ans.add(Integer.toString(nums[r]));
                }
                l = r + 1;
            }
        }
        return ans;
    }
}
