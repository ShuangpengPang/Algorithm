package com.shuangpeng.Problem.p0101_0200;

public class Problem0165CompareVersionNumbers {

    public int compareVersion0(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        int length1 = array1.length;
        int length2 = array2.length;
        int i = 0, j = 0;
        while (i < length1 || j < length2) {
            int num1 = 0, num2 = 0;
            if (i < length1) {
                num1 = Integer.valueOf(array1[i]);
                i++;
            }
            if (j < length2) {
                num2 = Integer.valueOf(array2[j]);
                j++;
            }
            if (num1 != num2) {
                return num1 - num2 > 0 ? 1 : -1;
            }
        }
        return 0;
    }

    public int compareVersion1(String version1, String version2) {
        String[] v1 = version1.split("\\."), v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; ++i) {
            int n1 = 0, n2 = 0;
            if (i < v1.length) {
                n1 = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                n2 = Integer.parseInt(v2[i]);
            }
            if (n1 != n2) {
                return n1 < n2 ? -1 : 1;
            }
        }
        return 0;
    }

    public int compareVersion(String version1, String version2) {
        int n1 = version1.length(), n2 = version2.length();
        int i = 0, j = 0;
        while (i < n1 || j < n2) {
            int x = 0, y = 0;
            while (i < n1 && version1.charAt(i) != '.') {
                x = x * 10 + version1.charAt(i) - '0';
                i++;
            }
            i++;
            while (j < n2 && version2.charAt(j) != '.') {
                y = y * 10 + version2.charAt(j) - '0';
                j++;
            }
            j++;
            if (x != y) {
                return x < y ? -1 : 1;
            }
        }
        return 0;
    }
}
