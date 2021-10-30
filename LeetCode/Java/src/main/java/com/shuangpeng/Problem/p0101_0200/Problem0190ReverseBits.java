package com.shuangpeng.Problem.p0101_0200;

public class Problem0190ReverseBits {

//    public static void main(String[] args) {
//        int i = 1 << 31;
//        System.err.println(i);
//    }

    public int reverseBits0(int n) {
        int length = 32;
        int[] bits = new int[length];
        for (int i = 0; i < length; i++) {
            bits[i] = n & 1;
            n >>= 1;
        }
        n = 0;
        for (int i = 0; i < length; i++) {
            if (bits[i] == 1) {
                n |= (1 << (length - i - 1));
            }
        }
        return n;
    }

//    (byte * 0x0202020202 & 0x010884422010) % 1023

    public int reverseBits1(int n) {
        int length = 32;
        int answer = 0;
        int power = length - 1;
        while (n != 0) {
            answer |= ((n & 1) << power);
            power--;
            n >>>= 1;
        }
        return answer;
    }

    public int reverseBits2(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    public int reverseBits(int n) {
        n = (n << 16) | (n >>> 16);
        n = ((n << 8) & 0xff00ff00) | ((n >>> 8) & 0x00ff00ff);
        n = ((n << 4) & 0xf0f0f0f0) | ((n >>> 4) & 0x0f0f0f0f);
        n = ((n << 2) & 0xcccccccc) | ((n >>> 2) & 0x33333333);
        n = ((n << 1) & 0xaaaaaaaa) | ((n >>> 1) & 0x55555555);
        return n;
    }
}
