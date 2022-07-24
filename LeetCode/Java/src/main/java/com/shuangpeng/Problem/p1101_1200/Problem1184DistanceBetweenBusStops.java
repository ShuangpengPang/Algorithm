package com.shuangpeng.Problem.p1101_1200;

/**
 * @Description: Problem1184DistanceBetweenBusStops（公交站间的距离）
 * @Date 2022/7/24 2:22 PM
 * @Version 1.0
 */
public class Problem1184DistanceBetweenBusStops {

    public int distanceBetweenBusStops0(int[] distance, int start, int destination) {
        int n = distance.length;
        int sum1 = 0;
        for (int i = start; i != destination; i = (i + 1) % n) {
            sum1 += distance[i];
        }
        int sum2 = 0;
        for (int i = destination; i != start; i = (i + 1) % n) {
            sum2 += distance[i];
        }
        return Math.min(sum1, sum2);
    }

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                sum1 += distance[i];
            } else {
                sum2 += distance[i];
            }
        }
        return Math.min(sum1, sum2);
    }
}
