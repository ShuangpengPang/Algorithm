package com.shuangpeng.competition.双周赛.第068场双周赛;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem2116CheckIfAParenthesesStringCanBeValid {

    public boolean canBeValid0(String s, String locked) {
        int n = s.length();
        int low = 0, high = 0;
        for (int i = 0; i < n; ++i) {
            char c1 = s.charAt(i);
            char c2 = locked.charAt(i);
            if (c2 == '1') {
                if (c1 == '(') {
                    ++low;
                    ++high;
                } else {
                    --high;
                    low = low > 0 ? low - 1 : 0;
                }
            } else {
                ++high;
                low = low > 0 ? low - 1 : 0;
            }
            if (low > high) {
                return false;
            }
        }
        return low == 0 && (high & 1) == 0;
    }

//    public boolean canBeValid(String s, String locked) {
//        int n = s.length();
//        if (n % 2 == 1)
//            return false;
//
//        int lo = 0, hi = 0;
//
//        for (int i = 0; i < n; ++i) {
//            if (s[i] == '(') {
//                hi++;
//                if (locked[i] == '1')
//                    lo++;
//                else
//                    lo = lo == 0 ? 1 : lo - 1;
//            } else {
//                lo = lo == 0 ? 1 : lo - 1;
//                if (locked[i] == '1')
//                    hi--;
//                else
//                    hi++;
//                if (lo > hi)
//                    return false;
//            }
//        }
//
//        return lo == 0;
//    }

    public boolean canBeValid1(String s, String locked) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (locked.charAt(i) == '0') {
                stack2.offer(i);
                continue;
            }
            if (s.charAt(i) == '(') {
                stack1.offer(i);
            } else {
                if (!stack1.isEmpty()) {
                    stack1.pollLast();
                } else if (!stack2.isEmpty()) {
                    stack2.pollLast();
                } else {
                    return false;
                }
            }
        }
        while (!stack1.isEmpty()) {
            if (stack2.isEmpty()) {
                return false;
            }
            int i = stack1.pollLast();
            int j = stack2.pollLast();
            if (i > j) {
                return false;
            }
        }
        return (stack2.size() & 1) == 0;
    }

    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }
        int mn = 0, mx = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                ++mx;
                if (locked.charAt(i) == '1') {
                    ++mn;
                } else {
                    mn = mn == 0 ? 1 : mn - 1;
                }
            } else {
                mn = mn == 0 ? 1 : mn - 1;
                mx = locked.charAt(i) == '1' ? mx - 1 : mx + 1;
                if (mx < mn) {
                    return false;
                }
            }
        }
        return mn == 0 && (mx & 1) == 0;
    }
}
