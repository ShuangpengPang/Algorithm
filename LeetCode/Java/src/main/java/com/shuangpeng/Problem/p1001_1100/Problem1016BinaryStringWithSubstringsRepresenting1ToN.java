package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1016BinaryStringWithSubstringsRepresenting1ToN（子串能表示从1到N数字的二进制串）
 * @date 2023/4/7 3:09 PM
 */
public class Problem1016BinaryStringWithSubstringsRepresenting1ToN {

    public boolean queryString0(String s, int n) {
        int m = s.length(), count = 0;
        if (n >= 2048 || n > m * m) {
            return false;
        }
        boolean[] has = new boolean[n + 1];
        for (int i = m - 1; i >= 0 && count < n; i--) {
            if (s.charAt(i) == '1') {
                int num = 0;
                for (int j = i; j < m; j++) {
                    num = (num << 1) + s.charAt(j) - '0';
                    if (num > n) {
                        break;
                    }
                    if (!has[num]) {
                        has[num] = true;
                        count++;
                    }
                }
            }
        }
        return count == n;
    }

    public boolean queryString1(String s, int n) {
        int m = s.length(), count = 0;
        if (n > (m + 1) * m >> 1) {
            return false;
        }
        boolean[] has = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = i, num = 0; j < m && num <= n; j++) {
                num = (num << 1) + (s.charAt(j) - '0');
                if (num > 0 && num <= n) {
                    if (!has[num]) {
                        count++;
                        has[num] = true;
                    }
                }
            }
        }
        return count == n;
    }
}

class Problem1016BinaryStringWithSubstringsRepresenting1ToN0 {

    int count, n, m;
    boolean[] visited;

    public boolean queryString(String s, int n) {
        this.n = n;
        m = s.length();
        if (n > (m + 1) * m >> 1) {
            return false;
        }
        if (n == 1) {
            return s.contains("1");
        }
        int k = getBitCount(n);
        count = 0;
        visited = new boolean[1 << k];
        char[] cs = s.toCharArray();
        check(cs, k);
        check(cs, k - 1);
        return count == n - (1 << (k - 2)) + 1;
    }

    private void check(char[] cs, int k) {
        int num = 0, N = 1 << (k - 1), M = 1 << k;
        for (int i = 0; i < k; i++) {
            num = (num << 1) + cs[i] - '0';
        }
        if (num >= N && num <= n && !visited[num]) {
            visited[num] = true;
            count++;
        }
        for (int i = k; i < m; i++) {
            num = (num << 1) + (cs[i] - '0') - (cs[i - k] - '0') * M;
            if (num >= N && num <= n && !visited[num]) {
                visited[num] = true;
                count++;
            }
        }
    }

    private int getBitCount(int n) {
        int v = 1, bit = 0;
        while (v <= n) {
            v <<= 1;
            bit++;
        }
        return bit;
    }
}
