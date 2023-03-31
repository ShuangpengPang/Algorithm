package com.shuangpeng.competition.第301到310场周赛.第305场周赛;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem2367NumberOfArithmeticTriplets（算术三元组的数目）
 * @Date 2022/8/20 6:50 PM
 * @Version 1.0
 */
public class Problem2367NumberOfArithmeticTriplets {

    // 比赛时写法
    public int arithmeticTriplets0(int[] nums, int diff) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] - nums[i] < diff) {
                    continue;
                } else if (nums[j] - nums[i] > diff) {
                    break;
                }
                for (int k = j + 1; k < n; k++) {
                    int d = nums[k] - nums[j];
                    if (d < diff) {
                        continue;
                    } else if (d > diff) {
                        break;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    public int arithmeticTriplets1(int[] nums, int diff) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = 1, k = 2; k < n; i++) {
            while (j < n && nums[j] < nums[i] + diff) {
                j++;
            }
            if (j == n) {
                break;
            }
            while (k < n && nums[k] < nums[j] + diff) {
                k++;
            }
            if (k == n) {
                break;
            }
            if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                ans++;
            }
        }
        return ans;
    }

    public int arithmeticTriplets2(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int num : nums) {
            if (set.contains(num - diff) && set.contains(num - diff * 2)) {
                ans++;
            }
            set.add(num);
        }
        return ans;
    }

    public int arithmeticTriplets3(int[] nums, int diff) {
        boolean[] set = new boolean[201];
        int ans = 0;
        for (int num : nums) {
            if (num >= 2 * diff && set[num - 2 * diff] && set[num - diff]) {
                ans++;
            }
            set[num] = true;
        }
        return ans;
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        int N = 251, ans = 0;
        int[] map = new int[N];
        for (int num : nums) {
            int count = map[num - diff + 50];
            if (count >= 2) {
                ans++;
            }
            map[num + 50] = count + 1;
        }
        return ans;
    }
}
