package com.shuangpeng.competition.第271到280场周赛.第272场周赛;

import java.util.ArrayList;
import java.util.List;

public class Problem2111MinimumOperationsToMakeTheArrayKIncreasing {

    // 比赛时写法
    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            List<Integer> list = new ArrayList<>();
            int count = 0;
            for (int j = i; j < n; j += k) {
                ++count;
                int idx = binarySearch(list, arr[j]);
                if (idx >= list.size()) {
                    list.add(arr[j]);
                } else {
                    list.set(idx, arr[j]);
                }
            }
            ans += count - list.size();
        }
        return ans;
    }

    private int binarySearch(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int data = list.get(mid);
            if (data <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
