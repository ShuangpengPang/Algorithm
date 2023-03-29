package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0955DeleteColumnsToMakeSortedII（删列造序II）
 * @date 2023/3/17 6:30 PM
 */
public class Problem0955DeleteColumnsToMakeSortedII {

    public int minDeletionSize0(String[] strs) {
        int n = strs.length, m = strs[0].length(), ans = 0;
        boolean[] delete = new boolean[m];
        for (int c = 0; c < m; c++) {
            for (int r = 1; r < n; r++) {
                int i = 0;
                while (i < c) {
                    if (!delete[i] && strs[r].charAt(i) > strs[r - 1].charAt(i)) {
                        break;
                    }
                    i++;
                }
                if (i == c && strs[r].charAt(c) < strs[r - 1].charAt(c)) {
                    delete[c] = true;
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public int minDeletionSize1(String[] strs) {
        int n = strs.length, m = strs[0].length(), ans = 0;
        String[] cur = new String[n];
        for (int c = 0; c < m; c++) {
            String[] tmp = Arrays.copyOf(cur, n);
            for (int i = 0; i < n; i++) {
                tmp[i] += strs[i].charAt(c);
            }
            if (isSorted(tmp)) {
                cur = tmp;
            } else {
                ans++;
            }
        }
        return ans;
    }

    private boolean isSorted(String[] strs) {
        for (int i = strs.length - 1; i > 0; i--) {
            if (strs[i - 1].compareTo(strs[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public int minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length(), ans = 0;
        boolean[] cuts = new boolean[n - 1];
        search: for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (!cuts[j] && strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    ans++;
                    continue search;
                }
            }
            for (int j = 0; j < n - 1; j++) {
                if (strs[j].charAt(i) < strs[j + 1].charAt(i)) {
                    cuts[j] = true;
                }
            }
        }
        return ans;
    }
}
