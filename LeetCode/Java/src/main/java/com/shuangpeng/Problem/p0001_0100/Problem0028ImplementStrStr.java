package com.shuangpeng.Problem.p0001_0100;

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

    public int strStr0(String haystack, String needle) {
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

    public int strStr1(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        int n = haystack.length();
        int length = needle.length();
        if (n < length) {
            return -1;
        }
        int target = 0;
        int base = 26;
        for (int i = 0; i < length; i++) {
            target = target * base + needle.charAt(i) - 'a';
        }
        int current = 0;
        for (int i = 0; i < length; i++) {
            current = current * base + haystack.charAt(i) - 'a';
        }
        long data = (long) Math.pow(26, length - 1);
        for (int i = 0; i < n - length + 1; i++) {
            if (current == target) {
                int j = i;
                while (j < i + length) {
                    if (haystack.charAt(j) != needle.charAt(j - i)) {
                        break;
                    }
                    j++;
                }
                if (j == i + length) {
                    return i;
                }
            } else {
                if (i + length < n) {
                    current = (int) ((current - (haystack.charAt(i) - 'a') * data) * base + haystack.charAt(i + length) - 'a');
                }
            }
        }
        return -1;
    }

    // function to convert character to integer
    public int charToInt(int idx, String s) {
        return (int)s.charAt(idx) - (int)'a';
    }

    public int strStr2(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L > n) return -1;

        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) return 0;

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) return start;
        }
        return -1;
    }

    public int strStr3(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int n = needle.length();
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 1; i < n; ++i) {
            char c = needle.charAt(i - 1);
            int j = i - 1;
            while (j > 0 && needle.charAt(next[j]) != c) {
                j = next[j];
            }
            next[i] = next[j] + 1;
        }
        int j = 0;
        for (int i = 0; i < haystack.length(); ++i) {
            char c = haystack.charAt(i);
            if (c == needle.charAt(j)) {
                j++;
            } else {
                while (j > 0 && needle.charAt(next[j]) != c) {
                    j = next[j];
                }
                j = next[j] + 1;
            }
            if (j == n) {
                return i - n + 1;
            }
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int n = needle.length();
        int[] p = new int[n];
        for (int i = 1, j = 0; i < n; ++i) {
            char c = needle.charAt(i);
            while (j > 0 && needle.charAt(j) != c) {
                j = p[j - 1];
            }
            if (needle.charAt(j) == c) {
                j++;
            }
            p[i] = j;
        }
        int length = haystack.length();
        for (int i = 0, j = 0; i < length; ++i) {
            char c = haystack.charAt(i);
            while (j > 0 && needle.charAt(j) != c) {
                j = p[j - 1];
            }
            if (needle.charAt(j) == c) {
                j++;
                if (j == n) {
                    return i - n + 1;
                }
            }
        }
        return -1;
    }
}
