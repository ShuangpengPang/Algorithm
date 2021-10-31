package com.shuangpeng.Problem.p0201_0300;

public class Problem0275HIndexII {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int h = n - mid;
            if (citations[mid] > h) {
                right = mid - 1;
            } else if (citations[mid] < h) {
                left = mid + 1;
            } else {
                return h;
            }
        }
        return n - left;
    }
}
