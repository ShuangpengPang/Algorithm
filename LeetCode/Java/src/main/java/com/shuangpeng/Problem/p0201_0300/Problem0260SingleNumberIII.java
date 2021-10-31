package com.shuangpeng.Problem.p0201_0300;

public class Problem0260SingleNumberIII {

    public int[] singleNumber0(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        int p = ((result ^ (result - 1)) + 1) >> 1;
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & p) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public int[] singleNumber(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        int lowBit = xorSum & (-xorSum);
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & lowBit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
