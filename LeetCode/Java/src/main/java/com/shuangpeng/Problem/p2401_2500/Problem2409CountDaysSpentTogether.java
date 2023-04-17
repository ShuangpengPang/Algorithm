package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2409CountDaysSpentTogether（统计共同度过的日子数）
 * @date 2023/4/17 10:11 AM
 */
public class Problem2409CountDaysSpentTogether {

    private static int[] dates = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int[] preSum = new int[13];
    static {
        for (int i = 1; i <= 12; i++) {
            preSum[i] = preSum[i - 1] + dates[i - 1];
        }
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int d1 = getDate(arriveAlice), d2 = getDate(leaveAlice);
        int d3 = getDate(arriveBob), d4 = getDate(leaveBob);
        int start = Math.max(d1, d3), end = Math.min(d2, d4);
        return Math.max(end - start + 1, 0);
    }

    private int getDate(String s) {
        int m1 = Integer.parseInt(s.substring(0, 2)), d2 = Integer.parseInt(s.substring(3));
        return preSum[m1 - 1] + d2;
    }
}
