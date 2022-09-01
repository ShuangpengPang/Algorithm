package com.shuangpeng.competition.双周赛.第063场双周赛;

import java.util.Arrays;

public class Problem2037 {

    // 比赛时代码
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int n = students.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(students[i] - seats[i]);
        }
        return ans;
    }
}
