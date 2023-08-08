package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1361ValidateBinaryTreeNodes（验证二叉树）
 * @date 2023/8/8 11:14 AM
 */
public class Problem1361ValidateBinaryTreeNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] visited = new boolean[n];
        int cnt = n;
        for (int i = 0; i < n; i++) {
            int left = leftChild[i], right = rightChild[i];
            if (left == i || right == i) {
                return false;
            }
            if (left != -1 && visited[left]) {
                return false;
            }
            if (left != -1) {
                visited[left] = true;
                cnt--;
            }
            if (right != -1 && visited[right]) {
                return false;
            }
            if (right != -1) {
                visited[right] = true;
                cnt--;
            }
        }
        if (cnt != 1) {
            return false;
        }
        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                root = i;
                break;
            }
        }
        Arrays.fill(visited, false);
        if (!dfs(root, leftChild, rightChild, visited)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int root, int[] leftChild, int[] rightChild, boolean[] visited) {
        if (root == -1) {
            return true;
        }
        if (visited[root]) {
            return false;
        }
        visited[root] = true;
        return dfs(leftChild[root], leftChild, rightChild, visited) && dfs(rightChild[root], leftChild, rightChild, visited);
    }
}
