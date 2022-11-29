package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2193MinimumNumberOfMovesToMakePalindrome（得到回文串的最少操作次数）
 * @date 2022/11/29 11:40 AM
 */
public class Problem2193MinimumNumberOfMovesToMakePalindrome {

    public int minMovesToMakePalindrome(String s) {
        char[] cs = s.toCharArray();
        int ans = 0, n = cs.length, l = 0, r = n - 1;
        while (l < r) {
            while (l < n && cs[l] == '#') {
                l++;
            }
            while (r >= 0 && cs[r] == '#') {
                r--;
            }
            if (l >= r) {
                break;
            }
            char c1 = cs[l], c2 = cs[r];
            if (c1 == c2) {
                l++;
                r--;
            } else {
                int idx = find(cs, c1, r - 1, l + 1, r - 1, -1);
                if (idx != -1) {
                    ans += getCount(cs, idx, r);
                    cs[idx] = '#';
                    l++;
                } else {
                    idx = find(cs, c2, l + 1, l + 1, r - 1, 1);
                    ans += getCount(cs, l, idx);
                    cs[idx] = '#';
                    r--;
                }
            }
        }
        return ans;
    }

    private int find(char[] cs, char c, int idx, int start, int end, int d) {
        while (idx >= start && idx <= end) {
            if (cs[idx] == c) {
                return idx;
            }
            idx += d;
        }
        return -1;
    }

    private int getCount(char[] cs, int l, int r) {
        int ans = 0;
        while (l < r) {
            if (cs[l] != '#') {
                ans++;
            }
            l++;
        }
        return ans;
    }
}
