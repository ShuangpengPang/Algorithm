package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem0899OrderlyQueue {

    public String orderlyQueue(String s, int k) {
        char[] chars = s.toCharArray();
        if (k == 1) {
            List<Character> list = new LinkedList<>();
            for (char c : chars) {
                list.add(c);
            }
            int n = s.length();
            for (int i = 0; i < n - 1; ++i) {
                char c = list.remove(0);
                list.add(c);
                if (compare(list, chars)) {
                    for (int j = 0; j < n; ++j) {
                        chars[j] = list.get(j);
                    }
                }
            }
        } else {
            Arrays.sort(chars);
        }
        Arrays.equals()
        return new String(chars);
    }

    private boolean compare(List<Character> list, char[] chars) {
        int i = 0;
        for (char c : list) {
            if (c < chars[i]) {
                return true;
            } else if (c > chars[i]) {
                return false;
            }
            ++i;
        }
        return false;
    }
}
