package com.shuangpeng.competition.第296场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2294PartitionArraySuchThatMaximumDifferenceIsK（划分数组使最大差为K）
 * @Date 2022/6/11 4:01 PM
 * @Version 1.0
 */
public class Problem2294PartitionArraySuchThatMaximumDifferenceIsK {

    // 比赛时写法
    public int partitionArray0(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int prev = -1000000;
        for (int num : nums) {
            if (num - prev > k) {
                prev = num;
                ++ans;
            }
        }
        return ans;
    }

    public int partitionArray1(int[] nums, int k) {
        Arrays.sort(nums);
        int prev = -k - 1;
        int ans = 0;
        for (int num : nums) {
            if (num - prev > k) {
                ++ans;
                prev = num;
            }
        }
        return ans;
    }

    public int partitionArray(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int n = max - min + 1;
        boolean[] bucket = new boolean[n];
        for (int num : nums) {
            bucket[num - min] = true;
        }
        int ans = 0, prev = -k - 1;
        for (int i = 0; i < n; ++i) {
            if (bucket[i]) {
                if (i - prev > k) {
                    ++ans;
                    prev = i;
                }
            }
        }
        return ans;
    }
}
