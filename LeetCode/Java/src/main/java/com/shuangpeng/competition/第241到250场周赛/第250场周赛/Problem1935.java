package com.shuangpeng.competition.第241到250场周赛.第250场周赛;

import java.util.HashSet;
import java.util.Set;

public class Problem1935 {

    public int canBeTypedWords0(String text, String brokenLetters) {
        String[] words = text.split(" ");
        Set<Character> set = new HashSet<>();
        for (char b : brokenLetters.toCharArray()) {
            set.add(b);
        }
        int answer = 0;
        for (String word : words) {
            int n = word.length();
            int i = 0;
            while (i < n) {
                if (set.contains(word.charAt(i))) {
                    break;
                }
                i++;
            }
            if (i == n) {
                answer++;
            }
        }
        return answer;
    }

    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> set = new HashSet<>();
        for (char b : brokenLetters.toCharArray()) {
            set.add(b);
        }
        int n = text.length();
        boolean valid = true;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                if (valid) {
                    count++;
                }
                valid = true;
            } else if (set.contains(c)) {
                valid = false;
            }
        }
        return valid ? count + 1 : count;
    }
}
