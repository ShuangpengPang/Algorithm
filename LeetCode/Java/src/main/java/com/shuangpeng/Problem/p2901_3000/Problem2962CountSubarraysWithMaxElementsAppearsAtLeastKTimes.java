package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2962CountSubarraysWithMaxElementsAppearsAtLeastKTimes（统计最大元素至少出现K次的子数组）
 * @date 2024/1/11 2:06 PM
 */
public class Problem2962CountSubarraysWithMaxElementsAppearsAtLeastKTimes {

    public long countSubarrays0(int[] nums, int k) {
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        long ans = 0;
        for (int i = 0, j = 0, cnt = 0, n = nums.length; j < n; j++) {
            cnt += nums[j] == maxNum ? 1 : 0;
            while (cnt == k) {
                cnt -= nums[i++] == maxNum ? 1 : 0;
            }
            ans += i;
        }
        return ans;
    }

    // 如果子数组中最大元素的次数至少为K，则解法如下
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indices.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
        }
        List<Integer> values = new ArrayList<>(indices.keySet());
        values.sort((a, b) -> b - a);
        int size = values.size();
        TreeSet<Integer> set = new TreeSet<>();
        set.add(-1);
        set.add(n);
        long ans = 0;
        for (int i = 0; i < size; i++) {
            List<Integer> list = indices.get(values.get(i));
            int m = list.size();
            for (int j = 0; j + k <= m; j++) {
                int index = list.get(j);
                int previous = set.lower(index), next = Math.min(set.higher(index), j + k < m ? list.get(j + k) : n);
                ans += ((long) (index - previous)) * Math.max(0, next - list.get(j + k - 1));
            }
            for (int j : list) {
                set.add(j);
            }
        }
        return ans;
    }
}
