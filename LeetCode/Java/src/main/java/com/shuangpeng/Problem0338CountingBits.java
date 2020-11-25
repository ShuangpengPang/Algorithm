package com.shuangpeng;

public class Problem0338CountingBits {

    public int[] countBits0(int num) {
        int b = 0;
        int n = num;
        while (n > 0) {
            n = n >> 1;
            b++;
        }
        int[] bits = new int[b];
        int count = 0;
        int[] result = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            int j = 0;
            while (j < b && bits[j] == 1) {
                bits[j] = 0;
                count--;
                j++;
            }
            if (j < b) {
                bits[j] = 1;
                count++;
            }
            result[i] = count;
        }
        return result;
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        int start = 1;
        int j = 0;
        for (int i = 1; i <= num; i++) {
            if (j == start) {
                j = 0;
                start = i;
            }
            result[i] = 1 + result[j++];
        }
        return result;
    }
}
