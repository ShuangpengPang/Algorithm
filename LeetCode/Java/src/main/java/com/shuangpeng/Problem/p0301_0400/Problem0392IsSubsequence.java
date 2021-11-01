package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0392IsSubsequence {

    public boolean isSubsequence0(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        int n1 = s.length(), n2 = t.length();
        if (n1 > n2) {
            return false;
        }
        int index = 0;
        for (int i = 0; i < n1; i++) {
            while (index < n2 && s.charAt(i) != t.charAt(index)) {
                index++;
            }
            if (index >= n2) {
                return false;
            }
            index++;
        }
        return true;
    }

    public boolean isSubsequence1(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        int n1 = s.length(), n2 = t.length();
        if (n1 > n2) {
            return false;
        }
        int count = 26;
        List<Integer>[] lists = new ArrayList[count];
        for (int i = 0; i < count; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < n2; i++) {
            lists[t.charAt(i) - 'a'].add(i);
        }
        int index = -1;
        for (int i = 0; i < n1; i++) {
            List<Integer> list = lists[s.charAt(i) - 'a'];
            int next = getNextIndex(list, index);
            if (next == -1) {
                return false;
            }
            index = next;
        }
        return true;
    }

    public int getNextIndex(List<Integer> list, int index) {
        int size = list.size();
        int left = 0, right = size;
        while (left < right) {
            int mid = (left + right) >> 1;
            int data = list.get(mid);
            if (data > index) {
                right = mid;
            } else if (data < index) {
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        return left < size ? list.get(left) : -1;
    }

    public boolean isSubsequence2(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        if (n1 == 0) {
            return true;
        }
        if (n1 > n2) {
            return false;
        }
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n1;
    }

    public boolean isSubsequence3(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        if (n1 == 0) {
            return true;
        }
        if (n1 > n2) {
            return false;
        }
        int count = 26;
        int[][] dp = new int[count][n2 + 1];
        for (int i = 0; i < count; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n2; i++) {
            int c = t.charAt(i) - 'a';
            for (int j = i; j >= 0; j--) {
                if (dp[c][j] >= 0) {
                    break;
                }
                dp[c][j] = i;
            }
        }
        int j = 0;
        for (int i = 0; i < n1; i++) {
            j = dp[s.charAt(i) - 'a'][j] + 1;
            if (j <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }
}
