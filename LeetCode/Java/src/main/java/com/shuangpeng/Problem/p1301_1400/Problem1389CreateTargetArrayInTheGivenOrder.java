package com.shuangpeng.Problem.p1301_1400;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1389CreateTargetArrayInTheGivenOrder（按既定顺序创建目标数组）
 * @date 2024/2/29 11:54 PM
 */
public class Problem1389CreateTargetArrayInTheGivenOrder {

    public int[] createTargetArray0(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            list.add(index[i], nums[i]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        int[] cnt = new int[n + 1], ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (query(cnt, mid + 1) + index[i] > mid) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans[left] = nums[i];
            add(cnt, left + 1);
        }
        return ans;
    }

    private int query(int[] cnt, int x) {
        int ans = 0;
        while (x > 0) {
            ans += cnt[x];
            x ^= x & -x;
        }
        return ans;
    }

    private void add(int[] cnt, int x) {
        while (x < cnt.length) {
            cnt[x]++;
            x += x & -x;
        }
    }
}
