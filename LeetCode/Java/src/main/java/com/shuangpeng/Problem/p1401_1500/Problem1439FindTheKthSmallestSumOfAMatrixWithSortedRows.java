package com.shuangpeng.Problem.p1401_1500;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: Problem1439FindTheKthSmallestSumOfAMatrixWithSortedRows（有序矩阵中第K小数组和）
 * @Date 2022/8/17 10:32 AM
 * @Version 1.0
 */
public class Problem1439FindTheKthSmallestSumOfAMatrixWithSortedRows {

    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length, sum = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < m; i++) {
            sum += mat[i][0];
            if (n > 1) {
                q.offer(new int[]{mat[i][1] - mat[i][0], i, 1});
            }
        }
        for (int i = 1; i < k; i++) {
            int[] arr = q.poll();
            int x = arr[1], y = arr[2];
            sum += arr[0];
            if (y < n - 1) {
                arr[0] = mat[x][y + 1] - mat[x][y];
                arr[2] = y + 1;
                q.offer(arr);
            }
        }
        return sum;
    }

    [[1,3,11],[2,4,6]]
}
