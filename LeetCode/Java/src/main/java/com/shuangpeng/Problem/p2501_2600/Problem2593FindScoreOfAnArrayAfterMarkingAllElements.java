package com.shuangpeng.Problem.p2501_2600;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2593FindScoreOfAnArrayAfterMarkingAllElements（标记所有元素后数组的分数）
 * @date 2023/12/5 11:26 PM
 */
public class Problem2593FindScoreOfAnArrayAfterMarkingAllElements {

    public long findScore(int[] nums) {
        int n = nums.length;
        boolean[] mark = new boolean[n];
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        for (int i = 0; i < n; i++) {
            q.offer(i);
        }
        long score = 0;
        while (!q.isEmpty()) {
            int index = q.poll();
            if (!mark[index]) {
                score += nums[index];
                mark[index] = true;
                if (index > 0) {
                    mark[index - 1] = true;
                }
                if (index + 1 < n) {
                    mark[index + 1] = true;
                }
            }
        }
        return score;
    }
}
