package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1592RearrangeSpacesBetweenWords（重新排列单词间的空格）
 * @Date 2022/9/7 10:19 AM
 * @Version 1.0
 */
public class Problem1592RearrangeSpacesBetweenWords {

    public String reorderSpaces(String text) {
        List<String> list = new ArrayList<>();
        int n = text.length(), i = 0, spaces = 0;
        while (i < n) {
            if (text.charAt(i) == ' ') {
                spaces++;
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (i < n && text.charAt(i) != ' ') {
                    sb.append(text.charAt(i));
                    i++;
                }
                list.add(sb.toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        if (size == 1) {
            sb.append(list.get(0));
            appendSpace(sb, spaces);
            return sb.toString();
        }
        for (int j = 0; j < size; j++) {
            sb.append(list.get(j));
            int cnt = j < size - 1 ? spaces / (size - 1) : spaces % (size - 1);
            appendSpace(sb, cnt);
        }
        return sb.toString();
    }

    private void appendSpace(StringBuilder sb, int spaces) {
        for (int i = 0; i < spaces; i++) {
            sb.append(' ');
        }
    }
}
