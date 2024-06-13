package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3169CountDaysWithoutMeetings（无需开会的工作日）
 * @date 2024/6/13 5:11 PM
 */
public class Problem3169CountDaysWithoutMeetings {

    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(e -> e[0]));
        int ans = 0, last = 0;
        for (int i = 0, n = meetings.length; i < n; i++) {
            ans += Math.max(0, meetings[i][0] - last - 1);
            last = Math.max(last, meetings[i][1]);
        }
        return ans + days - last;
    }
}
