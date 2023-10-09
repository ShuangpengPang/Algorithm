package com.shuangpeng.Problem.p1801_1900;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1850MinimumAdjacentSwapsToReachTheKthSmallestNumber（邻位交换的最小次数）
 * @date 2023/10/9 4:31 PM
 */
public class Problem1850MinimumAdjacentSwapsToReachTheKthSmallestNumber {

    public int getMinSwaps(String num, int k) {
        char[] cs = num.toCharArray();
        char[] arr = getKthNumber(cs.clone(), k);
        Map<Character, List<Integer>> map = new HashMap<>();
        int n = cs.length;
        for (int i = n - 1; i >= 0; i--) {
            map.computeIfAbsent(arr[i], key -> new ArrayList<>()).add(i + 1);
        }
        int[] bit = new int[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            List<Integer> list = map.get(cs[i - 1]);
            int j = list.remove(list.size() - 1);
            int x = j - 1, cnt = 0;
            while (x != 0) {
                cnt += bit[x];
                x ^= x & -x;
            }
            ans += Math.max(0, i - 1 - cnt);
            x = j;
            while (x <= n) {
                bit[x]++;
                x += x & -x;
            }
        }
        return ans;
    }

    private char[] getKthNumber(char[] cs, int k) {
        int n = cs.length;
        for (int i = 0; i < k; i++) {
            int j = n - 1;
            while (cs[j - 1] >= cs[j]) {
                j--;
            }
            int s = j;
            while (s < n && cs[s] > cs[j - 1]) {
                s++;
            }
            char c = cs[j - 1];
            cs[j - 1] = cs[s - 1];
            cs[s - 1] = c;
            reverse(cs, j, n - 1);
        }
        return cs;
    }

    private void reverse(char[] cs, int i, int j) {
        while (i < j) {
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
            i++;
            j--;
        }
    }
}
