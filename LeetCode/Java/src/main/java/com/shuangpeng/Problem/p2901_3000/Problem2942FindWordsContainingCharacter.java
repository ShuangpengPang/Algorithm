package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2942FindWordsContainingCharacter（查找包含给定字符的单词）
 * @date 2024/4/13 10:00 PM
 */
public class Problem2942FindWordsContainingCharacter {

    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        for (int n = words.length, i = 0; i < n; i++) {
            if (words[i].contains("" + x)) {
                ans.add(i);
            }
        }
        return ans;
    }
}
