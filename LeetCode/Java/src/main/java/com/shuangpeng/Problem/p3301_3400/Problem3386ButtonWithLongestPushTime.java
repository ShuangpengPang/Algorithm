package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3386ButtonWithLongestPushTime（按下时间最长的按钮）
 * @date 2025/2/22 2:36 PM
 */
public class Problem3386ButtonWithLongestPushTime {

    public int buttonWithLongestTime(int[][] events) {
        int ans = events[0][0], time = events[0][1];
        for (int i = 1, n = events.length; i < n; i++) {
            int idx = events[i][0], t = events[i][1] - events[i - 1][1];
            if (t > time || t == time && idx < ans) {
                ans = idx;
                time = t;
            }
        }
        return ans;
    }
}
