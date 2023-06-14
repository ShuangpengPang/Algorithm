package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1248CountNumberOfNiceSubarrays（统计「优美子数组」）
 * @date 2023/6/14 11:11 AM
 */
public class Problem1248CountNumberOfNiceSubarrays {

    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0, p1 = 0, p2 = 0, c = 0; i < n; i++) {
            c = (nums[i] & 1) == 0 ? c : c + 1;
            if (c == k) {
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
