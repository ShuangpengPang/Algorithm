package com.shuangpeng.Problem.p3001_3100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3006FindBeautifulIndicesInTheGivenArrayI（找出数组中的美丽下标I）
 * @date 2024/1/15 4:27 PM
 */
public class Problem3006FindBeautifulIndicesInTheGivenArrayI {

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        char[] cs = s.toCharArray(), csA = a.toCharArray(), csB = b.toCharArray();
        int[] nextA = getNext(csA), nextB = getNext(csB);
        List<Integer> indexA = getIndices(cs, csA, nextA), indexB = getIndices(cs, csB, nextB);
        List<Integer> ans = new ArrayList<>();
        int n1 = indexA.size(), n2 = indexB.size();
        for (int i = 0, j = 0; i < n1; i++) {
            int index = indexA.get(i);
            while (j < n2 && indexB.get(j) < index) {
                j++;
            }
            if (j > 0 && index - indexB.get(j - 1) <= k || j < n2 && indexB.get(j) - index <= k) {
                ans.add(index);
            }
        }
        return ans;
    }

    private int[] getNext(char[] cs) {
        int n = cs.length;
        int[] next = new int[n];
        for (int i = 1; i < n; i++) {
            int j = next[i - 1];
            while (j > 0 && cs[i] != cs[j]) {
                j = next[j - 1];
            }
            next[i] = cs[i] == cs[j] ? j + 1 : 0;
        }
        return next;
    }

    private List<Integer> getIndices(char[] cs, char[] t, int[] next) {
        int n1 = cs.length, n2 = t.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < n1; i++) {
            while (j > 0 && cs[i] != t[j]) {
                j = next[j - 1];
            }
            if (cs[i] == t[j]) {
                if (++j == n2) {
                    ans.add(i - n2 + 1);
                    j = next[j - 1];
                }
            }
        }
        return ans;
    }
}
