package com.shuangpeng.Problem.p0301_0400;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem0378KthSmallestElementInASortedMatrix {

    public int kthSmallest01(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int length = matrix.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < length; i++) {
            queue.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] a = queue.poll();
            if (a[2] < length - 1) {
                queue.offer(new int[]{matrix[a[1]][a[2] + 1], a[1], a[2] + 1});
            }
        }
        return queue.poll()[0];
    }

    public int kthSmallest2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int length = matrix.length;
        int min = matrix[0][0];
        int max = matrix[length - 1][length - 1];
        while (min <= max) {
            int mid = min + (max - min) / 2;
            int row = length - 1;
            int col = 0;
            int count = 0;
            int data = Integer.MIN_VALUE;
            while (row >= 0 && col < length) {
                if (matrix[row][col] <= mid) {
                    data = Math.max(data, matrix[row][col]);
                    count += row + 1;
                    col++;
                } else {
                    row--;
                }
            }
            if (count < k) {
                min = mid + 1;
            } else if (count > k) {
                max = mid - 1;
            } else {
                return data;
            }
        }
        return min;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }











    public int kthSmallest0(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }
}
