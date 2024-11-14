package com.shuangpeng.Problem.p3201_3300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3285FindIndicesOfStableMountains（找到稳定山的下标）
 * @date 2024/11/14 12:07 PM
 */
public class Problem3285FindIndicesOfStableMountains {

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> ans = new ArrayList<>();
        int n = height.length;
        for (int i = 1; i < n; i++) {
            if (height[i - 1] > threshold) {
                ans.add(i);
            }
        }
        return ans;
    }
}
