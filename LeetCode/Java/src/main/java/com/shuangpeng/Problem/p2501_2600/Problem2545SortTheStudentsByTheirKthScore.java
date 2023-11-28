package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2545SortTheStudentsByTheirKthScore（根据第K场考试的分数排序）
 * @date 2023/11/28 10:57 PM
 */
public class Problem2545SortTheStudentsByTheirKthScore {

    public int[][] sortTheStudents(int[][] score, int k) {
        int m = score.length, n = score[0].length;
        Integer[] ids = new Integer[m];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> (score[b][k] - score[a][k]));
        int[][] ans = new int[m][];
        for (int i = 0; i < m; i++) {
            ans[i] = score[ids[i]].clone();
        }
        return ans;
    }
}
