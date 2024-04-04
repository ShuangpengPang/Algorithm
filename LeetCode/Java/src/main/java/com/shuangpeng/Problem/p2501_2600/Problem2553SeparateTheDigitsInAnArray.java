package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2553SeparateTheDigitsInAnArray（分割数组中数字的数位）
 * @date 2024/4/4 6:58 PM
 */
public class Problem2553SeparateTheDigitsInAnArray {

    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = nums[i];
            if (x == 0) {
                list.add((x));
            } else {
                while (x != 0) {
                    list.add(x % 10);
                    x /= 10;
                }
            }
        }
        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = list.get(size - i - 1);
        }
        return ans;
    }
}
