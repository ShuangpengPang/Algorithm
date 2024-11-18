package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3265CountAlmostEqualPairsI（统计近似相等数对I）
 * @date 2024/11/18 5:07 PM
 */
public class Problem3265CountAlmostEqualPairsI {

    public int countPairs0(int[] nums) {
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
        int t1 = -1, t2 = -1;
        while (x != y) {
            int a = x % 10, b = y % 10;
            x /= 10;
            y /= 10;
            if (a != b) {
                if (t1 == -1) {
                    t1 = a;
                    t2 = b;
                } else if (t1 == b && t2 == a && x == y) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return t1 == -1 ? 1 : 0;
    }

    public int countPairs(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            ans += getCount(map, num);
            map.merge(num, 1, Integer::sum);
        }
        return ans;
    }

    private int getCount(Map<Integer, Integer> map, int num) {
        int[] digit = new int[10];
        int cnt = 0;
        int ans = map.getOrDefault(num, 0);
        while (num != 0) {
            digit[cnt++] = num % 10;
            num /= 10;
        }
        for (int i = 1; i < cnt; i++) {
            for (int j = 0; j < i; j++) {
                if (digit[i] != digit[j]) {
                    swap(digit, i, j);
                    ans += map.getOrDefault(toNumber(digit, cnt), 0);
                    swap(digit, i, j);
                }
            }
        }
        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    private int toNumber(int[] digit, int n) {
        int ans = 0;
        for (int i = 0, j = 1; i < n; i++, j *= 10) {
            ans += digit[i] * j;
        }
        return ans;
    }
}
