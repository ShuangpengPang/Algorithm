package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3289TheTwoSneakyNumbersOfDigitville（数字小镇中的捣蛋鬼）
 * @date 2024/11/15 4:17 PM
 */
public class Problem3289TheTwoSneakyNumbersOfDigitville {

    public int[] getSneakyNumbers0(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            while (t != nums[t]) {
                nums[i] = nums[t];
                nums[t] = t;
                t = nums[i];
            }
        }
        return new int[]{nums[n - 2], nums[n - 1]};
    }

    public int[] getSneakyNumbers(int[] nums) {
        int m = nums.length, n = m - 2;
        int xor = 0;
        for (int i = 0; i < m; i++) {
            xor ^= nums[i] ^ (i < n ? i : 0);
        }
        int shift = Integer.numberOfTrailingZeros(xor);
        int[] ans = new int[2];
        for (int i = 0; i < m; i++) {
            if (i < n) {
                ans[i >> shift & 1] ^= i;
            }
            ans[nums[i] >> shift & 1] ^= nums[i];
        }
        return ans;
    }
}
