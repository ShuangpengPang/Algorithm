package com.shuangpeng.Problem.p1101_1200;

public class Problem1154DayOfTheYear {

    public int dayOfYear0(String date) {
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            days[2] = 29;
        }
        int ans = 0;
        for (int i = 0; i < month; ++i) {
            ans += days[i];
        }
        return ans + day;
    }


    private static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] preSum = new int[days.length];
    static {
        for (int i = 1; i < days.length; ++i) {
            preSum[i] = preSum[i - 1] + days[i];
        }
    }

    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));
        boolean isLeap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        return day + (isLeap && month > 2 ? preSum[month - 1] + 1 : preSum[month - 1]);
    }
}
