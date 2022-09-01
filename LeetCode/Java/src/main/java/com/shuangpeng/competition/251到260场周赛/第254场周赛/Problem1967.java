package com.shuangpeng.competition.第254场周赛;

import java.util.ArrayList;
import java.util.List;

public class Problem1967 {

    public int numOfStrings0(String[] patterns, String word) {
        int count = 0;
        for (String p : patterns) {
            int i = 0;
            int start = 0;
            int j = start;
            while (j < word.length()) {
                if (p.charAt(i) == word.charAt(j)) {
                    i++;
                    j++;
                } else {
                    i = 0;
                    start++;
                    j = start;
                }
                if (i == p.length()) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public int numOfStrings1(String[] patterns, String word) {
        int count = 0;
        for (String p : patterns) {
            if (word.indexOf(p) != -1) {
                count++;
            }
        }
        return count;
    }

    public int numOfStrings(String[] patterns, String word) {
        int n = patterns.length;
        List<Integer>[] nexts = new List[n];
        for (int i = 0; i < n; ++i) {
            String pattern = patterns[i];
            int length = pattern.length();
            nexts[i] = new ArrayList<>(length);
            nexts[i].add(-1);
            for (int j = 1; j < length; ++j) {
                int k = j - 1;
                char c = pattern.charAt(j - 1);
                while (k > 0 && pattern.charAt(nexts[i].get(k)) != c) {
                    k = nexts[i].get(k);
                }
                nexts[i].add(nexts[i].get(k) + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < n; ++i) {
            String pattern = patterns[i];
            List<Integer> next = nexts[i];
            int j = 0;
            for (int k = 0; k < word.length(); ++k) {
                char c = word.charAt(k);
                if (pattern.charAt(j) == c) {
                    j++;
                } else {
                    while (j > 0 && pattern.charAt(next.get(j)) != c) {
                        j = next.get(j);
                    }
                    j = next.get(j) + 1;
                }
                if (j == pattern.length()) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

//    public static void main(String[] args) {
//        Problem1967 a = new Problem1967();
//        String[] patterns = {"glgpqusg","qsyrgdelg","akcsg","e","fnmi","gzqsyrgdelg","lapwypxrmaydjr","s","iwig"};
//        String word = "numiwigzqsyrgdelgxs";
//        a.numOfStrings(patterns, word);
//    }
}
