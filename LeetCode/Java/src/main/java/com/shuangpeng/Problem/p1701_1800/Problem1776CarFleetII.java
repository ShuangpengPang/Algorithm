package com.shuangpeng.Problem.p1701_1800;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Description: Problem1776CarFleetII（车队II）
 * @Date 2022/10/10 4:31 PM
 * @Version 1.0
 */
public class Problem1776CarFleetII {

    public double[] getCollisionTimes0(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int j = stack.peek();
                if (ans[j] == -1) {
                    break;
                }
                if (cars[i][1] <= cars[j][1] || (double) (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1]) > ans[j]) {
                    stack.pop();
                } else {
                    break;
                }
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                int j = stack.peek();
                if (cars[i][1] <= cars[j][1]) {
                    ans[i] = -1;
                } else {
                    ans[i] = (double) (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1]);
                }
            }
            stack.push(i);
        }
        return ans;
    }

    public double[] getCollisionTimes(int[][] cars) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = cars.length;
        double[] ans = new double[n];
        Arrays.fill(ans, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && cars[i][1] <= cars[stack.peek()][1]) {
                stack.pop();
            }
            while (!stack.isEmpty()) {
                int j = stack.peek();
                double t = (double) (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1]);
                if (ans[j] == -1 || t < ans[j]) {
                    ans[i] = t;
                    break;
                }
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }
}
