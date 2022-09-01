package com.shuangpeng.competition.第252场周赛;

public class Problem1954 {

    public long minimumPerimeter0(long neededApples) {
        long i = 0;
        while ((1 + i) * i * (2 * i + 1) * 2 < neededApples) {
            i++;
        }
        return i * 8;
    }


    public long minimumPerimeter(long neededApples) {
        long left = 0, right = (long) 1e5 + 1;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if ((1 + mid) * mid * (2 * mid + 1) * 2 < neededApples) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left * 8;
    }

    // (k, k) -> (-k, -k)
    // (1 + 2+ ...+ k) * 2 * 2 * (2k + 1)
    // (1 + k) * k * 2 * (2k + 1)
}
