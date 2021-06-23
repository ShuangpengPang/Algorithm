package com.shuangpeng.offer;

public class Offer15 {

    public int hammingWeight0(int n) {
        int answer = 0;
        while (n != 0) {
            answer++;
            n &= (n - 1);
        }
        return answer;
    }

    public int hammingWeight1(int n) {
        int answer = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                answer++;
            }
            n >>>= 1;
        }
        return answer;
    }

    public int hammingWeight(int n) {
        int answer = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                answer++;
            }
        }
        return answer;
    }
}
