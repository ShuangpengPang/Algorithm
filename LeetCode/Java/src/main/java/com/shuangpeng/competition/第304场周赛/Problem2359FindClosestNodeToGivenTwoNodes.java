package com.shuangpeng.competition.第304场周赛;

/**
 * @Description: Problem2359FindClosestNodeToGivenTwoNodes（找到离给定两个节点最近的节点）
 * @Date 2022/8/6 5:35 PM
 * @Version 1.0
 */
public class Problem2359FindClosestNodeToGivenTwoNodes {

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dis1 = new int[n], dis2 = new int[n];
        for (int i = node1, d = 0; i != -1 && dis1[i] == 0; i = edges[i]) {
            dis1[i] = ++d;
        }
        for (int i = node2, d = 0; i != -1 && dis2[i] == 0; i = edges[i]) {
            dis2[i] = ++d;
        }
        int ans = -1, dis = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dis1[i] != 0 && dis2[i] != 0) {
                int d = Math.max(dis1[i], dis2[i]);
                if (d < dis) {
                    dis = d;
                    ans = i;
                }
            }
        }
        return ans;
    }
}
