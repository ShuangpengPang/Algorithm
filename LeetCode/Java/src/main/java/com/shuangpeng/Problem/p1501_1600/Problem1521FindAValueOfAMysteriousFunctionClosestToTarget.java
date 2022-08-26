package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: Problem1521FindAValueOfAMysteriousFunctionClosestToTarget（找到最接近目标值的函数值）
 * @Date 2022/8/26 10:35 AM
 * @Version 1.0
 */
public class Problem1521FindAValueOfAMysteriousFunctionClosestToTarget {

    public int closestToTarget0(int[] arr, int target) {
        Set<Integer> set = new HashSet<>(), temp = new HashSet<>();
        int ans = Integer.MAX_VALUE;
        for (int num : arr) {
            temp.clear();
            temp.add(num);
            for (int i : set) {
                temp.add(num & i);
            }
            Set<Integer> t = set;
            set = temp;
            temp = t;
            for (int i : set) {
                ans = Math.min(ans, Math.abs(i - target));
            }
        }
        return ans;
    }

    public int closestToTarget1(int[] arr, int target) {
        List<Integer> valid = new ArrayList<>(), tmp = new ArrayList<>();
        int ans = Integer.MAX_VALUE;
        for (int num : arr) {
            tmp.clear();
            tmp.add(num);
            ans = Math.min(ans, Math.abs(target - num));
            int last = num;
            for (int pre : valid) {
                int i = num & pre;
                if (i != last) {
                    last = i;
                    ans = Math.min(ans, Math.abs(target - i));
                    tmp.add(i);
                }
            }
            List<Integer> t = valid;
            valid = tmp;
            tmp = t;
        }
        return ans;
    }

    public int closestToTarget(int[] arr, int target) {
        int n = arr.length, k = getPower(n);
        int[][] st = new int[n][k];
        for (int i = 0; i < n; i++) {
            st[i][0] = arr[i];
        }
        for (int j = 1; j < k; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                st[i][j] = st[i][j - 1] & st[i + (1 << (j - 1))][j - 1];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int l = i, r = n - 1;
            while (l <= r) {
                int m = l + (r - l >> 1);
                int d = query(st, i, m) - target;
                if (d == 0) {
                    return 0;
                }
                ans = Math.min(ans, Math.abs(d));
                if (d > 0) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return ans;
    }

    private int query(int[][] st, int l, int r) {
        int k = getPower(r - l + 1) - 1;
        return st[l][k] & st[r - (1 << k) + 1][k];
    }

    private int getPower(int n) {
        int ans = 0;
        while (n > 0) {
            n >>= 1;
            ans++;
        }
        return ans;
    }
}

