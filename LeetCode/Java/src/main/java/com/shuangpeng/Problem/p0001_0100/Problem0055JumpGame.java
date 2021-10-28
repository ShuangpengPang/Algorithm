package com.shuangpeng.Problem.p0001_0100;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0055JumpGame {

    // 广度优先遍历
    public boolean canJump0(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        boolean[] visited = new boolean[nums.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int index = queue.poll();
                int maxStep = nums[index];
                for (int i = maxStep; i >= 1; i--) {
                    int target = index + i;
                    if (target < nums.length - 1 && !visited[target]) {
                        queue.offer(target);
                        visited[target] = true;
                    } else if (target >= nums.length - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 贪心
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int max = 0;
        for (int i = 0; i <= max; i++) {
            if (max >= nums.length - 1) {
                return true;
            }
            max = Math.max(max, i + nums[i]);
        }
        return false;
    }
}
