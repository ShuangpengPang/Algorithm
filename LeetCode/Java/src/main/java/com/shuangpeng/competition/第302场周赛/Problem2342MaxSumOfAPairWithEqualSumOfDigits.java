package com.shuangpeng.competition.第302场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2342MaxSumOfAPairWithEqualSumOfDigits（数位和相等数对的最大和）
 * @Date 2022/7/23 11:44 AM
 * @Version 1.0
 */
public class Problem2342MaxSumOfAPairWithEqualSumOfDigits {

    public int maximumSum(int[] nums) {
        int ans = -1;
        Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums) {
            int digit = digitSum(num);
            map.putIfAbsent(digit, new int[2]);
            int[] arr = map.get(digit);
            if (num > arr[1]) {
                arr[0] = arr[1];
                arr[1] = num;
            } else if (num > arr[0]) {
                arr[0] = num;
            }
            if (arr[0] > 0 && arr[1] > 0) {
                ans = Math.max(ans, arr[0] + arr[1]);
            }
        }
        return ans;
    }

    private int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
