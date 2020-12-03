package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem0046Permutation {

//    public static void main(String[] args) {
//        Problem0046Permutation a = new Problem0046Permutation();
//        int[] nums = {1, 2, 3};
//        a.permute(nums);
//    }

    public List<List<Integer>> permute0(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> list = new LinkedList<>();
        list.add(nums[0]);
        result.add(list);
        for (int i = 1; i < nums.length; i++) {
            int data = nums[i];
            List<List<Integer>> temp = result;
            result = new ArrayList<>();
            for (List<Integer> item : temp) {
                for (int j = 0; j <= item.size(); j++) {
                    List<Integer> copy = new LinkedList<>(item);
                    copy.add(j, data);
                    result.add(copy);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, result, new ArrayList<>());
        return result;
    }

    // 回溯
    public void backtrack(int[] nums, List<List<Integer>> result, List<Integer> list) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        int location = list.size();
        for (int i = location; i < nums.length; i++) {
            swap(nums, i, location);
            list.add(nums[location]);
            backtrack(nums, result, list);
            list.remove(list.size() - 1);
            swap(nums, i, location);
        }
    }

    public void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[j] - nums[i];
            nums[j] = nums[j] - nums[i];
            nums[i] = nums[j] + nums[i];
        }
    }
}
