package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.List;

public class Problem0859BuddyStrings {

    public boolean buddyStrings0(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int n = s.length();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != goal.charAt(i)) {
                list.add(i);
                if (list.size() > 2) {
                    return false;
                }
            }
        }
        if (list.isEmpty()) {
            if (n > 26) {
                return true;
            }
            return check(s);
        }
        if (list.size() == 1) {
            return false;
        }
        int i = list.get(0), j = list.get(1);
        return s.charAt(i) == goal.charAt(j) && s.charAt(j) == goal.charAt(i);
    }

    private boolean check(String s) {
        int[] counts = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int j = s.charAt(i) - 'a';
            if (counts[j] > 0) {
                return true;
            }
            counts[j] = 1;
        }
        return false;
    }

    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int n = s.length();
        int a = -1, b = -1;
        boolean duplicate = false;
        int[] counts = new int[26];
        for (int i = 0; i < n; ++i) {
            if (++counts[s.charAt(i) - 'a'] == 2) {
                duplicate = true;
            }
            int c1 = s.charAt(i), c2 = goal.charAt(i);
            if (c1 != c2) {
                if (b == -1) {
                    a = c1;
                    b = c2;
                } else if (a != c2 || b != c1) {
                    return false;
                } else {
                    a = -1;
                }
            }
        }
        return a == -1 && (b != -1 || duplicate);
    }
}
