package com.shuangpeng.Problem.p0401_0500;

public class Problem0492ConstructTheRectangle {

    public int[] constructRectangle0(int area) {
        int L = (int) Math.sqrt(area - 1) + 1;
        while (area % L != 0) {
            ++L;
        }
        return new int[]{L, area / L};
    }

    public int[] constructRectangle(int area) {
        int L = area, W = 1;
        for (int i = 2; i * i <= area; ++i) {
            if (area % i == 0) {
                L = area / i;
                W = i;
            }
        }
        return new int[]{L, W};
    }
}
