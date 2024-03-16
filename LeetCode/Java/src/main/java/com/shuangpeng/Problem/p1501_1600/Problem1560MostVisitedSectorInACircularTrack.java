package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1560MostVisitedSectorInACircularTrack（圆形赛道上经过次数最多的扇区）
 * @date 2024/3/16 4:09 PM
 */
public class Problem1560MostVisitedSectorInACircularTrack {

    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> ans = new ArrayList<>();
        int m = rounds.length, end = rounds[m - 1];
        if (rounds[0] > end) {
            for (int i = 1; i <= end; i++) {
                ans.add(i);
            }
            end = n;
        }
        for (int i = rounds[0]; i <= end; i++) {
            ans.add(i);
        }
        return ans;
    }
}
