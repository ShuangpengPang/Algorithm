package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem0969PancakeSorting
 * @Date 2022/2/19 10:17 AM
 * @Version 1.0
 */
public class Problem0969PancakeSorting {

    public List<Integer> pancakeSort0(int[] arr) {
        int[] target = arr.clone();
        Arrays.sort(target);
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; --i) {
            int num = target[i];
            if (num != arr[i]) {
                int j = i - 1;
                while (arr[j] != num) {
                    --j;
                }
                if (j != 0) {
                    reverse(arr, j);
                    ans.add(j + 1);
                }
                reverse(arr, i);
                ans.add(i + 1);
            }
        }
        return ans;
    }

    private void reverse(int[] arr, int idx) {
        for (int i = 0, j = idx; i < j; ++i, --j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int n = arr.length; n > 1; n--) {
            int index = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }
            if (index == n - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, n - 1);
            ret.add(index + 1);
            ret.add(n);
        }
        return ret;
    }
}
