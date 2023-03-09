package com.shuangpeng.competition.第231到240场周赛.第232场周赛;

import java.util.PriorityQueue;

/**
 * @description: 最大平均通过率
 * @date 2023/3/9 7:29 PM
 **/
public class Problem1792MaximumAveragePassRatio {

    public double maxAverageRatio0(int[][] classes, int extraStudents) {
        if (classes == null) {
            return 0;
        }
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> {
            if (((b[0] + 1) / (b[1] + 1) - b[0] / b[1]) > ((a[0] + 1) / (a[1] + 1) - a[0] / a[1])) {
                return 1;
            }
            return -1;
        });
        for (int[] cls : classes) {
            queue.offer(new double[]{cls[0], cls[1]});
        }
        for (int i = 0; i < extraStudents; i++) {
            double[] cls = queue.poll();
            cls[0]++;
            cls[1]++;
            queue.offer(cls);
        }
        double sum = 0;
        while (!queue.isEmpty()) {
            double[] cls = queue.poll();
            sum += cls[0] / cls[1];
        }
        return sum / classes.length;
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            double d1 = (double) (a[0] + 1) / (a[1] + 1) - (double) a[0] / a[1];
            double d2 = (double) (b[0] + 1) / (b[1] + 1) - (double) b[0] / b[1];
            if (d2 > d1) {
                return 1;
            }
            return -1;
        });
        for (int[] cls : classes) {
            q.offer(cls);
        }
        for (int i = 0; i < extraStudents; i++) {
            int[] p = q.poll();
            p[0]++;
            p[1]++;
            q.offer(p);
        }
        double sum = 0;
        for (int[] cls : classes) {
            sum += (double) cls[0] / cls[1];
        }
        return sum / classes.length;
    }
}
