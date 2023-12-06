package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2597TheNumberOfBeautifulSubsets（美丽子集的数目）
 * @date 2023/12/6 12:48 PM
 */
public class Problem2597TheNumberOfBeautifulSubsets {

    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length, N = 1 << n, cnt = 0;
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            int m = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && Math.abs(nums[i] - nums[j]) == k) {
                    m |= 1 << j;
                }
            }
            mask[i] = m;
        }
        boolean[] valid = new boolean[N];
        valid[0] = true;
        for (int m = 0; m < N; m++) {
            if (valid[m]) {
                for (int i = 0; i < n; i++) {
                    if (!valid[m | 1 << i] && (m & mask[i]) == 0) {
                        valid[m | 1 << i] = true;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
