package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1234ReplaceTheSubstringForBalancedString（替换子串得到平衡字符串）
 * @date 2023/5/16 6:20 PM
 */
public class Problem1234ReplaceTheSubstringForBalancedString {

    public int balancedString(String s) {
        int[] map = new int['W' + 1];
        char[] arr = {'Q', 'W', 'E', 'R'};
        for (int i = 0; i < arr.length; i++) {
            map[arr[i]] = i;
        }
        char[] cs = s.toCharArray();
        int n = cs.length, avg = n >> 2;
        int[] cnt = new int[4];
        for (int i = 0; i < n; i++) {
            cnt[map[cs[i]]]++;
        }
        int ans = n;
        boolean valid = false;
        for (int l = -1, r = 0; r < n; r++) {
            cnt[map[cs[r]]]--;
            while (l < r && check(cnt, map[cs[l + 1]], avg)) {
                cnt[map[cs[++l]]]++;
                valid = true;
            }
            if (valid || check(cnt, -1, avg)) {
                ans = Math.min(ans, r - l);
            }
        }
        return ans;
    }

    private boolean check(int[] cnt, int c, int avg) {
        for (int i = 0; i < 4; i++) {
            int count = i == c ? cnt[i] + 1 : cnt[i];
            if (count > avg) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public int balancedString(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[idx(c)]++;
        }

        int partial = s.length() / 4;
        int res = s.length();

        if (check(cnt, partial)) {
            return 0;
        }
        for (int l = 0, r = 0; l < s.length(); l++) {
            while (r < s.length() && !check(cnt, partial)) {
                cnt[idx(s.charAt(r))]--;
                r++;
            }
            if (!check(cnt, partial)) {
                break;
            }
            res = Math.min(res, r - l);
            cnt[idx(s.charAt(l))]++;
        }
        return res;
    }

    public int idx(char c) {
        return c - 'A';
    }

    public boolean check(int[] cnt, int partial) {
        if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial || cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
            return false;
        }
        return true;
    }
}
