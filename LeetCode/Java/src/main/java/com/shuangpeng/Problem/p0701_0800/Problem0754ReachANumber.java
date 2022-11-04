package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0754ReachANumber（到达终点数字）
 * @Date 2022/11/4 10:09 AM
 * @Version 1.0
 */
public class Problem0754ReachANumber {

    public int reachNumber0(int target) {
        if (target < 0) {
            target = -target;
        }
        long n = (long) Math.floor(Math.sqrt(target << 1));
        if (n * (n + 1) >> 1 < target) {
            n++;
        }
        while (true) {
            if (((n * (n + 1) / 2 - target) & 1) == 0) {
                return (int) n;
            }
            n++;
        }
    }

    public int reachNumbe1(int target) {
        target = Math.abs(target);
        int s = 0, n = 0;
        while (s < target || ((s - target) & 1) == 1) {
            s += ++n;
        }
        return n;
    }

    public int reachNumber(int target) {
        target = Math.abs(target);
        long n = (long) Math.ceil((Math.sqrt(1 + 8.0 * target) - 1) / 2);
        long d = n * (n + 1) / 2 - target;
        return (int) ((d & 1) == 0 ? n : n + 1 + n % 2);
    }
}
