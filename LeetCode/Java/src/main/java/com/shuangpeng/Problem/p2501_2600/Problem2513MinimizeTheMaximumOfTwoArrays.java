package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2513MinimizeTheMaximumOfTwoArrays（最小化两个数组中的最大值）
 * @date 2023/11/24 10:38 PM
 */
public class Problem2513MinimizeTheMaximumOfTwoArrays {

    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long m = lcm(divisor1, divisor2), left = 0, right = uniqueCnt1 + uniqueCnt2 << 1;
        while (left <= right) {
            long mid = left + right >> 1;
            long cnt = mid / m, cnt1 = mid / divisor1 - cnt, cnt2 = mid / divisor2 - cnt;
            if (mid < cnt + Math.max(cnt1, uniqueCnt2) + Math.max(cnt2, uniqueCnt1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) left;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
