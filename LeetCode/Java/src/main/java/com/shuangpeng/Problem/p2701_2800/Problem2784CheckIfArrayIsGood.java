package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2784CheckIfArrayIsGood（检查数组是否是好的）
 * @date 2024/4/7 12:33 AM
 */
public class Problem2784CheckIfArrayIsGood {

    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == n) {
                nums[i] = nums[n];
                nums[n] = n;
                break;
            }
        }
        if (nums[n] != n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == i + 1) {
                continue;
            }
            int num = nums[i];
            while (num > i + 1 && num <= n) {
                int tmp = nums[num - 1];
                if (tmp == num) {
                    return false;
                }
                nums[num - 1] = num;
                num = tmp;
            }
            if (num < i + 1 || num > n) {
                return false;
            }
            nums[i] = num;
        }
        return true;
    }
}
