package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem1610MaximumNumberOfVisiblePoints {

    public int visiblePoints0(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> degrees = new ArrayList<>();
        int sameCount = 0;
        int x = location.get(0), y = location.get(1);
        for (List<Integer> point : points) {
            int x1 = point.get(0), y1 = point.get(1);
            if (x == x1 && y == y1) {
                ++sameCount;
            } else {
                degrees.add(Math.atan2(y1 - y, x1 - x));
            }
        }
        Collections.sort(degrees);
        int n = degrees.size();
        for (int i = 0; i < n; ++i) {
            degrees.add(degrees.get(i) + 2 * Math.PI);
        }
        double degree = angle * Math.PI / 180;
        int count = 0;
        for (int i = 0; i < n; ++i) {
            count = Math.max(count, binarySearch(degrees, degrees.get(i) + degree) - i);
        }
        return sameCount + count;
    }

    private int binarySearch(List<Double> degrees, double target) {
        int left = 0, right = degrees.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            double degree = degrees.get(mid);
            if (degree > target) {
                right = mid - 1;
            } else if (degree <= target) {
                left = mid + 1;
            }
        }
        return left;
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> degrees = new ArrayList<>();
        int sameCount = 0;
        int x = location.get(0), y = location.get(1);
        for (List<Integer> point : points) {
            int x1 = point.get(0), y1 = point.get(1);
            if (x == x1 && y == y1) {
                ++sameCount;
            } else {
                degrees.add(Math.atan2(y1 - y, x1 - x));
            }
        }
        Collections.sort(degrees);
        int n = degrees.size();
        for (int i = 0; i < n; ++i) {
            degrees.add(degrees.get(i) + 2 * Math.PI);
        }
        double degree = angle * Math.PI / 180;
        int count = 0;
        int m = n << 1;
        for (int l = 0, r = 0; r < m; ++r) {
            double lowDegree = degrees.get(r) - degree;
            while (degrees.get(l) < lowDegree) {
                ++l;
            }
            count = Math.max(count, r - l + 1);
        }
        return sameCount + count;
    }
}
