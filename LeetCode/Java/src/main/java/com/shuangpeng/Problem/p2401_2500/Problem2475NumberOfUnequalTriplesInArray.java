package com.shuangpeng.Problem.p2401_2500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2475NumberOfUnequalTriplesInArray（数组中不等三元组的数目）
 * @date 2023/6/13 9:55 AM
 */
public class Problem2475NumberOfUnequalTriplesInArray {

    public int unequalTriplets0(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] != nums[j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[k] != nums[i] && nums[k] != nums[j]) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int unequalTriplets1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && nums[j] == nums[i]) {
                j++;
            }
            ans += i * (j - i) * (n - j);
        }
        return ans;
    }

    public int unequalTriplets2(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] cnt = new int[max + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        int ans = 0, n = nums.length;
        for (int i = 0, c = 0; i <= max; i++) {
            if (cnt[i] > 0) {
                ans += c * cnt[i] * (n - c - cnt[i]);
                c += cnt[i];
            }
        }
        return ans;
    }

    public int unequalTriplets(int[] nums) {
        int res = 0, pairs = 0, i = 1, n = nums.length;
        int[] cnt = new int[1001];
        cnt[nums[0]] = 1;
        do {
            int count = cnt[nums[i]]++, others = i - count;
            res += pairs - count * others;
            pairs += others;
        } while (++i < n);
        return res;
    }
}
