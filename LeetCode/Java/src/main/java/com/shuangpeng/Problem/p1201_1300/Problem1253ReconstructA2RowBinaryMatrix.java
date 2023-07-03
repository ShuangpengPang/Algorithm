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

    public List<List<Integer>> reconstructMatrix0(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<Integer> first = new ArrayList<>(n), second = new ArrayList<>(n);
        for (int sum : colsum) {
            int num1 = sum == 0 || sum == 1 && upper < lower ? 0 : 1, num2 = sum - num1;
            first.add(num1);
            second.add(num2);
            upper -= num1;
            lower -= num2;
        }
        if (upper == 0 && lower == 0) {
            return new ArrayList<List<Integer>>() {{
                add(first);
                add(second);
            }};
        }
        return new ArrayList<>();
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length, sum = 0, two = 0;
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                two++;
            }
            sum += colsum[i];
        }
        if (sum != upper + lower || two > Math.min(upper, lower)) {
            return new ArrayList<>();
        }
        upper -= two;
        List<Integer> list1 = new ArrayList<>(n), list2 = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int num = colsum[i] == 0 || colsum[i] == 1 && upper == 0 ? 0 : 1;
            list1.add(num);
            list2.add(colsum[i] - num);
            upper -= colsum[i] != 2 ? num : 0;
        }
        return new ArrayList<List<Integer>>() {{
            add(list1);
            add(list2);
        }};
    }
}
