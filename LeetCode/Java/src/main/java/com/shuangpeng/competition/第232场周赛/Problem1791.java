package com.shuangpeng.competition.第232场周赛;

public class Problem1791 {

    public int findCenter(int[][] edges) {
        int i = edges[0][0];
        int j = edges[0][1];
        int m = edges[1][0];
        int n = edges[1][1];
        if (i == m || i == n) {
            return i;
        }
        return j;
    }
}
