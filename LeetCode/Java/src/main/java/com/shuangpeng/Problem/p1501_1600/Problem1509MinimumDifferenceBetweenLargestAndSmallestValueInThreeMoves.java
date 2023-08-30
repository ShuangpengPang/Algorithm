package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1509MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves（三次操作后最大值与最小值的最小差）
 * @date 2023/8/30 3:25 PM
 */
public class Problem1509MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public int minDifference0(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        int N = Integer.MAX_VALUE;
        int[] maxN = new int[4], minN = new int[4];
        Arrays.fill(maxN, -N);
        Arrays.fill(minN, N);
        for (int num : nums) {
            int index = 0;
            while (index < 4 && num <= maxN[index]) {
                index++;
            }
            if (index < 4) {
                for (int i = 2; i >= index; i--) {
                    maxN[i + 1] = maxN[i];
                }
                maxN[index] = num;
            }
            index = 0;
            while (index < 4 && num >= minN[index]) {
                index++;
            }
            if (index < 4) {
                for (int i = 2; i >= index; i--) {
                    minN[i + 1] = minN[i];
                }
                minN[index] = num;
            }
        }
        int ans = N;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, maxN[i] - minN[3 - i]);
        }
        return ans;
    }

    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a), minQ = new PriorityQueue<>();
        for (int num : nums) {
            if (maxQ.size() < 4 || maxQ.peek() > num) {
                maxQ.offer(num);
                if (maxQ.size() > 4) {
                    maxQ.poll();
                }
            }
            if (minQ.size() < 4 || minQ.peek() < num) {
                minQ.offer(num);
                if (minQ.size() > 4) {
                    minQ.poll();
                }
            }
        }
        int[] left = new int[4], right = new int[4];
        for (int i = 3; i >= 0; i--) {
            left[i] = maxQ.poll();
            right[3 - i] = minQ.poll();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, right[i] - left[i]);
        }
        return ans;
    }
}
