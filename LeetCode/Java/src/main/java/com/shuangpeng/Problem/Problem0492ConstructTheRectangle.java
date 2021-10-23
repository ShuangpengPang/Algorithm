package com.shuangpeng.Problem;

public class Problem0492ConstructTheRectangle {

    public int[] constructRectangle(int area) {
        int L = (int) Math.sqrt(area - 1) + 1;
        while (area % L != 0) {
            ++L;
        }
        return new int[]{L, area / L};
    }
}
