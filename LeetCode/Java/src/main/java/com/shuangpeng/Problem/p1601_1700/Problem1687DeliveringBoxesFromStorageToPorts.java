package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1687DeliveringBoxesFromStorageToPorts（从仓库到码头运输箱子）
 * @Date 2022/9/21 7:41 PM
 * @Version 1.0
 */
public class Problem1687DeliveringBoxesFromStorageToPorts {

    public int boxDelivering0(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long[] preWeight = new long[n + 1];
        int[] prePort = new int[n + 2];
        for (int i = 0; i < n; i++) {
            preWeight[i + 1] = preWeight[i] + boxes[i][1];
            prePort[i + 1] = prePort[i] + (i > 0 && boxes[i][0] != boxes[i - 1][0] ? 1 : 0);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[n + 1];
        stack.addLast(0);
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && (stack.peekFirst() < i - maxBoxes || preWeight[i] - preWeight[stack.peekFirst()] > maxWeight)) {
                stack.pollFirst();
            }
            int j = stack.isEmpty() ? -1 : stack.peekFirst();
            dp[i] = (j == -1 ? dp[i - 1] : dp[j] + prePort[i] - prePort[j + 1]) + 2;
            while (!stack.isEmpty() && dp[stack.peekLast()] - prePort[stack.peekLast() + 1] >= dp[i] - prePort[i + 1]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        return dp[n];
    }

    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] count = new int[n + 2], preWeight = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            count[i] = count[i - 1] + (i == 1 ? 0 : (boxes[i - 1][0] == boxes[i - 2][0] ? 0 : 1));
            preWeight[i] = preWeight[i - 1] + boxes[i - 1][1];
        }
        int[] dp = new int[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i <= n; i++) {
            while (stack.peek() < i - maxBoxes || preWeight[i] - preWeight[stack.peek()] > maxWeight) {
                stack.pollFirst();
            }
            int j = stack.peekFirst();
            dp[i] = dp[j] - count[j + 1] + count[i] + 2;
            while (!stack.isEmpty() && dp[stack.peekLast()] - count[stack.peekLast() + 1] >= dp[i] - count[i + 1]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        Problem1687DeliveringBoxesFromStorageToPorts a = new Problem1687DeliveringBoxesFromStorageToPorts();
//        int[] boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
//        a.boxDelivering()
//    }
}
