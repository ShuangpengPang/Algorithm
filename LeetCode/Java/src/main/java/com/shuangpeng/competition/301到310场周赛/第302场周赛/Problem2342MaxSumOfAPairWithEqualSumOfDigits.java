package com.shuangpeng.competition.第302场周赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem2342MaxSumOfAPairWithEqualSumOfDigits（数位和相等数对的最大和）
 * @Date 2022/7/23 11:44 AM
 * @Version 1.0
 */
public class Problem2342MaxSumOfAPairWithEqualSumOfDigits {

    // 比赛时写法
    public int maximumSum0(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int s = getSum(num);
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(num);
        }
        int ans = -1;
        for (int s : map.keySet()) {
            List<Integer> list = map.get(s);
            if (list.size() > 1) {
                list.sort((a, b) -> b - a);
                ans = Math.max(ans, list.get(0) + list.get(1));
            }
        }
        return ans;
    }

    private int getSum(int num) {
        int ans = 0;
        while (num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    public int maximumSum(int[] nums) {
        int ans = -1;
        Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums) {
            int digit = digitSum(num);
            map.putIfAbsent(digit, new int[2]);
            int[] arr = map.get(digit);
            if (arr[0] < num) {
                arr[0] = num;
                if (arr[1] < num) {
                    arr[0] = arr[1];
                    arr[1] = num;
                }
                if (arr[0] > 0 && arr[1] > 0) {
                    ans = Math.max(ans, arr[0] + arr[1]);
                }
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
