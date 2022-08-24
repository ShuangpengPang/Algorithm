package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1460MakeTwoArraysEqualByReversingSubArrays（通过翻转子数组使两个数组相等）
 * @Date 2022/8/24 10:02 AM
 * @Version 1.0
 */
public class Problem1460MakeTwoArraysEqualByReversingSubArrays {

    public boolean canBeEqual0(int[] target, int[] arr) {
        int max = 0;
        for (int num : target) {
            max = Math.max(max, num);
        }
        int[] cnt = new int[max + 1];
        int count = 0;
        for (int num : target) {
            if (cnt[num]++ == 0) {
                count++;
            }
        }
        for (int num : arr) {
            if (num > max) {
                return false;
            }
            cnt[num]--;
            if (cnt[num] < 0) {
                return false;
            } else if (cnt[num] == 0) {
                count--;
            }
        }
        return count == 0;
    }

    public boolean canBeEqual1(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }

    public boolean canBeEqual2(int[] target, int[] arr) {
        int max = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, target[i]);
            max = Math.max(max, arr[i]);
        }
        int[] cnt = new int[max + 1];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (++cnt[target[i]] == 1) {
                count++;
            }
            if (--cnt[arr[i]] == 0) {
                count--;
            }
        }
        return count == 0;
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        int max = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, target[i]);
            max = Math.max(max, arr[i]);
        }
        int[] cnt = new int[max + 1];
        for (int num : target) {
            cnt[num]++;
        }
        for (int num : arr) {
            if (--cnt[num] < 0) {
                return false;
            }
        }
        return true;
    }
}
