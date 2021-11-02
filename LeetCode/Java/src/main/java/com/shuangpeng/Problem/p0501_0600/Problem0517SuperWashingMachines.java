package com.shuangpeng.Problem.p0501_0600;

import java.util.Arrays;

public class Problem0517SuperWashingMachines {

    public int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        int n = machines.length;
        if (sum % n != 0) {
            return -1;
        }
        int avg = sum / n;
        int left = 0, right = sum;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            right -= machines[i];
            ans = Math.max(ans, Math.max(i * avg - left, 0) + Math.max((n - i - 1) * avg - right, 0));
            left += machines[i];
        }
        return ans;
    }
}
