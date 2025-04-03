package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3366MinimumArraySum（最小数组和）
 * @date 2025/4/2 15:20
 */
public class Problem3366MinimumArraySum {

    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length, N = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[op1 + 1][op2 + 1];
        for (int i = 0; i <= op1; i++) {
            Arrays.fill(dp[i], N);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int o1 = Math.min(op1, i); o1 >= 0; o1--) {
                for (int o2 = Math.min(op2, i); o2 >= 0; o2--) {
                    dp[o1][o2] += nums[i - 1];
                    int x = nums[i - 1] - (nums[i - 1] >> 1);
                    if (o1 > 0) {
                        dp[o1][o2] = Math.min(dp[o1][o2], dp[o1 - 1][o2] + x);
                    }
                    if (nums[i - 1] >= k && o2 > 0) {
                        dp[o1][o2] = Math.min(dp[o1][o2], dp[o1][o2 - 1] + nums[i - 1] - k);
                    }
                    if (o1 > 0 && o2 > 0 && nums[i - 1] >= k) {
                        int y = nums[i - 1] - k;
                        dp[o1][o2] = Math.min(dp[o1][o2], dp[o1 - 1][o2 - 1] + y - (y >> 1));
                        if (x >= k) {
                            dp[o1][o2] = Math.min(dp[o1][o2], dp[o1 - 1][o2 - 1] + x - k);
                        }
                    }
                }
            }
        }
        int ans = N;
        for (int i = 0; i <= op1; i++) {
            for (int j = 0; j <= op2; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }
        return ans;
    }
}

class Problem3366MinimumArraySum0 {
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int[][] f = new int[op1 + 1][op2 + 1];
        for (int x : nums) {
            for (int p = op1; p >= 0; p--) {
                for (int q = op2; q >= 0; q--) {
                    int res = f[p][q] + x;
                    if (p > 0) {
                        res = Math.min(res, f[p - 1][q] + (x + 1) / 2);
                    }
                    if (q > 0 && x >= k) {
                        res = Math.min(res, f[p][q - 1] + x - k);
                        if (p > 0) {
                            int y = (x + 1) / 2 >= k ? (x + 1) / 2 - k : (x - k + 1) / 2;
                            res = Math.min(res, f[p - 1][q - 1] + y);
                        }
                    }
                    f[p][q] = res;
                }
            }
        }
        return f[op1][op2];
    }
}

class Problem3366MinimumArraySum1 {
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        Arrays.sort(nums);
        int high = lowerBound(nums, k * 2 - 1);
        int low = lowerBound(nums, k);

        // 在 [2k-1,∞) 中的数，直接先除再减（从大到小操作）
        for (int i = nums.length - 1; i >= high; i--) {
            if (op1 > 0) {
                nums[i] = (nums[i] + 1) / 2;
                op1--;
            }
            if (op2 > 0) {
                nums[i] -= k;
                op2--;
            }
        }

        // 在 [k,2k-2] 中的数，先把小的数 -k
        Map<Integer, Integer> cnt = new HashMap<>();
        int odd = 0;
        for (int i = low; i < high; i++) {
            if (op2 > 0) {
                nums[i] -= k;
                if (k % 2 > 0 && nums[i] % 2 > 0) {
                    // nums[i] 原来是偶数，后面有机会把这次 -k 操作留给奇数，得到更小的答案
                    cnt.merge(nums[i], 1, Integer::sum); // cnt[nums[i]]++
                }
                op2--;
            } else {
                odd += nums[i] % 2; // 没有执行 -k 的奇数
            }
        }

        // 重新排序（注：这里可以改用合并两个有序数组的做法）
        Arrays.sort(nums, 0, high);

        int ans = 0;
        if (k % 2 > 0) {
            // 调整，对于 [k,2k-2] 中 -k 后还要再 /2 的数，如果原来是偶数，改成给奇数 -k 再 /2，这样答案可以减一
            for (int i = high - op1; i < high && odd > 0; i++) {
                int x = nums[i];
                if (cnt.containsKey(x)) {
                    if (cnt.merge(x, -1, Integer::sum) == 0) { // --cnt[x] == 0
                        cnt.remove(x);
                    }
                    odd--;
                    ans--;
                }
            }
        }

        // 最后，从大到小执行操作 1
        for (int i = high - 1; i >= 0 && op1 > 0; i--) {
            nums[i] = (nums[i] + 1) / 2;
            op1--;
        }

        for (int x : nums) {
            ans += x;
        }
        return ans;
    }

    // 见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
