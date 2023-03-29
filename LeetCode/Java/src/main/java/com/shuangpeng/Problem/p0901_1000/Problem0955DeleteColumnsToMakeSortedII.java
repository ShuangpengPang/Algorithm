package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0955DeleteColumnsToMakeSortedII（删列造序II）
 * @date 2023/3/17 6:30 PM
 */
public class Problem0955DeleteColumnsToMakeSortedII {

    public int minDeletionSize(String[] strs) {
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
}
