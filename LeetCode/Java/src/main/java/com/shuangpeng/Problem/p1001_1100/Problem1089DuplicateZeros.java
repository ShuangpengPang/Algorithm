package com.shuangpeng.Problem.p1001_1100;

/**
 * @Description: Problem1089DuplicateZeros（复写零）
 * @Date 2022/6/17 1:34 PM
 * @Version 1.0
 */
public class Problem1089DuplicateZeros {

    public void duplicateZeros0(int[] arr) {
        int n = arr.length;
        for (int i = 0, j = 0; i < n; ++i, ++j) {
            int num = arr[i] >= 10 ? (arr[i] / 10 - 1) : arr[i];
            if (num == 0) {
                if (j < n) {
                    arr[j] = (arr[j] + 1) * 10;
                }
                ++j;
            }
            if (j < n) {
                arr[j] = (arr[j] + 1) * 10 + num;
            }
            arr[i] %= 10;
        }
    }

    public void duplicateZeros1(int[] arr) {
        int n = arr.length;
        int i = -1, top = 0;
        while (top < n) {
            ++i;
            if (arr[i] == 0) {
                top += 2;
            } else {
                ++top;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j] = 0;
            --i;
            --j;
        }
        while (j >= 0) {
            if (arr[i] == 0) {
                arr[j--] = 0;
            }
            arr[j--] = arr[i--];
        }
    }

    public void duplicateZeros2(int[] arr) {
        int n = arr.length;
        int i = -1, top = 0;
        while (top < n) {
            ++top;
            if (arr[++i] == 0) {
                ++top;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (i >= 0) {
            arr[j--] = arr[i];
            if (arr[i--] == 0) {
                arr[j--] = 0;
            }
        }
    }

    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int i = 0, j = 0;
        while (j < n) {
            if (arr[i++] == 0) {
                ++j;
            }
            ++j;
        }
        --i; --j;
        while (i >= 0) {
            if (j < n) {
                arr[j] = arr[i];
            }
            if (arr[i--] == 0) {
                arr[--j] = 0;
            }
            --j;
        }
    }
}
