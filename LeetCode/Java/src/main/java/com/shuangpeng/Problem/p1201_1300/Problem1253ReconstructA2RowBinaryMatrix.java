package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1253ReconstructA2RowBinaryMatrix（重构2行二进制矩阵）
 * @date 2023/6/15 11:20 AM
 */
public class Problem1253ReconstructA2RowBinaryMatrix {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<Integer> r1 = new ArrayList<>(n), r2 = new ArrayList<>(n);
        for (int i = 0; i < n && upper >= 0 && lower >= 0; i++) {
            int sum = colsum[i];
            int num1 = sum == 0 || sum == 1 && upper < lower ? 0 : 1, num2 = sum - num1;
            r1.add(num1);
            r2.add(num2);
            upper -= num1;
            lower -= num2;
        }
        if (upper != 0 || lower != 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>(2);
        ans.add(r1);
        ans.add(r2);
        return ans;
    }
}
