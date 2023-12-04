package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2587RearrangeArrayToMaximizePrefixScore（重排数组以得到最大前缀分数）
 * @date 2023/12/4 11:26 PM
 */
public class Problem2587RearrangeArrayToMaximizePrefixScore {

    public int maxScore0(int[] nums) {
        long sum = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        int count = 0;
        for (int num : nums) {
            if (num > 0) {
                sum += num;
                count++;
            } else {
                q.offer(num);
            }
        }
        while (!q.isEmpty() && sum + q.peek() > 0) {
            sum += q.poll();
            count++;
        }
        return count;
    }

    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        int n = nums.length, i = n - 1;
        while (i >= 0 && sum + nums[i] > 0) {
            sum += nums[i];
            i--;
        }
        return n - i - 1;
    }
}
