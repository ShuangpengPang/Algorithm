package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2374NodeWithHighestEdgeScore（边积分最高的节点）
 * @Date 2022/9/1 6:29 PM
 * @Version 1.0
 */
public class Problem2374NodeWithHighestEdgeScore {

    // 比赛时写法
    public int edgeScore0(int[] edges) {
        int n = edges.length;
        long[] score = new long[n];
        Arrays.fill(score, -1);
        for (int i = 0; i < n; i++) {
            if (edges[i] >= 0) {
                if (score[edges[i]] == -1) {
                    score[edges[i]] = 0;
                }
                score[edges[i]] += i;
            }
        }
        long ans = -1, maxScore = -2;
        for (int i = 0; i < n; i++) {
            if (score[i] > maxScore) {
                maxScore = score[i];
                ans = i;
            }
        }
        return (int) ans;
    }

    public int edgeScore1(int[] edges) {
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

    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] score = new long[n];
        long max = -1L;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int j = edges[i];
            score[j] += i;
            if (score[j] > max) {
                max = score[j];
                ans = j;
            } else if (j < ans && score[j] == max) {
                ans = j;
            }
        }
        return ans;
    }
}
