package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2659MakeArrayEmpty（将数组清空）
 * @date 2024/2/4 12:50 PM
 */
public class Problem2659MakeArrayEmpty {

    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        Integer[] ids = new Integer[n];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            ids[i] = i + 1;
            set.add(i + 1);
        }
        Arrays.sort(ids, Comparator.comparingInt(a -> nums[a - 1]));
        int[] st = new int[n + 1];
        long ans = n;
        for (int i = 0, p = 1, t = 0; i < n; i++, t++) {
            int id = ids[i];
            int cnt1 = query(st, p), cnt2 = query(st, id);
            if (p <= id) {
                ans += id - p - cnt2 + cnt1;
            } else {
                ans += (id + n - p) - (t - (cnt1 - cnt2));
            }
            add(st, id);
            set.remove(id);
            Integer next = set.higher(id);
            if (next == null) {
                next = set.higher(0);
            }
            if (next != null) {
                p = next;
            }
        }
        return ans;
    }

    private int query(int[] st, int x) {
        int cnt = 0;
        while (x > 0) {
            cnt += st[x];
            x ^= x & (-x);
        }
        return cnt;
    }

    private void add(int[] st, int x) {
        int n = st.length;
        while (x < n) {
            st[x]++;
            x += x & (-x);
        }
    }
}
