package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1419MinimumNumberOfFrogsCroaking（数青蛙）
 * @date 2023/5/6 10:00 AM
 */
public class Problem1419MinimumNumberOfFrogsCroaking {

    public int minNumberOfFrogs(String croakOfFrogs) {
        String s = "croak";
        int[] next = new int[6];
        next[0] = Integer.MAX_VALUE;
        int n = croakOfFrogs.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int j = s.indexOf(croakOfFrogs.charAt(i)) + 1;
            if (next[j] == next[j - 1]) {
                return -1;
            }
            next[j]++;
            ans = Math.max(ans, next[1] - next[5]);
        }
        return next[1] == next[5] ? ans : -1;
    }
}
