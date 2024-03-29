package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0973KClosestPointsToOrigin（最接近原点的K个点）
 * @date 2023/3/29 6:27 PM
 */
public class Problem0973KClosestPointsToOrigin {

    public int[][] kClosest0(int[][] points, int k) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0] * a[0] + a[1] * a[1]));
        int[][] ans = new int[k][];
        for (int i = 0; i < k; i++) {
            ans[i] = points[i];
        }
        return ans;
    }

    public int[][] kClosest1(int[][] points, int k) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0] * a[0] + a[1] * a[1]));
        return Arrays.copyOfRange(points, 0, k);
    }

    public int[][] kClosest2(int[][] points, int k) {
        int n = points.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);
        for (int i = 0; i < k; i++) {
            q.offer(points[i]);
        }
        for (int i = k; i < n; i++) {
            int[] p = points[i], t = q.peek();
            if (p[0] * p[0] + p[1] * p[1] < t[0] * t[0] + t[1] * t[1]) {
                q.poll();
                q.offer(p);
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = q.poll();
        }
        return ans;
    }

    public int[][] kClosest(int[][] points, int k) {
        partition(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void partition(int[][] points, int start, int end, int k) {
        if (start == end) {
            return;
        }
        int d = points[end][0] * points[end][0] + points[end][1] * points[end][1];
        int j = start;
        for (int i = start; i < end; i++) {
            int[] p = points[i];
            if (p[0] * p[0] + p[1] * p[1] <= d) {
                swap(points, i, j);
                j++;
            }
        }
        swap(points, j, end);
        if (j == k - 1 || j == k) {
            return;
        }
        if (j > k) {
            partition(points, start, j - 1, k);
        } else {
            partition(points, j + 1, end, k);
        }
    }

    private void swap(int[][] points, int i, int j) {
        if (i != j) {
            int[] p = points[i];
            points[i] = points[j];
            points[j] = p;
        }
    }
}
