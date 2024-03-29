package com.shuangpeng.Problem.p0401_0500;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Problem0446ArithmeticSlicesII {

    public int numberOfArithmeticSlices0(int[] nums) {
        int n = nums.length;
        Map<Long, Map<Integer, Integer>>[] dp = new Map[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            dp[i].put(0L, new HashMap<Integer, Integer>() {{
                put(1, 1);
            }});
            for (int j = i - 1; j >= 0; j--) {
                long d = (long) nums[i] - nums[j];
                if (!dp[i].containsKey(d)) {
                    dp[i].put(d, new HashMap<>());
                }
                Map<Integer, Integer> map = dp[i].get(d);
                if (dp[j].containsKey(d)) {
                    Map<Integer, Integer> m = dp[j].get(d);
                    Set<Map.Entry<Integer, Integer>> entries = m.entrySet();
                    for (Map.Entry<Integer, Integer> entry : entries) {
                        int length = entry.getKey() + 1;
                        int count = entry.getValue();
                        if (length >= 3) {
                            answer += count;
                        }
                        map.put(length, map.getOrDefault(length, 0) + count);
                    }
                }
                if (d != 0) {
                    map.put(2, map.getOrDefault(2, 0) + 1);
                }
            }
        }
        return answer;
    }

    public int numberOfArithmeticSlices1(int[] A) {
        int n = A.length;
        long ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - (long) A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = cnt[j].getOrDefault(diff, 0);
                int origin = cnt[i].getOrDefault(diff, 0);
                cnt[i].put(diff, origin + sum + 1);
                ans += sum;
            }
        }
        return (int) ans;
    }

    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer>[] dp = new Map[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long delta = (long) nums[i] - (long) nums[j];
                if (delta <= Integer.MIN_VALUE || delta >= Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int old = dp[j].getOrDefault(diff, 0);
                int cur = dp[i].getOrDefault(diff, 0);
                dp[i].put(diff, old + cur + 1);
                answer += old;
            }
        }
        return answer;
    }

    public int numberOfArithmeticSlices3(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] diffMaps = new Map[n];
        for (int i = 0; i < n; ++i) {
            diffMaps[i] = new HashMap<>();
        }
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                long d = (long) nums[i] - nums[j];
                diffMaps[i].put(d, diffMaps[i].getOrDefault(d, 0) + 1);
            }
        }
        Map<Long, Integer>[] countMaps = new Map[n];
        for (int i = 0; i < n; ++i) {
            countMaps[i] = new HashMap<>();
        }
        int total = 0;
        for (int i = 2; i < n; ++i) {
            for (int j = i - 1; j > 0; j--) {
                long d = (long) nums[i] - nums[j];
                int count = countMaps[j].getOrDefault(d, 0);
                count += diffMaps[j].getOrDefault(d, 0);
                countMaps[i].put(d, countMaps[i].getOrDefault(d, 0) + count);
                total += count;
            }
        }
        return total;
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = new HashMap<>();
        }
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = (long) nums[i] - nums[j];
                int count = dp[j].getOrDefault(d, 0);
                ans += count;
                dp[i].put(d, dp[i].getOrDefault(d, 0) + count + 1);
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0446ArithmeticSlicesII a = new Problem0446ArithmeticSlicesII();
//        a.numberOfArithmeticSlices(new int[]{2, 2, 3, 4});
//    }
}
