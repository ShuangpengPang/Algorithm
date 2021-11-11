package com.shuangpeng.Problem.p1801_1900;

public class Problem1835FindXORSumOfAllPairsAND {

    public int getXORSum0(int[] arr1, int[] arr2) {
        int answer = 0;
        for (int i = 0; i <= 30; i++) {
            int cn1 = 0, cn2 = 0;
            for (int num : arr1) {
                if ((num & (1 << i)) != 0) {
                    cn1++;
                }
            }
            for (int num : arr2) {
                if ((num & (1 << i)) != 0) {
                    cn2++;
                }
            }
            if ((cn1 & 1) == 1 && (cn2 & 1) == 1) {
                answer |= (1 << i);
            }
        }
        return answer;
    }

    public int getXORSum(int[] arr1, int[] arr2) {
        int x = 0, y = 0;
        for (int num : arr1) {
            x ^= num;
        }
        for (int num : arr2) {
            y ^= num;
        }
        return x & y;
    }
}
