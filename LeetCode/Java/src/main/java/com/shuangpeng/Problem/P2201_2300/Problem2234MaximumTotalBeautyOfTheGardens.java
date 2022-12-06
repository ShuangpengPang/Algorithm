package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2234MaximumTotalBeautyOfTheGardens（花园的最大总美丽值）
 * @date 2022/12/5 6:09 PM
 */
public class Problem2234MaximumTotalBeautyOfTheGardens {

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length;
        if (flowers[0] >= target) {
            return (long) n * full;
        }
        long leftFlowers = newFlowers;
        for (int i = 0; i < n; i++) {
            flowers[i] = Math.min(flowers[i], target);
            leftFlowers -= target - flowers[i];
        }
        long sum = 0L, ans = 0L;
        for (int i = 0, x = 0; i <= n; i++) {
            if (leftFlowers >= 0) {
                while (x < i && (long) flowers[x] * x - sum <= leftFlowers) {
                    sum += flowers[x++];
                }
                long beauty = (long) (n - i) * full;
                if (x > 0) {
                    beauty += (long) Math.min((sum + leftFlowers) / x, target - 1) * partial;
                }
                ans = Math.max(ans, beauty);
            }
            if (i < n) {
                leftFlowers += target - flowers[i];
            }
        }
        return ans;
    }
}