package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3265CountAlmostEqualPairsI（统计近似相等数对I）
 * @date 2024/11/18 5:07 PM
 */
public class Problem3265CountAlmostEqualPairsI {

    public int countPairs(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                ans += getCount(nums[i], nums[j]);
            }
        }
        return ans;
    }

    private int getCount(int x, int y) {
        if (x == y) {
            return 1;
        }
        int[] d1 = new int[2], d2 = new int[2];
        int index = 0;
        while (x != 0) {
            int a = x % 10, b = y % 10;
            if (a != b) {
                if (index < 2) {
                    d1[index] = a;
                    d2[index] = b;
                    index++;
                } else {
                    return 0;
                }
            }
            x /= 10;
            y /= 10;
        }
        return index == 2 && d1[0] != d1[1] && d1[0] == d2[1] && d1[1] == d2[0] ? 1 : 0;
    }
}
