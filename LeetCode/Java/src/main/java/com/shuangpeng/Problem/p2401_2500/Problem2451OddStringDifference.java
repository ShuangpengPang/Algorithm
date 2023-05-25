package com.shuangpeng.Problem.p2401_2500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2451OddStringDifference（差值数组不同的字符串）
 * @date 2023/5/25 10:21 AM
 */
public class Problem2451OddStringDifference {

    public String oddString0(String[] words) {
        int m = words.length, n = words[0].length();
        Map<String, List<Integer>> map = new HashMap<>(2);
        for (int i = 0; i < m; i++) {
            String w = words[i];
            char[] cs = new char[n - 1];
            for (int j = 0; j < n - 1; j++) {
                cs[j] = (char) (w.charAt(j + 1) - w.charAt(j));
            }
            String s = new String(cs);
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            if (list.size() == 1) {
                return words[list.get(0)];
            }
        }
        return null;
    }

    public String oddString(String[] words) {
        int[] diff0 = get(words[0]), diff1 = get(words[1]);
        if (Arrays.equals(diff0, diff1)) {
            for (int i = 2; i < words.length; i++) {
                if (!Arrays.equals(diff0, get(words[i]))) {
                    return words[i];
                }
            }
        }
        return Arrays.equals(diff0, get(words[2])) ? words[1] : words[0];
    }

    private int[] get(String w) {
        int n = w.length();
        int[] ans = new int[n];
        for (int i = 0; i + 1 < n; i++) {
            ans[i] = w.charAt(i + 1) - w.charAt(i);
        }
        return ans;
    }
}
