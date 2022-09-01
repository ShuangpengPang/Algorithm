package com.shuangpeng.competition.第261到270场周赛.第261场周赛;

public class Problem2030 {

    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == letter) {
                ++count;
            }
        }
        StringBuilder sb = new StringBuilder();
        int a = n;
        int containCount = 0;
        int leftCount = count;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && a > k && containCount + leftCount >= repetition) {
                if (sb.charAt(sb.length() - 1) == letter) {
                    if (containCount + leftCount == repetition) {
                        break;
                    }
                    --containCount;
                }
                sb.deleteCharAt(sb.length() - 1);
                --a;
            }
            if (sb.length() < k && (sb.length() + repetition - containCount - (c == letter ? 1 : 0) < k)) {
                sb.append(c);
                if (c == letter) {
                    ++containCount;
                    --leftCount;
                }
            } else {
                if (c == letter) {
                    --leftCount;
                }
                --a;
            }
        }
        return sb.toString();
    }
}
