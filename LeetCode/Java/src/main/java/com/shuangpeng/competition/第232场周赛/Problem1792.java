package com.shuangpeng.competition.第232场周赛;

import java.util.PriorityQueue;

public class Problem1792 {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
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
}
