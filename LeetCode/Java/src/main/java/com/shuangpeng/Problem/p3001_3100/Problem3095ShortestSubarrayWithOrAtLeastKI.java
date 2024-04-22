package com.shuangpeng.Problem.p3001_3100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3095ShortestSubarrayWithOrAtLeastKI（或值至少K的最短子数组I）
 * @date 2024/4/22 4:01 PM
 */
public class Problem3095ShortestSubarrayWithOrAtLeastKI {

    public int minimumSubarrayLength0(int[] nums, int k) {
        int n = nums.length, N = Integer.MAX_VALUE, ans = N;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{i, nums[i]});
            int j = 0;
            for (int size = list.size(), p = 0; p < size; p++) {
                int[] arr = list.get(p);
                arr[1] |= nums[i];
                if (arr[1] >= k) {
                    ans = Math.min(ans, i - arr[0] + 1);
                }
                if (list.get(j)[1] != arr[1]) {
                    j++;
                }
                list.get(j)[0] = arr[0];
                list.get(j)[1] = arr[1];
            }
            list.subList(j + 1, list.size()).clear();
        }
        return ans == N ? -1 : ans;
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, N = Integer.MAX_VALUE, ans = N;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= k) {
                return 1;
            }
            for (int j = i - 1; j >= 0 && (nums[j] | nums[i]) != nums[j]; j--) {
                nums[j] |= nums[i];
                if (nums[j] >= k) {
                    ans = Math.min(ans, i - j + 1);
                }
            }
        }
        return ans == N ? -1 : ans;
    }
}
