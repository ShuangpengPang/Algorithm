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

    public int numberOfSubstrings0(String s) {
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

    public int numberOfSubstrings1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        List<Integer> zeros = new ArrayList<>();
        zeros.add(-1);
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') {
                zeros.add(i);
            }
        }
        zeros.add(n);
        int m = (int) Math.min(zeros.size() - 2, Math.sqrt(n));
        int ans = 0;
        for (int c = 0; c <= m; c++) {
            int count = 0, L = (c + 1) * c;
            for (int i = 0; i < n; i++) {
                if (cs[i] == '0') {
                    count++;
                }
                if (count >= c) {
                    ans += c == 0 ? i - zeros.get(count) : Math.max(0, Math.min(zeros.get(count - c + 1), i - L + 1) - zeros.get(count - c));
                }
            }
        }
        return ans;
    }

    public int numberOfSubstrings(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] zeros = new int[n + 1];
        zeros[0] = -1;
        int cnt0 = 0, cnt1 = 0, ans = 0;
        for (int i = 0; i <  n; i++) {
            if (cs[i] == '0') {
                zeros[++cnt0] = i;
            } else {
                ans += i - zeros[cnt0];
                cnt1++;
            }
            for (int j = cnt0, cnt = 1, square = 1; j > 0 && square <= cnt1; j--, cnt++, square = cnt * cnt) {
                ans += Math.max(0, zeros[j] - zeros[j - 1] - Math.max(0, square - (i - zeros[j] + 1 - cnt)));
            }
        }
        return ans;
    }
}
