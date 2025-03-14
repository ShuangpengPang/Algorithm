package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3258CountSubstringsThatSatisfyKConstraintI（统计满足K约束的子字符串数量I）
 * @date 2024/11/11 3:32 PM
 */
public class Problem3258CountSubstringsThatSatisfyKConstraintI {

    public int countKConstraintSubstrings0(String s, int k) {
        char[] cs = s.toCharArray();
        return (int) ((long) getCount(cs, k, new char[]{'0'}) + getCount(cs, k, new char[]{'1'}) - getCount(cs, k, new char[]{'0', '1'}));
    }

    private int getCount(char[] cs, int k, char[] arr) {
        int n = cs.length, m = arr.length;
        int[] count = new int[m];
        int ans = 0;
        for (int i = 0, j = 0; j < n; j++) {
            calculateCount(arr, count, cs[j], 1);
            while (!check(count, k)) {
                calculateCount(arr, count, cs[i], -1);
                i++;
            }
            ans += j - i + 1;
        }
        return ans;
    }

    private void calculateCount(char[] arr, int[] count, char c, int add) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == c) {
                count[i] += add;
                break;
            }
        }
    }

    private boolean check(int[] count, int k) {
        int n = count.length;
        for (int i = 0; i < n; i++) {
            if (count[i] > k) {
                return false;
            }
        }
        return true;
    }

    public int countKConstraintSubstrings1(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0, ones = 0;
        for (int i = 0, j = 0; j < n; j++) {
            ones += cs[j] - '0';
            while (ones > k && (j - i + 1 - ones) > k) {
                ones -= cs[i++] - '0';
            }
            ans += j - i + 1;
        }
        return ans;
    }

    public int countKConstraintSubstrings2(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] cnt = new int[2];
        for (int i = 0, j = 0; j < n; j++) {
            cnt[cs[j] & 1]++;
            while (cnt[0] > k && cnt[1] > k) {
                cnt[cs[i++] & 1]--;
            }
            ans += j - i + 1;
        }
        return ans;
    }

    public int countKConstraintSubstrings(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] right = new int[n], preSum = new int[n + 1];
        Arrays.fill(right, n);
        for (int i = 0, j = 0, ones = 0; j < n; j++) {
            ones += cs[j] - '0';
            while (ones > k && j - i - ones + 1 > k) {
                right[i] = j;
                ones -= cs[i++] - '0';
            }
            preSum[j + 1] = preSum[j] + j - i + 1;
        }
        int part1 = (int) ((long) (right[0] - 0 + 1) * (right[0] - 0) / 2);
        int part2 = preSum[n] - preSum[right[0]];
        return part1 + part2;
    }
}
