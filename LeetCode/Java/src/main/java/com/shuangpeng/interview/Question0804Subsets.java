package com.shuangpeng.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0804Subsets（面试题 08.04. 幂集）
 * @date 2024/8/28 11:12 AM
 */
public class Question0804Subsets {

    public List<List<Integer>> subsets0(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int num : nums) {
            int n = ans.size();
            for (int i = 0; i < n; i++) {
                List<Integer> list = new ArrayList<>(ans.get(i));
                list.add(num);
                ans.add(list);
            }
        }
        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length, N = 1 << n;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    list.add(nums[j]);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
