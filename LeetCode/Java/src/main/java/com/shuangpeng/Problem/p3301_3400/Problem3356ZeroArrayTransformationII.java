package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3356ZeroArrayTransformationII（零数组变换II）
 * @date 2025/4/1 11:40
 */
public class Problem3356ZeroArrayTransformationII {

    public int minZeroArray(int[] nums, int[][] queries) {
        int left = -1, right = queries.length - 1;
        int n = nums.length;
        int[] diff = new int[n + 1];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            Arrays.fill(diff, 0);
            if (!check(nums, queries, diff, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left == queries.length ? -1 : left + 1;
    }

    private boolean check(int[] nums, int[][] queries, int[] diff, int k) {
        for (int i = 0; i <= k; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }
        for (int i = 0, s = 0, n = nums.length; i < n; i++) {
            s += diff[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
}

class Problem3356ZeroArrayTransformationII0 {

    class SegmentTree {
        private int[] a, lazy;

        SegmentTree(int[] nums) {
            int m = nums.length, n = 2 << (32 - Integer.numberOfLeadingZeros(m - 1));
            a = new int[n];
            lazy = new int[n];
            build(nums, 0, 0, m - 1);
        }

        private void build(int[] nums, int i, int s, int e) {
            if (s == e) {
                a[i] = nums[s];
                return;
            }
            int m = s + (e - s >> 1), l = (i << 1) + 1, r = l + 1;
            build(nums, l, s, m);
            build(nums, r, m + 1, e);
            a[i] = Math.max(a[l], a[r]);
        }

        private void update(int i, int s, int e, int l, int r, int v) {
            if (l <= s && e <= r) {
                lazyUpdate(i, v);
                return;
            }
            spread(i);
            int m = s + (e - s >> 1);
            if (l <= m) {
                update((i << 1) + 1, s, m, l, Math.min(m, r), v);
            }
            if (r > m) {
                update((i << 1) + 2, m + 1, e, Math.max(l, m + 1), r, v);
            }
            a[i] = Math.max(a[(i << 1) + 1], a[(i << 1) + 2]);
        }

        private void lazyUpdate(int i, int v) {
            a[i] -= v;
            lazy[i] += v;
        }

        private void spread(int i) {
            lazyUpdate((i << 1) + 1, lazy[i]);
            lazyUpdate((i << 1) + 2, lazy[i]);
            lazy[i] = 0;
        }

        private int query() {
            return a[0];
        }
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        SegmentTree st = new SegmentTree(nums);
        if (st.query() <= 0) {
            return 0;
        }
        for (int i = 0, n = queries.length; i < n; i++) {
            st.update(0, 0, nums.length - 1, queries[i][0], queries[i][1], queries[i][2]);
            if (st.query() <= 0) {
                return i + 1;
            }
        }
        return -1;
    }
}

class Problem3356ZeroArrayTransformationII1 {

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] diff = new int[n + 1];
        int j = 0;
        for (int i = 0, s = 0; i < n; i++) {
            s += diff[i];
            while (s < nums[i] && j < m) {
                int l = queries[j][0], r = queries[j][1], v = queries[j][2];
                diff[l] += v;
                diff[r + 1] -= v;
                if (l <= i && i <= r) {
                    s += v;
                }
                j++;
            }
            if (s < nums[i]) {
                return -1;
            }
        }
        return j;
    }
}
