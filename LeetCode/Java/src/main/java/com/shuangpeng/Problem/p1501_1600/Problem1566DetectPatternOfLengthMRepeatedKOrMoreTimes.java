package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1566DetectPatternOfLengthMRepeatedKOrMoreTimes（重复至少K次且长度为M的模式）
 * @date 2024/3/16 2:42 PM
 */
public class Problem1566DetectPatternOfLengthMRepeatedKOrMoreTimes {

    public boolean containsPattern0(int[] arr, int m, int k) {
        int n = arr.length;
        for (int i = 0; i + m * k <= n; i++) {
            boolean valid = true;
            for (int c = 1, j = i + m; c < k && valid; c++) {
                for (int x = 0; x < m && valid; x++, j++) {
                    if (arr[i + x] != arr[j]) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        for (int i = 0, e = i + m * k; e <= n; i++, e++) {
            boolean valid = true;
            for (int j = i + m; j < e; j++) {
                if (arr[j] != arr[j - m]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }

//    public boolean containsPattern(int[] arr, int m, int k) {
//        int n = arr.length;
//        for (int i = 0; i <= n - m * k; i++) {
//            int[] a = new int[m];
//            for (int j = 0; j < m; j++) {
//                a[j] = arr[i + j];
//            }
//            if (check(arr, a, i + m, k - 1)) {
//                return true;
//            }
//        }
//        return false;
////        [1,2,3,1,2]
//    }
//
//    private boolean check(int[] arr, int[] a, int start, int cnt) {
//        int n = arr.length, m = a.length;
//        int[] next = new int[m];
//        for (int i = 1, j = 0; i < m; i++) {
//            while (j > 0 && a[i] != a[j]) {
//                j = next[j - 1];
//            }
//            next[i] = j = a[i] == a[j] ? j + 1 : 0;
//        }
//        for (int i = start, j = 0; cnt > 0 && i - j + cnt * m <= n; i++) {
//            while (j > 0 && arr[i] != a[j]) {
//                j = next[j - 1];
//            }
//            if (arr[i] == a[j] && ++j == m) {
//                j = 0;
//                cnt--;
//            }
//        }
//        return cnt == 0;
//    }
}
