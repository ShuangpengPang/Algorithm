package com.shuangpeng.Problem;

public class Problem0028ImplementStrStr {

//    "mississippi"
//            "issip"

    // a[k] -> a[i - 1]

//    public static void main(String[] args) {
//        String haystack = "mississippi";
//        String needle = "issip";
//        Problem0028ImplementStrStr a = new Problem0028ImplementStrStr();
//        a.strStr(haystack, needle);
//    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        int length = needle.length();
        int[] next = new int[length];
        next[0] = -1;
        int k = -1;
        int i = 0;
        while (i < length - 1) {
            if (k == -1 || needle.charAt(k) == needle.charAt(i)) {
                k++;
                i++;
                if (needle.charAt(i) == needle.charAt(k)) {
                    next[i] = next[k];
                } else {
                    next[i] = k;
                }
            } else {
                k = next[k];
            }
        }
        int n = haystack.length();
        i = 0;
        int j = 0;
        while (i < n) {
            if (j == length) {
                return i - length;
            }
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == length ? i - length : -1;
    }
}
