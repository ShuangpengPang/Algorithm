package com.shuangpeng.Problem.p2701_2800;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2800ShortestStringThatContainsThreeStrings（包含三个字符串的最短字符串）
 * @date 2023/8/3 3:15 PM
 */
public class Problem2800ShortestStringThatContainsThreeStrings {

    public String minimumString(String a, String b, String c) {
        Set<String> set = new HashSet<>();
        for (String s : getSet(a, b)) {
            set.addAll(getSet(s, c));
        }
        for (String s : getSet(a, c)) {
            set.addAll(getSet(s, b));
        }
        for (String s : getSet(b, c)) {
            set.addAll(getSet(s, a));
        }
        List<String> list = new ArrayList<>(set);
        list.sort((x, y) -> x.length() != y.length() ? x.length() - y.length() : x.compareTo(y));
        return list.get(0);
    }

    private Set<String> getSet(String a, String b) {
        Set<String> ans = new HashSet<>();
        if (a.contains(b)) {
            ans.add(a);
            return ans;
        }
        if (b.contains(a)) {
            ans.add(b);
            return ans;
        }
        ans.add(concatString(a, b));
        ans.add(concatString(b, a));
        return ans;
    }

    private String concatString(String a, String b) {
        int n1 = a.length(), n2 = b.length(), n = Math.min(n1, n2);
        while (n > 0) {
            boolean match = true;
            for (int i = n1 - n, j = 0; j < n; i++, j++) {
                if (a.charAt(i) != b.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                break;
            }
            n--;
        }
        return a + b.substring(n, n2);
    }
}
