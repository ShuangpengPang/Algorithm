package com.shuangpeng.Problem.p0901_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0992SubarraysWithKDifferentIntegers
 * @Date 2022/5/6 4:54 PM
 * @Version 1.0
 */
public class Problem0992SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct0(int[] nums, int k) {
        int n = nums.length;
        int[] indices = new int[n + 1];
        Set<Integer> set = new HashSet<>(k);
        int ans = 0;
        for (int i = 0, left = 0, right = 0; i < n; ++i) {
            int num = nums[i];
            indices[num] = i;
            if (set.size() == k && !set.contains(num)) {
                set.remove(nums[right]);
                left = ++right;
            }
            while (right != indices[nums[right]]) {
                ++right;
            }
            set.add(num);
            if (set.size() == k) {
                ans += right - left + 1;
            }
        }
        return ans;
    }

    public int subarraysWithKDistinct1(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    /**
     * @param nums
     * @param k
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostKDistinct(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[n + 1];
        int res = 0;
        int count = 0;
        for (int left = 0, right = 0; right < n; ++right) {
            if (freq[nums[right]] == 0) {
                ++count;
            }
            ++freq[nums[right]];
            while (count > k) {
                --freq[nums[left]];
                if (freq[nums[left]] == 0) {
                    --count;
                }
                ++left;
            }
            res += right - left + 1;
        }
        return res;
    }

    public int subarraysWithKDistinct2(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        int[] freq = new int[n + 1];
        int[] lastIndex = new int[n + 1];
        int ans = 0;
        for (int left = 0, right = 0, i = 0; i < n; ++i) {
            lastIndex[nums[i]] = i;
            if (freq[nums[i]] == 0) {
                ++count;
            }
            ++freq[nums[i]];
            while (count > k) {
                --freq[nums[left]];
                if (freq[nums[left]] == 0) {
                    --count;
                    right = left + 1;
                }
                ++left;
            }
            while (lastIndex[nums[right]] != right) {
                ++right;
            }
            if (count == k) {
                ans += right - left + 1;
            }
        }
        return ans;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int[] num1 = new int[n + 1];
        int[] num2 = new int[n + 1];
        int tot1 = 0, tot2 = 0;
        int left1 = 0, left2 = 0, right = 0;
        int ret = 0;
        while (right < n) {
            if (num1[nums[right]] == 0) {
                tot1++;
            }
            num1[nums[right]]++;
            if (num2[nums[right]] == 0) {
                tot2++;
            }
            num2[nums[right]]++;
            while (tot1 > k) {
                num1[nums[left1]]--;
                if (num1[nums[left1]] == 0) {
                    tot1--;
                }
                left1++;
            }
            while (tot2 > k - 1) {
                num2[nums[left2]]--;
                if (num2[nums[left2]] == 0) {
                    tot2--;
                }
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

//    public static void main(String[] args) {
//        Problem0992SubarraysWithKDifferentIntegers a = new Problem0992SubarraysWithKDifferentIntegers();
//        a.subarraysWithKDistinct(new int[]{1, 2}, 1);
//    }
}
