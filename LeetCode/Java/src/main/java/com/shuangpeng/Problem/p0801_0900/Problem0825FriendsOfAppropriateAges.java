package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0825FriendsOfAppropriateAges {

    public int numFriendRequests0(int[] ages) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int age : ages) {
            map.put(age, map.getOrDefault(age, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int size = list.size();
        int[] preSum = new int[size + 1];
        for (int i = 0; i < size; ++i) {
            preSum[i + 1] = preSum[i] + map.get(list.get(i));
        }
        int ans = 0;
        for (int l = 0, r = 0; r < size; ++r) {
            while (l <= r && (list.get(l) - 7) << 1 <= list.get(r)) {
                ++l;
            }
            if (l <= r) {
                ans += map.get(list.get(r)) * (preSum[r + 1] - preSum[l] - 1);
            }
        }
        return ans;
    }

    public int numFriendRequests1(int[] ages) {
        Arrays.sort(ages);
        int ans = 0;
        int left = 0, right = 0;
        int n = ages.length;
        for (int i = 0; i < n; ++i) {
            while (ages[left] <= ages[i] && (ages[left] - 7) << 1 <= ages[i]) {
                ++left;
            }
            if (ages[left] > ages[i]) {
                continue;
            }
            while (right + 1 < n && ages[right + 1] <= ages[i]) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }

    public int numFriendRequests(int[] ages) {
        int N = 121;
        int[] count = new int[N];
        for (int age : ages) {
            ++count[age];
        }
        for (int i = 1; i < N; ++i) {
            count[i] += count[i - 1];
        }
        int ans = 0;
        for (int i = 1; i < N; ++i) {
            int j = (int) (0.5 * i + 7);
            if (j < i) {
                ans += (count[i] - count[i - 1]) * (count[i] - count[j] - 1);
            }
        }
        return ans;
    }
}
