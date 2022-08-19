package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1450NumberOfStudentsDoingHomeworkAtAGivenTime（在既定时间做作业的学生人数）
 E* @Date 2022/8/19 10:01 AM
 * @Version 1.0
 */
public class Problem1450NumberOfStudentsDoingHomeworkAtAGivenTime {

    public int busyStudent0(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ans++;
            }
        }
        return ans;
    }

    public int busyStudent1(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, startTime[i]);
            max = Math.max(max, endTime[i]);
        }
        if (queryTime < min || queryTime > max) {
            return 0;
        }
        int[] diff = new int[max + 2];
        for (int i = 0; i < n; i++) {
            diff[startTime[i]]++;
            diff[endTime[i] + 1]--;
        }
        int cnt = 0;
        for (int i = 0; i <= queryTime; i++) {
            cnt += diff[i];
        }
        return cnt;
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int cnt = binarySearch(startTime, queryTime);
        return cnt - binarySearch(endTime, queryTime - 1);
    }

    private int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (target >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
