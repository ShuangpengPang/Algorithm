package com.shuangpeng.interview;

/**
 * @Description: Question1719MissingTwo（消失的两个数字）
 * @Date 2022/9/26 11:27 AM
 * @Version 1.0
 */
public class Question1719MissingTwo {

    public int[] missingTwo(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] <= n && nums[i] != i + 1) {
                int j = nums[i] - 1;
                nums[i] = nums[j];
                nums[j] = j + 1;
            }
        }
        int a = -1, b = -1, c = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                if (a == -1) {
                    a = i + 1;
                } else {
                    b = i + 1;
                }
                c = nums[i];
            }
        }
        if (a == -1) {
            a = n + 1;
            b = n + 2;
        } else if (b == -1) {
            b = (n << 1) + 3 - c;
        }
        return new int[]{a, b};
    }
}
