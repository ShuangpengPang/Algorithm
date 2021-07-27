package com.shuangpeng.Problem;

public class Problem1893CheckIfAllTheIntegersInARangeAreCovered {

    public boolean isCovered0(int[][] ranges, int left, int right) {
        boolean[] covered = new boolean[right - left + 1];
        for (int[] range : ranges) {
            if (range[1] >= left && range[1] <= right) {
                int start = Math.max(range[0], left);
                for (int i = start - left; i <= range[1] - left; i++) {
                    covered[i] = true;
                }
            } else if (range[0] >= left && range[0] <= right) {
                int end = Math.min(right, range[1]);
                for (int i = range[0] - left; i <= end - left; i++) {
                    covered[i] = true;
                }
            } else if (range[0] < left && range[1] > right) {
                return true;
            }
        }
        for (int i = 0; i < covered.length; i++) {
            if (!covered[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }
        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
            if (left <= i && i <= right && diff[i] <= 0) {
                return false;
            }
        }
        return true;
    }
}
