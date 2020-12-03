package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0448FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers0(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return answer;
        }
        int i = 0;
        while (i < nums.length) {
            int a = nums[i];
            int b = nums[a - 1];
            if (a == b) {
                i++;
            } else {
                nums[i] = b;
                nums[a - 1] = a;
            }
        }
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                answer.add(i + 1);
            }
        }
        return answer;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return answer;
        }
        for (int i = 0; i < nums.length; i++) {
            int data = nums[i];
            data = data > 0 ? data : -data;
            if (nums[data - 1] > 0) {
                nums[data - 1] = -nums[data - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                answer.add(i + 1);
            }
        }
        return answer;
    }
}
