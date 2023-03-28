package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0986IntervalListIntersections（区间列表的交集）
 * @date 2023/3/28 2:55 PM
 */
public class Problem0986IntervalListIntersections {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int n1 = firstList.length, n2 = secondList.length;
        for (int i = 0, j = 0; i < n1 && j < n2;) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (start <= end) {
                list.add(new int[]{start, end});
            }
            if (firstList[i][1] <= secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        int n = list.size();
        int[][] ans = new int[n][];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
