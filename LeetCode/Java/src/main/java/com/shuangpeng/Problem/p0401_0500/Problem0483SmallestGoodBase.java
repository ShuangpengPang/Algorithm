package com.shuangpeng.Problem.p0401_0500;

public class Problem0483SmallestGoodBase {

    public String smallestGoodBase0(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }

    public String smallestGoodBase(String n) {
        long value = Long.parseLong(n);
        int mMax = (int) (Math.log(value) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(value, 1.0 / m);
            long sum = 1;
            long j = 1;
            for (int i = 0; i < m; i++) {
                j *= k;
                sum += j;
            }
            if (sum == value) {
                return Long.toString(k);
            }
        }
        return Long.toString(value - 1);
    }
}
