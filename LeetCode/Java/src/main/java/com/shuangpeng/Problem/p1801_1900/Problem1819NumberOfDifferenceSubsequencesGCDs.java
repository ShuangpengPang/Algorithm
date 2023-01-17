package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1819NumberOfDifferenceSubsequencesGCDs（序列中不同最大公约数的数目）
 * @date 2023/1/16 6:33 PM
 */
public class Problem1819NumberOfDifferenceSubsequencesGCDs {

    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        boolean[] map = new boolean[max + 1];
        for (int num : nums) {
            map[num] = true;
        }
        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int g = 0;
            for (int x = i; (g == 0 || g > i) && x <= max; x += i) {
                if (map[x]) {
                    g = g == 0 ? x : gcd(g, x);
                }
            }
            if (g == i) {
                ans++;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
