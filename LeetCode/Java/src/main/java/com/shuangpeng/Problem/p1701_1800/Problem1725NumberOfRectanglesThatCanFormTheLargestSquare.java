package com.shuangpeng.Problem.p1701_1800;

public class Problem1725NumberOfRectanglesThatCanFormTheLargestSquare {

    public int countGoodRectangles(int[][] rectangles) {
        int ans = 0, maxLen = 0;
        for (int[] rect : rectangles) {
            int l = Math.min(rect[0], rect[1]);
            if (l > maxLen) {
                maxLen = l;
                ans = 1;
            } else if (l == maxLen) {
                ++ans;
            }
        }
        return ans;
    }
}
