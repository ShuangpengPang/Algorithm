package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1324PrintWordsVertically（竖直打印单词）
 * @date 2023/7/11 3:21 PM
 */
public class Problem1324PrintWordsVertically {

    public List<String> printVertically(String s) {
        String[] strs = s.split(" ");
        int maxLength = 0;
        for (String str : strs) {
            maxLength = Math.max(maxLength, str.length());
        }
        List<String> ans = new ArrayList<>(maxLength);
        for (int i = 0; i < maxLength; i++) {
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(i < str.length() ? str.charAt(i) : ' ');
            }
            int j = sb.length() - 1;
            while (j >= 0 && sb.charAt(j) == ' ') {
                sb.setLength(sb.length() - 1);
                j--;
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}
