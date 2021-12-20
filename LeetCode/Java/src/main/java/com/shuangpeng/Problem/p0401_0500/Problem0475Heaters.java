package com.shuangpeng.Problem.p0401_0500;

import java.util.Arrays;

public class Problem0475Heaters {

    public int findRadius0(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        int n1 = houses.length, n2 = heaters.length;
        for (int i = 0; i < n2; ++i) {
            if (i == 0) {
                ans = Math.max(ans, heaters[i] - houses[0]);
            }
            if (i == n2 - 1) {
                ans = Math.max(ans, houses[n1 - 1] - heaters[i]);
            }
            if (i > 0 && heaters[i] == heaters[i - 1]) {
                continue;
            }
            if (i > 0 && heaters[i] > houses[0] && heaters[i - 1] < houses[n1 - 1]) {
                int m = heaters[i - 1] + ((heaters[i] - heaters[i - 1]) >> 1);
                int idx = binarySearch(houses, m);
                int house1 = idx < 0 ? -1 : houses[idx];
                int house2 = idx + 1 >= n1 ? heaters[i] : houses[idx + 1];
                ans = Math.max(ans, Math.max(house1 - heaters[i - 1], heaters[i] - house2));
            }
        }
        return ans;
    }

    private int binarySearch(int[] houses, int num) {
        int left = 0, right = houses.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (houses[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

//    public int findRadius(int[] houses, int[] heaters) {
//        Arrays.sort(houses);
//        Arrays.sort(heaters);
//        int n1 = houses.length, n2 = heaters.length;
//        int j = 0;
//        int ans = 0;
//        for (int i = 0; i < n2; ++i) {
//            if (i == 0) {
//                ans = Math.max(ans, heaters[i] - houses[0]);
//            }
//            if (i == n2 - 1) {
//                ans = Math.max(ans, houses[n1 - 1] - heaters[i]);
//            }
//            if (i > 0 ) {
//                int mid = heaters[i - 1] + ((heaters[i] - heaters[i - 1]) >> 1);
//                while ()
//            }
//        }
//    }
}
