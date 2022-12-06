package com.shuangpeng.Problem.p1801_1900;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1805NumberOfDifferenceIntegersInAString（字符串中不同整数的数目）
 * @date 2022/12/6 10:08 AM
 */
public class Problem1805NumberOfDifferenceIntegersInAString {

    public int numDifferentIntegers(String word) {
        int n = word.length();
        Set<String> set = new HashSet<>();
        for (int i = 0, j = 0; i <= n; i++) {
            if (i == n || !Character.isDigit(word.charAt(i))) {
                if (i > j) {
                    while (j < i - 1 && word.charAt(j) == '0') {
                        j++;
                    }
                    set.add(word.substring(j, i));
                }
                j = i + 1;
            }
        }
        return set.size();
    }
}
