package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.Set;

public class Problem0898BitwiseORsOfSubarrays {

    public int subarrayBitwiseORs0(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int n = arr.length;
        set.add(arr[0]);
        for (int i = 1; i < n; ++i) {
            int k = arr[i];
            for (int j = i; j >= 0; --j) {
                if (j != i) {
                    k |= arr[j];
                    if (k == arr[j]) {
                        break;
                    }
                }
                if (!set.contains(k)) {
                    set.add(k);
                }
            }
        }
        return set.size();
    }

    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> ans = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            Set<Integer> temp = new HashSet<>();
            temp.add(arr[i]);
            for (int num : set) {
                temp.add(num | arr[i]);
            }
            set = temp;
            ans.addAll(set);
        }
        return ans.size();
    }
}
