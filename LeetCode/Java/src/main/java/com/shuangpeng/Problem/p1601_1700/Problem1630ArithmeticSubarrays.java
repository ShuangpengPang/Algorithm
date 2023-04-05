package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1630ArithmeticSubarrays（等差子数组）
 * @date 2023/4/5 3:56 PM
 */
public class Problem1630ArithmeticSubarrays {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        Set<Integer> set = new HashSet<>();
        List<Boolean> ans = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            set.clear();
            int start = l[i], end = r[i], count = end - start + 1;
            if (count == 1) {
                ans.add(false);
                continue;
            }
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = start; j <= end; j++) {
                int num = nums[j];
                set.add(num);
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            if (set.size() == 1) {
                ans.add(true);
            } else if (set.size() < count) {
                ans.add(false);
            } else {
                int d = (max - min) / (count - 1);
                int value = min + d;
                boolean valid = true;
                for (int j = 1; j < count; j++) {
                    if (!set.contains(value)) {
                        valid = false;
                        break;
                    }
                    value += d;
                }
                ans.add(valid);
            }
        }
        return ans;
    }
}
