package com.shuangpeng.Problem.p0301_0400;

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

    public int[] countBits1(int num) {
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

    public int[] countBits4(int num) {
        if (num < 0) {
            return new int[0];
        }
        int[] answer = new int[num + 1];
        int j = 0;
        int k = 0;
        for (int i = 1; i <= num; i++) {
            if (i == (1 << j)) {
                answer[i] = 1;
                j++;
                k = 0;
            } else {
                answer[i] = 1 + answer[++k];
            }
        }
        return answer;
    }

    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        int i = 0;
        int b = 1;
        while (b <= num) {
            while (i < b && i + b <= num) {
                result[i + b] = result[i] + 1;
                i++;
            }
            i = 0;
            b <<= 1;
        }
        return result;
    }

    public int[] countBits3(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i >>> 1] + (i & 1);
        }
        return result;
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }
}
