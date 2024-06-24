package com.shuangpeng.lcr.p101_200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR139TrainingPlan（训练计划I）
 * @date 2024/5/14 10:40 AM
 */
public class LCR139TrainingPlan {

    public int[] trainingPlan0(int[] actions) {
        int n = actions.length;
        Integer[] arr = new Integer[n];
        Arrays.setAll(arr, i -> actions[i]);
        Arrays.sort(arr, (a, b) -> (b & 1) - (a & 1));
        return Arrays.stream(arr).mapToInt(i -> i).toArray();
    }

    public int[] trainingPlan(int[] actions) {
        for (int n = actions.length, p = 0, i = 0; i < n; i++) {
            if ((actions[i] & 1) == 1) {
                swap(actions, p, i);
                p++;
            }
        }
        return actions;
    }

    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
