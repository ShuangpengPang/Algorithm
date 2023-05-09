package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2437NumberOfValidClockTimes（有效时间的数目）
 * @date 2023/5/9 10:38 AM
 */
public class Problem2437NumberOfValidClockTimes {

    public int countTime0(String time) {
        int ans = 0;
        char c1 = time.charAt(0), c2 = time.charAt(1);
        char c3 = time.charAt(3), c4 = time.charAt(4);
        for (char h1 = '0'; h1 <= '2'; h1++) {
            if (c1 != '?' && c1 != h1) {
                continue;
            }
            for (char h2 = '0'; h2 <= '9'; h2++) {
                if (h1 == '2' && h2 > '3') {
                    break;
                }
                if (c2 != '?' && c2 != h2) {
                    continue;
                }
                for (char m1 = '0'; m1 <= '5'; m1++) {
                    if (c3 != '?' && c3 != m1) {
                        continue;
                    }
                    for (char m2 = '0'; m2 <= '9'; m2++) {
                        if (c4 != '?' && c4 != m2) {
                            continue;
                        }
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public int countTime(String time) {
        int[] r1 = getRange(time.charAt(0)), r2 = getRange(time.charAt(1));
        int[] r3 = getRange(time.charAt(3)), r4 = getRange(time.charAt(4));
        int ans = 0;
        for (int h1 = r1[0]; h1 <= r1[1]; h1++) {
            if (h1 > 2) {
                break;
            }
            for (int h2 = r2[0]; h2 <= r2[1]; h2++) {
                if (h1 == 2 && h2 > 3) {
                    break;
                }
                for (int m1 = r3[0]; m1 <= r3[1]; m1++) {
                    if (m1 > 5) {
                        break;
                    }
                    for (int m2 = r4[0]; m2 <= r4[1]; m2++) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private int[] getRange(char c) {
        if (c == '?') {
            return new int[]{0, 9};
        }
        return new int[]{c - '0', c - '0'};
    }
}

class Problem2437NumberOfValidClockTimes0 {

    int ans;

    public int countTime(String time) {
        ans = 0;
        dfs(time.toCharArray(), 0);
        return ans;
    }

    private void dfs(char[] cs, int pos) {
        if (pos == cs.length) {
            if (check(cs)) {
                ans++;
            }
            return;
        }
        if (cs[pos] == '?') {
            for (char c = '0'; c <= '9'; c++) {
                cs[pos] = c;
                dfs(cs, pos + 1);
                cs[pos] = '?';
            }
        } else {
            dfs(cs, pos + 1);
        }
    }

    private boolean check(char[] cs) {
        if ((cs[0] - '0') * 10 + cs[1] - '0' > 23) {
            return false;
        }
        if ((cs[3] - '0') * 10 + cs[4] - '0' > 59) {
            return false;
        }
        return true;
    }
}

class Problem2437NumberOfValidClockTimes1 {

    public int countTime(String time) {
        char h1 = time.charAt(0), h2 = time.charAt(1);
        char m1 = time.charAt(3), m2 = time.charAt(4);
        int c1 = 0, c2 = 0;
        for (int i = 0; i < 24; i++) {
            if (check(h1, h2, i)) {
                c1++;
            }
        }
        for (int i = 0; i < 60; i++) {
            if (check(m1, m2, i)) {
                c2++;
            }
        }
        return c1 * c2;
    }

    private boolean check(char c1, char c2, int v) {
        if (c1 != '?' && c1 - '0' != v / 10) {
            return false;
        }
        if (c2 != '?' && c2 - '0' != v % 10) {
            return false;
        }
        return true;
    }
}
