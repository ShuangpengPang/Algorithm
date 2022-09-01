package com.shuangpeng.competition.第248场周赛;

import java.util.Arrays;

public class Problem1921 {

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        double[] temp = new double[n];
        for (int i = 0; i < n; i++) {
            temp[i] = (double) dist[i] / (double) speed[i];
        }
        Arrays.sort(temp);
        for (int i = 0; i < n; i++) {
            if (i >= temp[i]) {
                return i;
            }
        }
        return n;
    }
}
