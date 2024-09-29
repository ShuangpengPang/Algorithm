package com.shuangpeng.Problem.p3201_3300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3234CountTheNumberOfSubstringsWithDominantOnes（统计1显著的字符串的数量）
 * @date 2024/9/26 6:23 PM
 */
public class Problem3234CountTheNumberOfSubstringsWithDominantOnes {

    public int numberOfSubstrings(String s) {
        char[] cs = s.toCharArray();
        List<Integer> index = new ArrayList<>();
        index.add(-1);
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') {
                index.add(i);
            }
        }
        index.add(n);
        int size = index.size(), count = (int) Math.min(size - 2, Math.sqrt(n));
        long ans = 0;
        for (int i = 1; i < size; i++) {
            int c = index.get( i) - index.get(i - 1);
            ans += (long) c * (c - 1) / 2;
        }
        for (int i = 1; i <= count; i++) {
            for (int j = i + 1; j < size; j++) {
                int m = (i + 1) * i;
                int prev = index.get(j - i - 1), next = index.get(j);
                int first = index.get(j - i), last = index.get(j - 1);
                int idx = Math.min(last - m + 1, first);
                int t = next - Math.max(prev + m, Math.max(idx + m, last + 1));
                int c = Math.min(Math.min(first - prev, first - idx), t);
                ans += (long) Math.max(0, (idx - prev)) * (next - last);
                ans += (long) (2 * t - c + 1) * Math.max(0, c) / 2;
            }
        }
        return (int) ans;
    }

//    public static void main(String[] args) {
//        Problem3234CountTheNumberOfSubstringsWithDominantOnes a = new Problem3234CountTheNumberOfSubstringsWithDominantOnes();
//        a.numberOfSubstrings("00011");
//    }
}
