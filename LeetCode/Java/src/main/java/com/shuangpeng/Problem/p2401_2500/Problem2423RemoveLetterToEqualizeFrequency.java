package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2423RemoveLetterToEqualizeFrequency（删除字符使频率相同）
 * @date 2023/4/30 2:35 PM
 */
public class Problem2423RemoveLetterToEqualizeFrequency {

    public boolean equalFrequency(String word) {
        int min = Integer.MAX_VALUE, max = 0;
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            cnt[c - 'a']++;
        }
        int n = word.length(), count = 0;
        for (int c : cnt) {
            if (c > 0) {
                count++;
                min = Math.min(min, c);
                max = Math.max(max, c);
            }
        }
        return min == 1 && n - 1 == (count - 1) * max || max - min <= 1 && n - 1 == count * (max - 1);
    }
}
