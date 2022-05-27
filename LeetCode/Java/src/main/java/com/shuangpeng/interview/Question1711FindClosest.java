package com.shuangpeng.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Question1711FindClosest（单词距离）
 * @Date 2022/5/27 10:30 AM
 * @Version 1.0
 */
public class Question1711FindClosest {

    public int findClosest0(String[] words, String word1, String word2) {
        int n = words.length;
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (words[i].equals(word1)) {
                list1.add(i);
            } else if (words[i].equals(word2)) {
                list2.add(i);
            }
        }
        if (list1.size() > list2.size()) {
            List<Integer> temp = list1;
            list1 = list2;
            list2 = temp;
        }
        int ans = Integer.MAX_VALUE;
        for (int i : list1) {
            int left = 0, right = list2.size() - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int j = list2.get(mid);
                if (i > j) {
                    left = mid + 1;
                } else if (i < j) {
                    right = mid - 1;
                }
            }
            if (left == 0) {
                ans = Math.min(ans, list2.get(left) - i);
            } else if (left == list2.size()) {
                ans = Math.min(ans, i - list2.get(left - 1));
            } else {
                ans = Math.min(ans, Math.min(i - list2.get(left - 1), list2.get(left) - i));
            }
        }
        return ans;
    }

    public int findClosest1(String[] words, String word1, String word2) {
        int n = words.length;
        int idx1 = -n, idx2 = -n;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (words[i].equals(word1)) {
                ans = Math.min(ans, i - idx2);
                idx1 = i;
            } else if (words[i].equals(word2)) {
                ans = Math.min(ans, i - idx1);
                idx2 = i;
            }
            if (ans == 1) {
                break;
            }
        }
        return ans;
    }

    public int findClosest(String[] words, String word1, String word2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            String word = words[i];
            if (word.equals(word1)) {
                list1.add(i);
            } else if (word.equals(word2)) {
                list2.add(i);
            }
        }
        int n1 = list1.size(), n2 = list2.size();
        int i = 0, j = 0;
        int ans = Integer.MAX_VALUE;
        while (i < n1 && j < n2 && ans != 1) {
            int idx1 = list1.get(i), idx2 = list2.get(j);
            if (idx1 < idx2) {
                ans = Math.min(ans, idx2 - idx1);
                ++i;
            } else {
                ans = Math.min(ans, idx1 - idx2);
                ++j;
            }
        }
        return ans;
    }
}
