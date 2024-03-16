package com.shuangpeng.Problem.p1801_1900;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1854MaximumPopulationYear（人口最多的年份）
 * @date 2024/3/16 10:40 AM
 */
public class Problem1854MaximumPopulationYear {

    public int maximumPopulation1(int[][] logs) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] p : logs) {
            map.merge(p[0], 1, Integer::sum);
            map.merge(p[1], -1, Integer::sum);
        }
        int maxCnt = 0, year = 0, cnt = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cnt += entry.getValue();
            if (cnt > maxCnt) {
                maxCnt = cnt;
                year = entry.getKey();
            }
        }
        return year;
    }

    public int maximumPopulation(int[][] logs) {
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        for (int[] l : logs) {
            minValue = Math.min(minValue, l[0]);
            maxValue = Math.max(maxValue, l[1]);
        }
        int n = maxValue - minValue + 1;
        int[] diff = new int[n];
        for (int[] l : logs) {
            diff[l[0] - minValue]++;
            diff[l[1] - minValue]--;
        }
        int maxCnt = 0, year = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += diff[i];
            if (cnt > maxCnt) {
                maxCnt = cnt;
                year = minValue + i;
            }
        }
        return year;
    }
}
