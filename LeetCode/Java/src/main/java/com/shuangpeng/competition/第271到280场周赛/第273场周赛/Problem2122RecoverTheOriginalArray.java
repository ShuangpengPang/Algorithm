package com.shuangpeng.competition.第271到280场周赛.第273场周赛;

import java.util.Arrays;

public class Problem2122RecoverTheOriginalArray {

    public int[] recoverArray0(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = n >> 1;
        int k = 0;
        for (int i = 1; i <= m; ++i) {
            int diff = nums[i] - nums[0];
            if (diff == 0 || (diff & 1) == 1) {
                continue;
            }
            boolean find = false;
            boolean[] visited = new boolean[n];
            int l = 0, r = i;
            while (l < n && r < n) {
                while (l < n && visited[l]) {
                    ++l;
                }
                if (l == n) {
                    k = diff >> 1;
                    find = true;
                    break;
                }
                while (r < n && (visited[r] || nums[r] < nums[l] + diff)) {
                    ++r;
                }
                if (r == n || nums[r] != nums[l] + diff) {
                    break;
                }
                visited[l] = true;
                visited[r] = true;
            }
            if (find) {
                break;
            }
        }
        return getAnswer(nums, k);
    }

    private int[] getAnswer(int[] nums, int k) {
        int n = nums.length, m = n >> 1;
        int[] ans = new int[m];
        int idx = 0;
        int l = 0, r = 0;
        boolean[] visited = new boolean[n];
        while (idx < m) {
            while (visited[l]) {
                ++l;
            }
            while (visited[r] || nums[r] < nums[l] + (k << 1)) {
                ++r;
            }
            visited[l] = true;
            visited[r] = true;
            ans[idx] = nums[l] + k;
            ++idx;
        }
        return ans;
    }

    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, m = n >> 1;
        int[] ans = new int[m];
        for (int i = 1; i < n; ++i) {
            int diff = nums[i] - nums[0];
            if (diff == 0 || (diff & 1) == 1) {
                continue;
            }
            int k = diff >> 1;
            boolean[] visited = new boolean[n];
            int idx = 0;
            int left = 0, right = i;
            while (idx < m) {
                while (visited[left]) {
                    ++left;
                }
                while (right < n && (visited[right] || nums[right] < nums[left] + diff)) {
                    ++right;
                }
                if (right == n || nums[right] != nums[left] + diff) {
                    break;
                }
                ans[idx] = nums[left] + k;
                visited[left] = visited[right] = true;
                ++idx;
            }
            if (idx == m) {
                break;
            }
        }
        return ans;
    }
}
