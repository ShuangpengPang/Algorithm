package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Description: 划分为k个相等的子集
 * @Date 2022/9/20 11:32 AM
 **/
public class Problem0698PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets0(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxValue = Math.max(maxValue, nums[i]);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (maxValue > target) {
            return false;
        }
        final int MAX = 1 << n;
        int[] dp = new int[MAX];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int status = 0; status < MAX; status++) {
            if (dp[status] == -1) {
                continue;
            }
            for (int i = n - 1; i >= 0; i--) {
                int m = 1 << (n - i - 1);
                if ((status & m) != 0) {
                    continue;
                }
                int remain = target - dp[status] % target;
                if (nums[i] <= remain) {
                    int s = status | m;
                    dp[s] = dp[status] + nums[i];
                    if (dp[s] == sum - target) {
                        return true;
                    }
                }
            }
        }
        return dp[MAX - 1] != -1;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxValue = Math.max(maxValue, nums[i]);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (maxValue > target) {
            return false;
        }
        return dfs(nums, 0, 0, new Boolean[1 << n], sum - target, target);
    }

    private boolean dfs(int[] nums, int status, int sum, Boolean[] memo, int targetSum, int target) {
        if (memo[status] != null) {
            return memo[status];
        }
        if (sum == targetSum) {
            memo[status] = true;
            return true;
        }
        int remain = target - sum % target;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            int m = 1 << n - i - 1;
            if ((status & m) == 0 && nums[i] <= remain
                    && dfs(nums, status | m, sum + nums[i], memo, targetSum, target)) {
                memo[status] = true;
                return true;
            }
        }
        memo[status] = false;
        return false;
    }

//    public static void main(String[] args) {
//        int[] arr = new int[]{2, 2, 2, 2, 3, 4, 5};
//        Problem0698PartitionToKEqualSumSubsets a = new Problem0698PartitionToKEqualSumSubsets();
//        a.canPartitionKSubsets(arr, 4);
//    }
}

class Problem0698PartitionToKEqualSumSubsets0 {

    public boolean canPartitionKSubsets0(int[] nums, int k) {
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (max > target) {
            return false;
        }
        Arrays.sort(nums);
        int n = nums.length;
        return dfs(nums, 0, 0, k, 0, target, new HashSet<>());
    }

    private boolean dfs(int[] nums, int idx, int status, int k, int sum, int target, Set<Integer> memo) {
        if (k == 0) {
            return true;
        }
        if (memo.contains(status)) {
            return false;
        }
        int n = nums.length;
        if (idx == 0) {
            for (int i = 0; i < n; i++) {
                if ((status & (1 << i)) == 0) {
                    if (nums[i] == target) {
                        sum = 0;
                        k--;
                        idx = 0;
                    } else {
                        sum = nums[i];
                        idx = i + 1;
                    }
                    return dfs(nums, idx, status | (1 << i), k, sum, target, memo);
                }
            }
        }
        for (int i = idx; i < n; i++) {
            if ((status & (1 << i)) != 0 || sum + nums[i] > target) {
                continue;
            }
            if (i == 0 || nums[i] != nums[i - 1] || (status & (1 << (i - 1))) != 0) {
                if (sum + nums[i] == target && dfs(nums, 0, status | (1 << i), k - 1, 0, target, memo)) {
                    return true;
                } else if (sum + nums[i] < target && dfs(nums, i + 1, status | (1 << i), k, sum + nums[i], target, memo)) {
                    return true;
                }
            }
        }
        memo.add(status);
        return false;
    }

    public boolean canPartitionKSubsets1(int[] nums, int k) {
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (max > target) {
            return false;
        }
        int n = nums.length, N = 1 << n;
        int[] s = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    s[i] = nums[j] + s[i ^ (1 << j)];
                    break;
                }
            }
        }
        boolean[] dp = new boolean[N];
        dp[0] = true;
        for (int i = 1; i < N; i++) {
            if (s[i] % target != 0) {
                continue;
            }
            for (int j = i; j > 0; j = i & (j - 1)) {
                if (s[j] == target && dp[i ^ j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N - 1];
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (max > target) {
            return false;
        }
        int n = nums.length, N = 1 << n;
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    int m = i ^ (1 << j);
                    if (dp[m] != -1 && dp[m] + nums[j] <= target) {
                        dp[i] = (dp[m] + nums[j]) % target;
                        break;
                    }
                }
            }
        }
        return dp[N - 1] == 0;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, sum = 0, N = 1 << n;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / k;
        if (sum % k != 0 || nums[n - 1] > target) {
            return false;
        }
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] != -1) {
                for (int j = 0; j < n && dp[i] + nums[j] <= target; j++) {
                    int m = i | (1 << j);
                    if (m != i) {
                        int s = dp[i] + nums[j];
                        dp[m] = s == target ? 0 : s;
                    }
                }
            }
        }
        return dp[N - 1] == 0;
    }
}

// 模拟退火
class Problem0698PartitionToKEqualSumSubsets1 {
    int[] nums;
    int n, tval, k;
    Random random = new Random(20220920);
    double hi = 1e9, lo = 1e-4, fa = 0.95;
    int N = 600;
    boolean ans;
    int calc() {
        int diff = tval * k;
        for (int i = 0, j = 0; i < n && j < k; j++) {
            int cur = 0;
            while (i < n && cur + nums[i] <= tval) cur += nums[i++];
            diff -= cur;
        }
        if (diff == 0) ans = true;
        return diff;
    }
    void sa() {
        shuffle(nums);
        for (double t = hi; t > lo && !ans; t *= fa) {
            int a = random.nextInt(n), b = random.nextInt(n);
            if (a == b) continue;
            int prev = calc();
            swap(nums, a, b);
            int cur = calc();
            int diff = cur - prev;
            if (Math.log(diff / t) > random.nextDouble()) swap(nums, a, b);
        }
    }
    public boolean canPartitionKSubsets(int[] _nums, int _k) {
        nums = _nums; k = _k;
        int tot = 0;
        for (int x : nums) tot += x;
        if (tot % k != 0) return false;
        n = nums.length; tval = tot / k;
        while (!ans && N-- > 0) sa();
        return ans;
    }
    void shuffle(int[] nums) {
        for (int i = n; i > 0; i--) swap(nums, random.nextInt(i), i - 1);
    }
    void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }
}

