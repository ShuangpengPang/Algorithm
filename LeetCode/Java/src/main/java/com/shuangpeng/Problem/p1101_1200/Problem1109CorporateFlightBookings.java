package com.shuangpeng.Problem.p1101_1200;

public class Problem1109CorporateFlightBookings {

    public int[] corpFlightBookings0(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            int from = booking[0];
            int to = booking[1];
            int seats = booking[2];
            for (int i = from; i <= to; ++i) {
                ans[i - 1] += seats;
            }
        }
        return ans;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            int from = booking[0];
            int to = booking[1];
            int seats = booking[2];
            ans[from - 1] += seats;
            if (to < n) {
                ans[to] -= seats;
            }
        }
        for (int i = 1; i < n; ++i) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }
}
