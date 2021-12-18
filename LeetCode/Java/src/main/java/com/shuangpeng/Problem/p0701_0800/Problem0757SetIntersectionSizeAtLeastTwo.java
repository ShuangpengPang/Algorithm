package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;

public class Problem0757SetIntersectionSizeAtLeastTwo {

    public int intersectionSizeTwo0(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int n = intervals.length;
        int[] todo = new int[n];
        Arrays.fill(todo, 2);
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = intervals[i][0]; j < intervals[i][0] + todo[i]; ++j) {
                for (int k = 0; k < i; ++k) {
                    if (todo[k] > 0 && j <= intervals[k][1]) {
                        --todo[k];
                    }
                }
            }
            ans += todo[i];
        }
        return ans;
    }

    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int first = -2, second = -1;
        int ans = 0;
        for (int[] pair : intervals) {
            int a = pair[0], b = pair[1];
            if (a > second) {
                first = b - 1;
                second = b;
                ans += 2;
            } else if (a > first) {
                if (b > second) {
                    first = second;
                    second = b;
                } else {
                    first = b - 1;
                }
                ++ans;
            }
        }
        return ans;
    }
}
