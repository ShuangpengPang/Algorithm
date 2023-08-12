package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1234ReplaceTheSubstringForBalancedString（替换子串得到平衡字符串）
 * @date 2023/5/16 6:20 PM
 */
public class Problem1234ReplaceTheSubstringForBalancedString {

    public int balancedString0(String s) {
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
        for (int l = 0, r = 0; r < n; r++) {
            cnt[map[cs[r]]]--;
            while (l <= r && check(cnt, map[cs[l]], avg)) {
                cnt[map[cs[l++]]]++;
                valid = true;
            }
            if (valid || check(cnt, -1, avg)) {
                ans = Math.min(ans, r - l + 1);
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

    public int balancedString1(String s) {
        char[] arr = {'Q', 'W', 'E', 'R'};
        int[] ids = new int[26];
        for (int i = 0; i < 4; i++) {
            ids[arr[i] - 'A'] = i;
        }
        int[] cnt = new int[4];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            cnt[ids[c - 'A']]++;
        }
        int n = cs.length, m = n >> 2;
        if (check(cnt, m)) {
            return 0;
        }
        int ans = n;
        for (int l = 0, r = 0; r < n; r++) {
            cnt[ids[cs[r] - 'A']]--;
            while (l <= r + 1 && check(cnt, m)) {
                ans = Math.min(ans, r - l + 1);
                cnt[ids[cs[l++] - 'A']]++;
            }
        }
        return ans;
    }

    private boolean check(int[] cnt, int m) {
        int n = cnt.length;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > m) {
                return false;
            }
        }
        return true;
    }

    public int balancedString(String s) {
        int n = s.length(), q = n >> 2;
        int[] cnt = new int[4];
        String str = "QWER";
        for (int i = 0; i < n; i++) {
            cnt[str.indexOf(s.charAt(i))]++;
        }
        for (int i = 0; i < 4; i++) {
            cnt[i] -= q;
        }
        int ans = Integer.MAX_VALUE;
        int[] tmp = new int[4];
        for (int i = 0, j = 0; i < n; i++) {
            tmp[str.indexOf(s.charAt(i))]++;
            while (j <= i && check(cnt, tmp)) {
                ans = Math.min(ans, i - j + 1);
                tmp[str.indexOf(s.charAt(j++))]--;
            }
            if (j > i && check(cnt, tmp)) {
                return 0;
            }
        }
        return ans;
    }

    private boolean check(int[] cnt, int[] tmp) {
        for (int i = 0; i < 4; i++) {
            if (tmp[i] < cnt[i]) {
                return false;
            }
        }
        return true;
    }
}
