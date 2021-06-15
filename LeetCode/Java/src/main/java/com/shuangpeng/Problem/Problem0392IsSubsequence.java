package com.shuangpeng.Problem;

import java.util.ArrayList;
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

    public boolean isSubsequence(String s, String t) {
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
}
