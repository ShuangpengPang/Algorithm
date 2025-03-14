package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0504FindClosedNumbers（面试题 05.04. 下一个数）
 * @date 2024/8/29 11:19 AM
 */
public class Question0504FindClosedNumbers {

    public int[] findClosedNumbers0(int num) {
        int[] ans = {-1, -1};
        if (num < Integer.MAX_VALUE) {
            int tmp = num & -num, max = num + tmp, lowBit = max & -max;
            ans[0] = max | (num & lowBit - 1) >> Integer.numberOfTrailingZeros(tmp) + 1;
        }
        int m = num & num + 1;
        if (m != 0) {
            int b = m & -m, bit = (num + 1) & -(num + 1);
            ans[1] = (m ^ b) | (b >> 1) | (bit - 1) << Integer.numberOfTrailingZeros(b) - Integer.numberOfTrailingZeros(bit) - 1;
        }
        return ans;
    }

    public int[] findClosedNumbers(int num) {
        if (num == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        } else if (num == 1) {
            return new int[]{2, -1};
        }
        return new int[]{getLarger(num), ~getLarger(~num)};
    }

    private int getLarger(int num) {
        int lowBit = num & -num;
        int max = num + lowBit;
        return max | (num ^ max) / lowBit >>> 2;
    }
}
