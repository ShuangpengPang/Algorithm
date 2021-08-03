package com.shuangpeng.competition.第252场周赛;

public class Problem1955 {

    public int countSpecialSubsequences(int[] nums) {
        final int M = (int) 1e9 + 7;
        int n = nums.length;
        long zero = 0, one = 0, two = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = (zero << 1) + 1;
                if (zero >= M) {
                    zero -= M;
                }
            } else if (nums[i] == 1) {
                one = (one << 1) + zero;
                one %= M;
            } else {
                two = (two << 1) + one;
                two %= M;
            }
        }
        return (int) two;
    }
}
