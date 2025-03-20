package com.shuangpeng.Problem.p3301_3400;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3318FindXSumOfAllKLongSubarraysI（计算子数组的x-sumI）
 * @date 2025/3/20 14:07
 */
public class Problem3318FindXSumOfAllKLongSubarraysI {

    Map<Integer, Integer> freq;
    TreeSet<int[]> left, right;
    int sum = 0;

    public int[] findXSum(int[] nums, int k, int x) {
        freq = new HashMap<>();
        left = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        right = new TreeSet<>(left.comparator());
        sum = 0;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            delete(nums[i]);
            freq.merge(nums[i], 1, Integer::sum);
            add(nums[i]);
            int j = i - k + 1;
            if (j < 0) {
                continue;
            }
            while (!right.isEmpty() && left.size() < x) {
                rightToLeft();
            }
            while (left.size() > x) {
                leftToRight();
            }
            ans[j] = sum;
            delete(nums[j]);
            freq.merge(nums[j], -1, Integer::sum);
            add(nums[j]);
        }
        return ans;
    }

    private void rightToLeft() {
        int[] p = right.pollLast();
        sum += p[0] * p[1];
        left.add(p);
    }

    private void leftToRight() {
        int[] p = left.pollFirst();
        sum -= p[0] * p[1];
        right.add(p);
    }

    private void delete(int x) {
        int c = freq.getOrDefault(x, 0);
        if (c == 0) {
            return;
        }
        int[] p = {c, x};
        if (left.contains(p)) {
            left.remove(p);
            sum -= c * x;
        } else {
            right.remove(p);
        }
    }

    private void add(int x) {
        int c = freq.getOrDefault(x, 0);
        if (c == 0) {
            return;
        }
        int[] p = {c, x};
        if (!left.isEmpty() && left.comparator().compare(p, left.first()) > 0) {
            left.add(p);
            sum += c * x;
        } else {
            right.add(p);
        }
    }
}
