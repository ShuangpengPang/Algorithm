package com.shuangpeng.Problem.p3001_3100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3097ShortestSubarrayWithOrAtLeastKII（或值至少为K的最短子数组II）
 * @date 2024/4/3 2:47 PM
 */
public class Problem3097ShortestSubarrayWithOrAtLeastKII {

    public int minimumSubarrayLength0(int[] nums, int k) {
        int n = nums.length, bit = 32 - Integer.numberOfLeadingZeros(k);
        int[] cnt = new int[bit];
        int ans = n + 1;
        for (int i = 0, j = 0, or = 0; j < n; j++) {
            int num = nums[j];
            if (num >= k) {
                return 1;
            }
            or |= num;
            for (int x = 0; x < bit && num != 0; x++) {
                if ((num & 1) == 1) {
                    cnt[x]++;
                }
                num >>= 1;
            }
            if (or >= k) {
                while (or >= k && i <= j) {
                    for (int x = 0; x < bit; x++) {
                        if ((nums[i] >> x & 1) == 1 && --cnt[x] == 0) {
                            or ^= 1 << x;
                        }
                    }
                    i++;
                }
                ans = Math.min(ans, j - i + 2);
            }
        }
        return ans == n + 1 ? -1 : ans;
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, ans = n + 1;
        List<int[]> ors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ors.add(new int[]{0, i});
            int j = 0;
            for (int[] o : ors) {
                o[0] |= nums[i];
                if (o[0] >= k) {
                    ans = Math.min(ans, i - o[1] + 1);
                }
                if (o[0] == ors.get(j)[0]) {
                    ors.get(j)[1] = o[1];
                } else {
                    ors.set(++j, o);
                }
            }
            ors.subList(j + 1, ors.size()).clear();
        }
        return ans == n + 1 ? -1 : ans;
    }
}
