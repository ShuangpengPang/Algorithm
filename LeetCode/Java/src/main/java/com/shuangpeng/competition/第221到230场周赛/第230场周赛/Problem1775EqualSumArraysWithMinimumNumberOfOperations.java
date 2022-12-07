package com.shuangpeng.competition.第221到230场周赛.第230场周赛;

import java.util.Arrays;

/**
 * @description: 通过最少操作次数使数组的和相等
 * @date 2022/12/7 11:14 AM
 **/
public class Problem1775EqualSumArraysWithMinimumNumberOfOperations {

    public int minOperations0(int[] nums1, int[] nums2) {
        int sum1 = 0;
        for (int n : nums1) {
            sum1 += n;
        }
        int sum2 = 0;
        for (int n : nums2) {
            sum2 += n;
        }
        if (sum1 == sum2) {
            return 0;
        }
        int diff = sum2 - sum1;
        if (sum1 > sum2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            diff = sum1 - sum2;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Integer[] temp = new Integer[]{1, 2, 3};
        Arrays.sort(temp, (a, b) -> b - a);

        int i = 0, j = nums2.length - 1;
        int move = 0;
        while ((i < nums1.length || j >= 0) && diff > 0) {
            if (i >= nums1.length) {
                diff -= (nums2[j] - 1);
                j--;
                move++;
            } else if (j < 0) {
                diff -= (6 - nums1[i]);
                i++;
                move++;
            } else {
                int a = 6 - nums1[i];
                int b = nums2[j] - 1;
                if (diff <= a || diff <= b) {
                    move++;
                    return move;
                }
                if (a > b) {
                    diff -= a;
                    move++;
                    i++;
                } else {
                    diff -= b;
                    move++;
                    j--;
                }
            }
        }
        return diff <= 0 ? move : -1;
    }

//    int minOperations(vector<int>& nums1, vector<int>& nums2) {
//        int sum1 = accumulate(nums1.begin(), nums1.end(), 0);
//        int sum2 = accumulate(nums2.begin(), nums2.end(), 0);
//        if (sum1 == sum2) {
//            return 0;
//        }
//        if (sum1 > sum2) {
//            return minOperations(nums2, nums1);
//        }
//
//        int diff = sum2 - sum1;
//        vector<int> freq(6);
//        for (int num: nums1) {
//            ++freq[6 - num];
//        }
//        for (int num: nums2) {
//            ++freq[num - 1];
//        }
//
//        int ans = 0;
//        for (int i = 5; i >= 1 && diff > 0; --i) {
//            while (freq[i] && diff > 0) {
//                ++ans;
//                --freq[i];
//                diff -= i;
//            }
//        }
//
//        return (diff > 0 ? -1 : ans);
//    }

    public int minOperations(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return -1;
        }
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if (sum1 == sum2) {
            return 0;
        } else if (sum1 > sum2) {
            return minOperations(nums2, nums1);
        }
        int diff = sum2 - sum1;
        int[] a = new int[6];
        for (int num : nums1) {
            a[6 - num]++;
        }
        for (int num : nums2) {
            a[num - 1]++;
        }
        int i = 5;
        int answer = 0;
        while (i >= 0 && diff > 0) {
            if (a[i] > 0) {
                diff -= i;
                a[i]--;
                answer++;
            }
            if (a[i] <= 0) {
                i--;
            }
        }
        return diff <= 0 ? answer : -1;
    }
}

class Problem1775EqualSumArraysWithMinimumNumberOfOperations0 {

    public int minOperations0(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 * 6 < n2 || n2 * 6 < n1) {
            return -1;
        }
        int[] cnt1 = new int[6], cnt2 = new int[6];
        int s1 = 0, s2 = 0;
        for (int num : nums1) {
            cnt1[num - 1]++;
            s1 += num;
        }
        for (int num : nums2) {
            cnt2[num - 1]++;
            s2 += num;
        }
        if (s1 > s2) {
            int t = s1;
            s1 = s2;
            s2 = t;
            int[] cnt = cnt1;
            cnt1 = cnt2;
            cnt2 = cnt;
        }
        int d = s2 - s1, ans = 0;
        for (int i = 0, j = 5; i < 5 && d > 0; i++, j--) {
            int add = 5 - i;
            int c = Math.min((d - 1) / add + 1, cnt1[i]);
            ans += c;
            d -= c * add;
            if (d <= 0) {
                break;
            }
            c = Math.min((d - 1) / add + 1, cnt2[j]);
            ans += c;
            d -= c * add;
        }
        return ans;
    }

    public int minOperations(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 * 6 < n2 || n2 * 6 < n1) {
            return -1;
        }
        int[] cnt1 = new int[6], cnt2 = new int[6];
        int diff = 0;
        for (int num : nums1) {
            cnt1[num - 1]++;
            diff += num;
        }
        for (int num : nums2) {
            cnt2[num - 1]++;
            diff -= num;
        }
        if (diff == 0) {
            return 0;
        }
        if (diff > 0) {
            return getCount(cnt1, cnt2, diff);
        }
        return getCount(cnt2, cnt1, -diff);
    }

    private int getCount(int[] cnt1, int[] cnt2, int diff) {
        for (int i = 0; i < 6; i++) {
            cnt1[5 - i] += cnt2[i];
        }
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; i--) {
            int c = Math.min((diff + i - 1) / i, cnt1[i]);
            ans += c;
            diff -= c * i;
        }
        return ans;
    }
}