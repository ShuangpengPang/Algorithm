package com.shuangpeng;

public class MaxSubsequenceSum {

    public static void main(String[] args) {
//        int[] array = {31, -41, 59, 26, -53, 58, 97, -93, -23, 84};
        int[] array = {-1, -2, -3, -4, -5};
        System.err.println(getMaxSubsequenceSum(array));
    }

    public static int getMaxSubsequenceSum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] sums = new int[array.length];
        sums[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sums[i] = sums[i - 1] + array[i];
        }
        int min = 0; int max = Integer.MIN_VALUE;
        for (int i = 0; i< sums.length; i++) {
            if ((sums[i] - min) > max) {
                max = sums[i] - min;
            }
            if (sums[i] < min) {
                min = sums[i];
            }
        }
        return max;
    }
}
