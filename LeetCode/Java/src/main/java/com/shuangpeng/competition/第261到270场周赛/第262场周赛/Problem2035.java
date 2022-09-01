package com.shuangpeng.competition.第261到270场周赛.第262场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem2035 {

    public int minimumDifference(int[] nums) {
        int n = nums.length >> 1;
        int[] A = new int[n], B = new int[n];
        List<List<Integer>> listA = new ArrayList<>(n + 1);
        List<List<Integer>> listB = new ArrayList<>(n + 1);
        for (int i = 0; i < n; ++i) {
            A[i] = nums[i];
            B[i] = nums[i + n];
            listA.add(new ArrayList<>());
            listB.add(new ArrayList<>());
        }
        listA.add(new ArrayList<>());
        listB.add(new ArrayList<>());
        for (int m = 0; m < 1 << n; ++m) {
            int sumA = 0, sumB = 0, count = 0;
            for (int i = 0; i < n; ++i) {
                if ((m & (1 << i)) != 0) {
                    sumA += A[i];
                    sumB += B[i];
                    ++count;
                }
            }
            listA.get(count).add(sumA);
            listB.get(count).add(sumB);
        }
        for (int i = 0; i <= n; ++i) {
            Collections.sort(listB.get(i));
        }
        int sum = Arrays.stream(nums).sum();
        int half = sum >> 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; ++i) {
            for (int a : listA.get(i)) {
                List<Integer> list = listB.get(n - i);
                int left = 0, right = list.size() - 1;
                while (left < right) {
                    int mid = left + ((right - left) >> 1);
                    if (a + list.get(mid) < half) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                int s = a + list.get(left);
                ans = Math.min(ans, Math.abs((s << 1) - sum));
            }
        }
        return ans;
    }
}
