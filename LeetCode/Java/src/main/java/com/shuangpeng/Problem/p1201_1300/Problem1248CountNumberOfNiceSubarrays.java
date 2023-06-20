package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1248CountNumberOfNiceSubarrays（统计「优美子数组」）
 * @date 2023/6/14 11:11 AM
 */
public class Problem1248CountNumberOfNiceSubarrays {

    public int numberOfSubarrays0(int[] nums, int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>(n);
        list.add(-1);
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                list.add(i);
            }
        }
        list.add(n);
        int ans = 0;
        for (int i = k; i < list.size() - 1; i++) {
            ans += (list.get(i - k + 1) - list.get(i - k)) * (list.get(i + 1) - list.get(i));
        }
        return ans;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0, p1 = 0, p2 = 0, c = 0; i < n; i++) {
            if ((nums[i] & 1) == 1 && ++c == k) {
                p1 = p2;
                while ((nums[p2] & 1) == 0) {
                    p2++;
                }
                p2++;
                c--;
            }
            ans += p2 - p1;
        }
        return ans;
    }
}
