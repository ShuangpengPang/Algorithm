package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2951FindThePeeks（找出峰值）
 * @date 2024/4/14 8:07 PM
 */
public class Problem2951FindThePeeks {

    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> ans = new ArrayList<>();
        for (int n = mountain.length, i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
