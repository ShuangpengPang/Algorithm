package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1455CheckIfAWordOccursAsAPrefixOfAnyWordInASentence（检查单词是否为句中其他单词的前缀）
 * @Date 2022/8/21 9:28 PM
 * @Version 1.0
 */
public class Problem1455CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] strs = sentence.split(" ");
        int n = strs.length, m = searchWord.length();
        for (int i = 0; i < n; i++) {
            String s = strs[i];
            if (s.length() >= m) {
                int j = 0;
                while (j < m) {
                    if (s.charAt(j) != searchWord.charAt(j)) {
                        break;
                    }
                    j++;
                }
                if (j == m) {
                    return i + 1;
                }
            }
        }
        return -1;
    }
}
