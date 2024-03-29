package com.shuangpeng.competition.双周赛.第081场双周赛;

/**
 * @Description: Problem2315CountAsterisks（统计星号）
 * @Date 2022/7/4 1:55 PM
 * @Version 1.0
 */
public class Problem2315CountAsterisks {

    // 比赛时写法
    public int countAsterisks0(String s) {
        int n = s.length();
        int ans = 0;
        int prev = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                if (prev == -1) {
                    ans++;
                } else {
                    count++;
                }
            } else if (c == '|') {
                if (prev == -1) {
                    prev = i;
                } else {
                    prev = -1;
                    count = 0;
                }
            }
        }
        ans += count;
        return ans;
    }

    public int countAsterisks1(String s) {
        int n = s.length();
        boolean outer = true;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*' && outer) {
                ans++;
            } else if (c == '|') {
                outer = !outer;
            }
        }
        return ans;
    }

    public int countAsterisks2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') {
                i++;
                while (s.charAt(i) != '|') {
                    i++;
                }
            } else if (c == '*') {
                ans++;
            }
        }
        return ans;
    }

    public int countAsterisks3(String s) {
        int n = s.length(), ans = 0;
        boolean between = false;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') {
                between = !between;
            } else if (c == '*' && !between) {
                ans++;
            }
        }
        return ans;
    }

    public int countAsterisks4(String s) {
        int n = s.length(), ans = 0, cnt1 = 0;
        boolean outer = true;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') {
                outer = !outer;
                if (outer) {
                    cnt1 = 0;
                }
            } else if (c == '*') {
                if (outer) {
                    ans++;
                } else {
                    cnt1++;
                }
            }
        }
        return ans + cnt1;
    }

    public int countAsterisks(String s) {
        int n = s.length(), ans = 0, ok = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') {
                ok ^= 1;
            } else if (c == '*') {
                ans += ok;
            }
        }
        return ans;
    }
}
