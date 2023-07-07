package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1306JumpGameIII（跳跃游戏III）
 * @date 2023/7/7 5:10 PM
 */
public class Problem1306JumpGameIII {

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>(n);
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int p = q.poll(), p1 = p - arr[p], p2 = p + arr[p];
            if (arr[p] == 0) {
                return true;
            }
            if (p1 >= 0 && !visited[p1]) {
                visited[p1] = true;
                q.offer(p1);
            }
            if (p2 < n && !visited[p2]) {
                visited[p2] = true;
                q.offer(p2);
            }
        }
        return false;
    }
}
