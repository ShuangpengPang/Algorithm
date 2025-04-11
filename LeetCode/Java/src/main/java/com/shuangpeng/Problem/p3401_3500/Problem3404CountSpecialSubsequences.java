package com.shuangpeng.Problem.p3401_3500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3404CountSpecialSubsequences（统计特殊子序列的数目）
 * @date 2025/4/9 19:31
 */
public class Problem3404CountSpecialSubsequences {

    public long numberOfSubsequences0(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 4; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                int a = nums[i], b = nums[j], g = gcd(a, b);
                map.merge((b / g << 16) | (a / g), 1, Integer::sum);
            }
        }
        long ans = 0;
        for (int i = 2; i < n - 4; i++) {
            for (int j = 0; j < i - 1; j++) {
                int a = nums[j], b = nums[i], g = gcd(a, b);
                ans += map.getOrDefault((a / g << 16) | (b / g), 0);
            }
            int c = nums[i + 2];
            for (int j = i + 4; j < n; j++) {
                int d = nums[j], g = gcd(c, d);
                map.merge((d / g << 16) | (c / g), -1, Integer::sum);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public long numberOfSubsequences1(int[] nums) {
        int n = nums.length;
        Map<Double, Integer> map = new HashMap<>();
        long ans = 0;
        for (int i = 3; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                ans += map.getOrDefault((double) nums[j] / nums[i], 0);
            }
            double b = nums[i - 1];
            for (int j = 0; j < i - 2; j++) {
                map.merge(nums[j] / b, 1, Integer::sum);
            }
        }
        return ans;
    }
}

class Problem3404CountSpecialSubsequences0 {

    public long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 4; i < n - 2; i++) {
            int b = nums[i - 2];
            for (int j = i - 4; j >= 0; j--) {
                int a = nums[j], g = gcd(a, b);
                map.merge(((a / g) << 16) | b / g, 1, Integer::sum);
            }
            int c = nums[i];
            for (int j = i + 2; j < n; j++) {
                int d = nums[j], g = gcd(c, d);
                ans += map.getOrDefault(((d / g) << 16) | c / g, 0);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

//    public static void main(String[] args) {
//        Problem3404CountSpecialSubsequences0 a = new Problem3404CountSpecialSubsequences0();
//        int[] nums = {1,2,3,4,3,6,1};
//        a.numberOfSubsequences(nums);
//    }
}
