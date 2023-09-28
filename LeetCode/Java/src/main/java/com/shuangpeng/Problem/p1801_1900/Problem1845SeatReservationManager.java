package com.shuangpeng.Problem.p1801_1900;

import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1845SeatReservationManager（座位预约管理系统）
 * @date 2023/9/28 11:18 AM
 */
public class Problem1845SeatReservationManager {
}

class SeatManager {

    boolean[] occupied;
    TreeSet<Integer> seat;

    public SeatManager(int n) {
        occupied = new boolean[n + 2];
        seat = new TreeSet<>();
        seat.add(1);
    }

    public int reserve() {
        int s = seat.first();
        occupied[s] = true;
        seat.remove(s);
        if (!occupied[s + 1]) {
            seat.add(s + 1);
        }
        return s;
    }

    public void unreserve(int seatNumber) {
        occupied[seatNumber] = false;
        seat.add(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */
