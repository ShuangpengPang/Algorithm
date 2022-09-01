package com.shuangpeng.competition.第255场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1982 {

    public int[] recoverArray0(int n, int[] sums) {
        Arrays.sort(sums);
        List<Integer> list = recurse(n, sums);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public List<Integer> recurse(int n, int[] sums) {
        if (n == 1) {
            if (sums[0] == 0) {
                return new ArrayList<Integer>() {{
                    add(sums[1]);
                }};
            }
            if (sums[1] == 0) {
                return new ArrayList<Integer>() {{
                    add(sums[0]);
                }};
            }
            return null;
        }
        int length = sums.length;
        int half = length >> 1;
        int[] S = new int[half];
        int[] T = new int[half];
        int i = 0, j = 0;
        boolean[] used = new boolean[length];
        int d = sums[1] - sums[0];
        int left = 0, right = 0;
        while (true) {
            while (left < length && used[left]) {
                left++;
            }
            if (left == length) {
                break;
            }
            S[i++] = sums[left];
            used[left] = true;
            while (right < length && (used[right] || sums[right] != sums[left] + d)) {
                right++;
            }
            if (right == length) {
                break;
            }
            T[j++] = sums[right];
            used[right] = true;
        }
        if (i != j) {
            return null;
        }
        List<Integer> list = recurse(n - 1, S);
        if (list != null) {
            list.add(d);
            return list;
        }
        list = recurse(n - 1, T);
        if (list != null) {
            list.add(-d);
        }
        return list;
    }

    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int length = sums.length;
            boolean[] used = new boolean[length];
            int half = length >> 1;
            int[] S = new int[half];
            int[] T = new int[half];
            int d = sums[1] - sums[0];
            int left = 0, right = 0;
            int l = 0, r = 0;
            boolean flag = false;
            while (true) {
                while (left < length && used[left]) {
                    left++;
                }
                if (left == length) {
                    break;
                }
                used[left] = true;
                if (sums[left] == 0) {
                    flag = true;
                }
                S[l++] = sums[left];
                while (used[right] || sums[right] != sums[left] + d) {
                    right++;
                }
                used[right] = true;
                T[r++] = sums[right];
            }
            if (flag) {
                ans[i] = d;
                sums = S;
            } else {
                ans[i] = -d;
                sums = T;
            }
        }
        return ans;
    }
}
