package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0944DeleteColumnsToMakeSorted（删列造序）
 * @Date 2022/5/12 10:11 AM
 * @Version 1.0
 */
public class Problem0944DeleteColumnsToMakeSorted {

    public int minDeletionSize0(String[] strs) {
        int m = strs.length, n = strs[0].length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            char pre = strs[0].charAt(i);
            for (int j = 1; j < m; ++j) {
                char c = strs[j].charAt(i);
                if (c < pre) {
                    ++ans;
                    break;
                }
                pre = c;
            }
        }
        return ans;
    }

    public int minDeletionSize(String[] strs) {
        int m = strs.length, n = strs[0].length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (strs[j - 1].charAt(i) > strs[j].charAt(i)) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
