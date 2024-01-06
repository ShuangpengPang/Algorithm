package com.shuangpeng.Problem.p2901_3000;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2905FindIndicesWithIndexAndValueDifferenceII（找出满足差值条件的下标II）
 * @date 2024/1/2 11:04 PM
 */
public class Problem2905FindIndicesWithIndexAndValueDifferenceII {

    public int[] findIndices0(int[] nums, int indexDifference, int valueDifference) {
        int[] ans = {-1, -1};
        int n = nums.length;
        if (indexDifference >= n) {
            return ans;
        }
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(n - indexDifference, (a, b) -> nums[a] - nums[b]);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(n - indexDifference, (a, b) -> nums[b] - nums[a]);
        for (int i = indexDifference; i < n; i++) {
            pq1.offer(i);
            pq2.offer(i);
        }
        for (int i = indexDifference; i < n && ans[0] == -1; i++) {
            int j = i - indexDifference;
            while (pq1.peek() < i) {
                pq1.poll();
            }
            while (pq2.peek() < i) {
                pq2.poll();
            }
            if (Math.abs(nums[j] - nums[pq1.peek()]) >= valueDifference) {
                ans[0] = j;
                ans[1] = pq1.peek();
            }
            if (Math.abs(nums[j] - nums[pq2.peek()]) >= valueDifference) {
                ans[0] = j;
                ans[1] = pq2.peek();
            }
        }
        return ans;
    }

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int N = Integer.MAX_VALUE, maxIndex = 0, minIndex = 0;
        for (int i = indexDifference, j = 0; i < nums.length; i++, j++) {
            if (nums[j] > nums[maxIndex]) {
                maxIndex = j;
            } else if (nums[j] < nums[minIndex]) {
                minIndex = j;
            }
            if (nums[maxIndex] - nums[i] >= valueDifference) {
                return new int[]{maxIndex, i};
            }
            if (nums[i] - nums[minIndex] >= valueDifference) {
                return new int[]{minIndex, i};
            }
        }
        return new int[]{-1, -1};
    }
}
