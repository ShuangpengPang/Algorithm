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

    public int findRadius1(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int n1 = houses.length, n2 = heaters.length;
        int j = 0;
        int ans = 0;
        for (int i = 0; i < n2; ++i) {
            if (i == 0) {
                ans = Math.max(ans, heaters[i] - houses[0]);
            }
            if (i == n2 - 1) {
                ans = Math.max(ans, houses[n1 - 1] - heaters[i]);
            }
            if (i > 0) {
                int mid = heaters[i - 1] + ((heaters[i] - heaters[i - 1]) >> 1);
                while (j < n1 && houses[j] <= mid) {
                    ++j;
                }
                if ((j > 0 && heaters[i - 1] < houses[j - 1] && houses[j - 1] < heaters[i])
                        || (j < n1 && heaters[i - 1] < houses[j] && houses[j] < heaters[i])) {
                    int house1 = j == 0 ? heaters[i - 1] : houses[j - 1];
                    int house2 = j >= n1 ? heaters[i] : houses[j];
                    ans = Math.max(ans, Math.max(house1 - heaters[i - 1], heaters[i] - house2));
                }
            }
        }
        return ans;
    }

    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int ans = 0;
        int n = heaters.length;
        for (int house : houses) {
            int i = binarySearch(heaters, house);
            int left = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            int right = i + 1 >= n ? Integer.MAX_VALUE : heaters[i + 1] - house;
            ans = Math.max(ans, Math.min(left, right));
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

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int n1 = houses.length, n2 = heaters.length;
        int ans = 0;
        for (int i = 0, j = 0; i < n1; ++i) {
            int house = houses[i];
            int distance = Math.abs(house - heaters[j]);
            while (j + 1 < n2 && Math.abs(house - heaters[j + 1]) <= distance) {
                ++j;
                distance = Math.abs(house - heaters[j]);
            }
            ans = Math.max(ans, distance);
        }
        return ans;
    }
}
