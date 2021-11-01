package com.shuangpeng.Problem.p0401_0500;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Problem0454FourSumII {

//    public static void main(String[] args) {
//        Problem0454FourSumII a = new Problem0454FourSumII();
//        int[] A = {0}, B = {0}, C = {0}, D = {0};
//        a.fourSumCount(A, B, C, D);
//    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Map<Integer, Integer> firstMap = getSumMap(A, B);
        Map<Integer, Integer> secondMap = getSumMap(C, D);
        int answer = 0;
        Set<Integer> keySet = firstMap.keySet();
        for (int key : keySet) {
            answer += firstMap.get(key) * secondMap.getOrDefault(-key, 0);
        }
        return answer;
    }

    private Map<Integer, Integer> getSumMap(int[] a, int[] b) {
        int length = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int sum = a[i] + b[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        return map;
    }
}
