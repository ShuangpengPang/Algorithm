package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1493LongestSubarrayOf1sAfterDeletingOneElement（删除一个元素以后全为1的最长子数组）
 * @date 2023/9/8 2:11 PM
 */
public class Problem1493LongestSubarrayOf1sAfterDeletingOneElement {

    public int longestSubarray0(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int s = i;
            while (i < n && nums[i] == 1) {
                i++;
            }
            if (i == n) {
                ans = Math.max(ans, i - s + (s > 0 ? 1 : 0));
                break;
            }
            int j = i + 1;
            while (j < n && nums[j] == 1) {
                j++;
            }
            ans = Math.max(ans, j - s);
        }
        return Math.max(ans - 1, 0);
    }

    public int longestSubarray1(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int p = i;
                while (i < n && nums[i] == 1) {
                    i++;
                }
                int j = i + 1;
                while (j < n && nums[j] == 1) {
                    j++;
                }
                ans = Math.max(ans, j - p - 1);
            }
        }
        return Math.min(ans, n - 1);
    }

    public int longestSubarray2(int[] nums) {
        int cnt1 = 0, cnt2 = 0, ans = 0;
        for (int num : nums) {
            if (num == 1) {
                cnt1++;
                cnt2++;
            } else {
                cnt2 = cnt1;
                cnt1 = 0;
            }
            ans = Math.max(ans, cnt2);
        }
        return Math.min(ans, nums.length - 1);
    }

    public int longestSubarray(int[] nums) {
        int n = nums.length, sum = 0;
        int l = 0, r = 0;
        for (r = 0; r < n; r++) {
            sum += nums[r];
            if (sum < r - l) {
                sum -= nums[l++];
            }
        }
        return r - l - 1;
    }
}
