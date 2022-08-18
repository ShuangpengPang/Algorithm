package com.shuangpeng.Problem.p1201_1300;

import java.util.*;

/**
 * @Description: Problem1224MaximumEqualFrequency（最大相等频率）
 * @Date 2022/7/25 5:05 PM
 * @Version 1.0
 */
public class Problem1224MaximumEqualFrequency {

    public int maxEqualFreq0(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> freqCount = new HashMap<>();
        int ans = 0, n = nums.length;
        int min = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int freq = freqMap.getOrDefault(nums[i], 0);
            if (freqCount.containsKey(freq)) {
                int count = freqCount.get(freq);
                if (count > 1) {
                    freqCount.put(freq, count - 1);
                } else {
                    freqCount.remove(freq);
                    if (freq == min) {
                        min++;
                    }
                }
            }
            freqMap.put(nums[i], freq + 1);
            freqCount.put(freq + 1, freqCount.getOrDefault(freq + 1, 0) + 1);
            max = Math.max(max, freq + 1);
            if (freq == 0) {
                min = 1;
            }
            if (freqCount.size() == 1) {
                if (freqCount.get(max) == 1 || max == 1) {
                    ans = i + 1;
                }
            } else if (freqCount.size() == 2) {
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                for (int key : freqCount.keySet()) {
                    min = Math.min(min, key);
                    max = Math.max(max, key);
                }
                if (min + 1 == max && freqCount.get(max) == 1 || min == 1 && freqCount.get(min) == 1) {
                    ans = i + 1;
                }
            }
        }
        return ans;
    }

    public int maxEqualFreq1(int[] nums) {
        int[] cnt = new int[(int) (1e5) + 1];
        int maxFreq = 0, maxN = 0, N = 0, ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (cnt[num]++ == 0) {
                N++;
            }
            if (cnt[num] > maxFreq) {
                maxFreq = cnt[num];
                maxN = 1;
            } else if (cnt[num] == maxFreq) {
                maxN++;
            }
            if (maxFreq == 1 || maxFreq * maxN == i || maxN == 1 && maxFreq == i / N + 1) {
                ans = i + 1;
            }
        }
        return ans;
    }

    public int maxEqualFreq2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Set<Integer>> sets = new HashMap<>();
        int n = nums.length, min = 1, max = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            int old = freq.getOrDefault(nums[i], 0);
            if (old != 0) {
                Set<Integer> set = sets.get(old);
                set.remove(nums[i]);
                if (set.isEmpty()) {
                    sets.remove(old);
                    if (old == min) {
                        min++;
                    }
                }
            }
            int f = old + 1;
            min = Math.min(min, f);
            max = Math.max(max, f);
            freq.put(nums[i], f);
            sets.computeIfAbsent(f, k -> new HashSet<>()).add(nums[i]);
            if (sets.size() == 1 && (max == 1 || sets.get(max).size() == 1)) {
                ans = i + 1;
            } else if (sets.size() == 2 && (max == min + 1 && sets.get(max).size() == 1 || min == 1 && sets.get(1).size() == 1)) {
                ans = i + 1;
            }
        }
        return ans;
    }

    public int maxEqualFreq3(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), freqCount = new HashMap<>();
        int maxFreq = 0, n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int f = freq.getOrDefault(nums[i], 0) + 1;
            freq.put(nums[i], f);
            if (f > 1) {
                freqCount.put(f - 1, freqCount.get(f - 1) - 1);
            }
            freqCount.put(f, freqCount.getOrDefault(f, 0) + 1);
            maxFreq = Math.max(maxFreq, f);
            boolean valid = maxFreq == 1 || freqCount.get(maxFreq) == 1 && maxFreq + freqCount.getOrDefault(maxFreq - 1, 0) * (maxFreq - 1) == i + 1
                    || freqCount.get(maxFreq) * maxFreq == i && freqCount.getOrDefault(1, 0) == 1;
            if (valid) {
                ans = i + 1;
            }
        }
        return ans;
    }

    public int maxEqualFreq(int[] nums) {
        int n = nums.length, ans = 0, maxFreq = 0, N = 0;
        int M = n;
        for (int num : nums) {
            M = Math.max(M, num);
        }
        int[] freq = new int[M + 1], cnt = new int[M + 1];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (freq[num]++ == 0) {
                N++;
            }
            cnt[freq[num]]++;
            cnt[freq[num] - 1]--;
            maxFreq = Math.max(maxFreq, freq[num]);
            if (maxFreq == 1 || cnt[maxFreq - 1] == N - 1 || cnt[maxFreq] == N - 1 && cnt[1] == 1) {
                ans = i + 1;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1224MaximumEqualFrequency a = new Problem1224MaximumEqualFrequency();
//        int[] nums = {10,2,8,9,3,8,1,5,2,3,7,6};
//        a.maxEqualFreq(nums);
//    }
}

