package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0866PrimePalindrome（回文素数）
 * @date 2022/11/22 6:20 PM
 */
public class Problem0866PrimePalindrome {

    public int primePalindrome(int n) {
        int[] nums = parse(n);
        while (!isPrime(nums)) {
            nums = getNext(nums);
        }
        int ans = 0;
        for (int num : nums) {
            ans = ans * 10 + num;
        }
        return ans;
    }

    private int[] parse(int n) {
        if (n == 0) {
            return new int[1];
        }
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        int s = list.size();
        int[] ans = new int[s];
        for (int i = 0; i < s; i++) {
            ans[i] = list.get(s - i - 1);
        }
        int m = s - 1 >> 1;
        int i = m;
        while (i >= 0) {
            if (ans[i] > ans[s - i - 1]) {
                while (i >= 0) {
                    ans[s - i - 1] = ans[i];
                    i--;
                }
            } else if (ans[i] < ans[s - i - 1]) {
                int j = m;
                while (ans[j] == 9) {
                    j--;
                }
                ans[j]++;
                j++;
                while (j <= m) {
                    ans[j] = 0;
                    j++;
                }
                for (j = 0; j <= m; j++) {
                    ans[s - j - 1] = ans[j];
                }
                break;
            }
            i--;
        }
        return ans;
    }

    private int[] getNext(int[] nums) {
        int n = nums.length, m = n - 1 >> 1;
        int j = m;
        while (j >= 0 && nums[j] == 9) {
            j--;
        }
        if (j == -1) {
            int[] ans = new int[n + 1];
            ans[0] = 1;
            ans[n] = 1;
            return ans;
        }
        int num = nums[j] + 1;
        nums[j] = num;
        nums[n - j - 1] = num;
        j++;
        while (j <= m) {
            nums[j] = nums[n - j - 1] = 0;
            j++;
        }
        return nums;
    }

    private boolean isPrime(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans = ans * 10 + num;
        }
        if (ans == 1) {
            return false;
        }
        for (int i = 2; i * i <= ans; i++) {
            if (ans % i == 0) {
                return false;
            }
        }
        return true;
    }
}
