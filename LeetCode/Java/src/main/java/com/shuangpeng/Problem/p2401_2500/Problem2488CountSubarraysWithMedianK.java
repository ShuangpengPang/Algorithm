package com.shuangpeng.Problem.p2401_2500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2488CountSubarraysWithMedianK（统计中位数为K的子数组）
 * @date 2022/12/15 12:07 PM
 */
public class Problem2488CountSubarraysWithMedianK {

    public int countSubarrays0(int[] nums, int k) {
        int n = nums.length, idx = -1;
        int cnt = 0, ans = 0;
        Map<Integer, Integer> even = new HashMap<>(), odd = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (idx == -1) {
                int j = (cnt << 1) - i;
                Map<Integer, Integer> map = (i & 1) == 0 ? even : odd;
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
            cnt += nums[i] < k ? 1 : 0;
            if (nums[i] == k) {
                idx = i;
            }
            if (idx != -1) {
                int j = (cnt << 1) - i - 1;
                Map<Integer, Integer> m1 = (i & 1) == 0 ? odd : even;
                Map<Integer, Integer> m2 = (i & 1) == 0 ? even : odd;
                ans += m1.getOrDefault(j + 2, 0);
                ans += m2.getOrDefault(j + 1, 0);
            }
        }
        return ans;
    }

    public int countSubarrays1(int[] nums, int k) {
        int n = nums.length, pos = 0;
        while (nums[pos] != k) {
            pos++;
        }
        Map<Integer, Integer> diff = new HashMap<>();
        diff.put(0, 1);
        for (int i = pos + 1, c = 0; i < n; i++) {
            c += nums[i] > k ? 1 : -1;
            diff.put(c, diff.getOrDefault(c, 0) + 1);
        }
        int ans = 0;
        for (int i = pos, c = 0; i >= 0; i--) {
            c += nums[i] < k ? 1 : (nums[i] > k ? -1 : 0);
            ans += diff.getOrDefault(c, 0) + diff.getOrDefault(c + 1, 0);
        }
        return ans;
    }

    public int countSubarrays(int[] nums, int k) {
        int n = nums.length, s = n, ans = 0;
        int[] cnt = new int[n << 1];
        boolean find = false;
        for (int i = 0; i < n; i++) {
            if (!find) {
                cnt[s]++;
            }
            if (nums[i] == k) {
                find = true;
            } else {
                s += nums[i] > k ? 1 : -1;
            }
            if (find) {
                ans += cnt[s] + cnt[s - 1];
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem2488CountSubarraysWithMedianK a = new Problem2488CountSubarraysWithMedianK();
//        int[] nums = {3, 2, 1, 4, 5};
//        a.countSubarrays(nums, 4);
//    }
}

//class Solution {
//    public:
//    int countSubarrays(vector<int>& nums, int K) {
//        int n = nums.size();
//
//        // 求中位数大等于 LIM 的子数组有几个
//        auto gao = [&](int LIM) {
//            // 把每个数改成 1 或 -1
//            vector<int> vec;
//            for (int x : nums) {
//                if (x >= LIM) vec.push_back(1);
//                else vec.push_back(-1);
//            }
//
//            // 用数组维护 d 的值，由于下标取值范围是 [-n, n]，所以加一个偏移量 n
//            int d[n * 2 +1];
//            memset(d, 0, sizeof(d));
//            d[n] = 1;
//
//            long long ret = 0;
//            for (int i = 0, s = n, c = 0; i < n; i++) {
//                // 分类讨论 c 的变化情况
//                if (vec[i] == 1) {
//                    c += d[s];
//                    s++;
//                } else {
//                    c -= d[s - 1];
//                    s--;
//                }
//                ret += c;
//                d[s]++;
//            }
//            return ret;
//        };
//
//        return gao(K) - gao(K + 1);
//    }
//};
