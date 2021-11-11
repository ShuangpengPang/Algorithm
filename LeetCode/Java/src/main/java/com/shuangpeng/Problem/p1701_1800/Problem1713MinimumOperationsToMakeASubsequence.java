package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem1713MinimumOperationsToMakeASubsequence {

    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        Map<Integer, Integer> indexMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            indexMap.put(target[i], i);
        }
        int[] lengths = new int[n + 1];
        Arrays.fill(lengths, Integer.MAX_VALUE);
        lengths[0] = -1;
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            if (indexMap.containsKey(a)) {
                int index = indexMap.get(a);
                int left = 1, right = index + 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (lengths[mid] < index) {
                        left = mid + 1;
                    } else if (lengths[mid] >= index) {
                        right = mid - 1;
                    }
                }
                lengths[left] = index;
                maxLength = Math.max(maxLength, left);
            }
        }
        return n - maxLength;
    }

//    public static void main(String[] args) {
//        int[] target = new int[]{147006006, 414087855, 781906580, 213872647, 341866400, 674590438, 530308968, 178008557, 87329397, 886710682};
//        int[] arr = new int[]{530308968, 147006006, 672718815, 341866400, 886710682, 341866400, 530308968, 178008557, 6513508, 6513508};
//        Problem1713MinimumOperationsToMakeASubsequence a = new Problem1713MinimumOperationsToMakeASubsequence();
//        a.minOperations(target, arr);
//    }
}
