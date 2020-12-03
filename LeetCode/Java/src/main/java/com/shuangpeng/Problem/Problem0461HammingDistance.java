package com.shuangpeng.Problem;

public class Problem0461HammingDistance {

    public int hammingDistance(int x, int y) {
        x = x ^ y;
        int count = 0;
        while (x != 0) {
            x = x & (x - 1);
            count++;
        }
        return count;
    }
}
