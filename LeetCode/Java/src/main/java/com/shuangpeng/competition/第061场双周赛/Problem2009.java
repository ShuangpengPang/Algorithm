package com.shuangpeng.competition.第061场双周赛;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem2009 {

    public int minOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        Integer[] array = set.toArray(new Integer[0]);
        Arrays.sort(array);
        int length = array.length;
        int maxCount = 0;
        for (int i = 0, j = 0; j < length; ++j) {
            while (array[j] - array[i] > n - 1) {
                ++i;
            }
            maxCount = Math.max(maxCount, j - i + 1);
        }
        return n - maxCount;
    }
}
