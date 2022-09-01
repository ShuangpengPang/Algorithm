package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

/**
 * @Description: Problem2374NodeWithHighestEdgeScore（边积分最高的节点）
 * @Date 2022/9/1 6:29 PM
 * @Version 1.0
 */
public class Problem2374NodeWithHighestEdgeScore {

    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] score = new long[n];
        for (int i = 0; i < n; i++) {
            score[edges[i]] += i;
        }
        int ans = -1;
        long max = -1;
        for (int i = 0; i < n; i++) {
            if (score[i] > max) {
                max = score[i];
                ans = i;
            }
        }
        return ans;
    }
}
