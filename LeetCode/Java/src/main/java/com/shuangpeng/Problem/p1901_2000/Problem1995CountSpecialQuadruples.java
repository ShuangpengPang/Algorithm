package com.shuangpeng.Problem.p1901_2000;

import java.util.HashMap;
import java.util.Map;

public class Problem1995CountSpecialQuadruples {

    public int countQuadruplets0(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int a = 0; a < n - 3; ++a) {
            for (int b = a + 1; b < n - 2; ++b) {
                for (int c = b + 1; c < n - 1; ++c) {
                    for (int d = c + 1; d < n; ++d) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            ++ans;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int countQuadruplets1(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int c = n - 2; c >= 2; --c) {
            map.put(nums[c + 1], map.getOrDefault(nums[c + 1], 0) + 1);
            for (int b = c - 1; b >= 1; --b) {
                for (int a = b - 1; a >= 0; --a) {
                    ans += map.getOrDefault(nums[a] + nums[b] + nums[c], 0);
                }
            }
        }
        return ans;
    }

    public int countQuadruplets2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int b = n - 3; b >= 1; --b) {
            for (int d = b + 2; d < n; ++d) {
                map.put(nums[d] - nums[b + 1], map.getOrDefault(nums[d] - nums[b + 1], 0) + 1);
            }
            for (int a = b - 1; a >= 0; --a) {
                ans += map.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }

    public int countQuadruplets3(int[] nums) {
        int n = nums.length;
        final int M = 101;
        int[][][] dp = new int[n + 1][M][4];
        dp[0][0][0] = 1;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            for (int j = 0; j < M; ++j) {
                for (int k = 0; k < 4; ++k) {
                    dp[i + 1][j][k] = dp[i][j][k] + (j >= num && k >= 1 ? dp[i][j - num][k - 1] : 0);
                }
            }
            ans += dp[i][num][3];
        }
        return ans;
    }

    public int countQuadruplets4(int[] nums) {
        int n = nums.length;
        final int M = 101;
        int[][] dp = new int[M][4];
        dp[0][0] = 1;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            ans += dp[num][3];
            for (int j = M - 1; j >= 0; --j) {
                for (int k = 0; k < 4; ++k) {
                    dp[j][k] = dp[j][k] + (j >= num && k >= 1 ? dp[j - num][k - 1] : 0);
                }
            }
        }
        return ans;
    }

    public int countQuadruplets(int[] nums) {
        final int M = 101;
        int n  = nums.length;
        int[] dp1 = new int[M];
        int[] dp2 = new int[M];
        int[] dp3 = new int[M];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (i >= 2) {
                for (int j = 1; j <= 100 - x; ++j) {
                    if (dp2[j] > 0) {
                        dp3[j + x] += dp2[j];
                    }
                }
                ans += dp3[x];
            }
            if (i >= 1) {
                for (int j = 1; j <= 100 - x; ++j) {
                    if (dp1[j] > 0) {
                        dp2[j + x] += dp1[j];
                    }
                }
            }
            ++dp1[x];
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1995CountSpecialQuadruples a = new Problem1995CountSpecialQuadruples();
//        int[] nums = {28,8,49,85,37,90,20,8};
//        int i = a.countQuadruplets(nums);
//        int j = 1;
//    }
}
