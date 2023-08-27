package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0228SummaryRanges（汇总区间）
 * @Date 2022/10/31 3:29 PM
 * @Version 1.0
 */
public class Problem0228SummaryRanges {

    public List<String> summaryRanges0(int[] nums) {
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

    public List<String> summaryRanges1(int[] nums) {
        int n = nums.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < n; j++, i = j) {
            while (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                j++;
            }
            ans.add(i == j ? String.valueOf(nums[j]) : nums[i] + "->" + nums[j]);
        }
        return ans;
    }

    public List<String> summaryRanges2(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length, p = 0;
        if (n == 0) {
            return ans;
        }
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] != nums[i - 1] + 1) {
                ans.add(i - p == 1 ? String.valueOf(nums[i - 1]) : nums[p] + "->" + nums[i - 1]);
                p = i;
            }
        }
        ans.add(p == n - 1 ? String.valueOf(nums[p]) : nums[p] + "->" + nums[n - 1]);
        return ans;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int n = nums.length, i = 0;
        while (i < n) {
            int start = nums[i], end = start;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                end = nums[i++];
            }
            list.add(start == end ? String.valueOf(start) : start + "->" + end);
        }
        return list;
    }
}
