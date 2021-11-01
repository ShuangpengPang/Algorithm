package com.shuangpeng.Problem.p0401_0500;

public class Problem0474OnesAndZeroes {

//    public static void main(String[] args) {
//        Problem0474OnesAndZeroes a = new Problem0474OnesAndZeroes();
//        String[] strs = {"0","00","1"};
//        a.findMaxForm(strs, 1, 0);
//    }

    class StringInfo {
        int m = 0;
        int n = 0;

        StringInfo(int[] a) {
            m = a[0];
            n = a[1];
        }
    }

    public int findMaxForm0(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int length = strs.length;
        StringInfo[] infos = new StringInfo[length];
        for (int i = 0; i < length; i++) {
            infos[i] = new StringInfo(getCount(strs[i]));
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < length; i++) {
            int mm = infos[i].m;
            int nn = infos[i].n;
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (mm <= j && nn <= k) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - mm][k - nn] + 1);
                    } else {
                        dp[j][k] = dp[j][k];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private int[] getCount(String string) {
        int m = 0, n = 0;
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (string.charAt(i) == '0') {
                m++;
            } else {
                n++;
            }
        }
        return new int[]{m, n};
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][] array = new int[length][2];
        for (int i = 0; i < length; i++) {
            String str = strs[i];
            int len = str.length();
            for (int j = 0; j < len; j++) {
                if (str.charAt(j) == '0') {
                    array[i][0]++;
                } else {
                    array[i][1]++;
                }
            }
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < array.length; i++) {
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= array[i][0] && k >= array[i][1]) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - array[i][0]][k - array[i][1]] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }

//    public static void main(String[] args) {
//        Problem0474OnesAndZeroes a = new Problem0474OnesAndZeroes();
//        String[] strs = {"11111","100","1101","1101","11000"};
//        a.findMaxForm(strs, 5, 7);
//    }
}
