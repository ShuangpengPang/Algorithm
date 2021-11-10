package com.shuangpeng.Problem.p1001_1100;

public class Problem1014BestSightseeingPair {

    public int maxScoreSightseeingPair0(int[] values) {
        int n = values.length;
        int[] left = new int[n];
        left[0] = 0;
        for (int i = 1; i < n; ++i) {
            left[i] = (left[i - 1] > values[i - 1] ? left[i - 1] : values[i - 1]) - 1;
        }
        int[] right = new int[n];
        right[n - 1] = 0;
        for (int i = n - 2; i >= 0; --i) {
            right[i] = (right[i + 1] > values[i + 1] ? right[i + 1] : values[i + 1]) - 1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int value = values[i] + Math.max(left[i], right[i]);
            ans = Math.max(ans, value);
        }
        return ans;
    }

    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int ans = 0;
        int maxValue = values[0];
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, maxValue + values[i] - i);
            maxValue = Math.max(maxValue, values[i] + i);
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1014BestSightseeingPair a = new Problem1014BestSightseeingPair();
//        a.maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6});
//    }
}
