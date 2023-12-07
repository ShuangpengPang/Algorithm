package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2610ConvertAnArrayIntoA2DArrayWithConditions（转换二维数组）
 * @date 2023/12/7 11:30 PM
 */
public class Problem2610ConvertAnArrayIntoA2DArrayWithConditions {

    public List<List<Integer>> findMatrix(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        int rows = 0;
        for (int num : nums) {
            rows = Math.max(rows, ++cnt[num]);
        }
        List<List<Integer>> ans = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            ans.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                ans.get(j).add(i);
            }
        }
        return ans;
    }
}
