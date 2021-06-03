package com.shuangpeng.Problem;

public class Problem1829MaximumXORForEachQuery {

    public int[] getMaximumXor0(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] array = new int[n];
        array[0] = nums[0];
        for (int i = 1; i < n; i++) {
            array[i] = array[i - 1] ^ nums[i];
        }
        int[] answer = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int k = 0;
            for (int j = 0; j < maximumBit; j++) {
                int num = 1 << j;
                if ((array[i] & num) == 0) {
                    k += num;
                }
            }
            answer[n - 1 - i] = k;
        }
        return answer;
    }

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int k = (1 << maximumBit) - 1;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = k ^ xor;
            xor ^= nums[n - i - 1];
        }
        return answer;
    }
}
