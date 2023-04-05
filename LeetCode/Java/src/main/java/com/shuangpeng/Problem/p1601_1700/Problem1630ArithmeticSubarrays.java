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

    public List<Boolean> checkArithmeticSubarrays0(int[] nums, int[] l, int[] r) {
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

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int left = l[i], right = r[i], count = right - left + 1;
            if (count == 1) {
                ans.add(false);
                continue;
            }
            int min = nums[left], max = nums[left];
            for (int j = left + 1; j <= right; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
            }
            if (min == max) {
                ans.add(true);
                continue;
            }
            if ((max - min) % (count - 1) != 0) {
                ans.add(false);
                continue;
            }
            int d = (max - min) / (count - 1);
            boolean[] visited = new boolean[count];
            boolean flag = true;
            for (int j = left; j <= right; j++) {
                if ((nums[j] - min) % d != 0) {
                    flag = false;
                    break;
                }
                int index = (nums[j] - min) / d;
                if (visited[index]) {
                    flag = false;
                    break;
                }
                visited[index] = true;
            }
            ans.add(flag);
        }
        return ans;
    }
}
