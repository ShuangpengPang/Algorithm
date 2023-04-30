package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2423RemoveLetterToEqualizeFrequency（删除字符使频率相同）
 * @date 2023/4/30 2:35 PM
 */
public class Problem2423RemoveLetterToEqualizeFrequency {

    public boolean equalFrequency(String word) {
        int[] cnt = new int[26];
        int n = word.length();
        for (int i = 0; i < n; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        int count = 0, a = n, b = 0;
        for (int c : cnt) {
            if (c > 0) {
                count++;
                a = Math.min(a, c);
                b = Math.max(b, c);
            }
        }
        return b == 1 || b - a <= 1 && (b - 1) * count == n - 1 || a == 1 && b * (count - 1) == n - 1;
    }
}
