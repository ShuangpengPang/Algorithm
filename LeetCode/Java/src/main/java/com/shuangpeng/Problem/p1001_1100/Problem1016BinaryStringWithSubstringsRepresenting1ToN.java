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

    int n, m;
    boolean[] visited;

    public boolean queryString(String s, int n) {
        this.n = n;
        m = s.length();
        if (n == 1) {
            return s.contains("1");
        }
        int k = getBitCount(n);
        if (m < Math.min(k + n - (1 << (k - 1)), k + (1 << k - 2) - 2)) {
            return false;
        }
        visited = new boolean[1 << k];
        char[] cs = s.toCharArray();
        return check(cs, k, n) && check(cs, k - 1, (1 << (k - 1)) - 1);
    }

    private boolean check(char[] cs, int k, int max) {
        int num = 0, N = 1 << (k - 1);
        for (int i = 0; i < k; i++) {
            num = (num << 1) + cs[i] - '0';
        }
        int count = 0;
        if (num >= N && num <= max && !visited[num]) {
            visited[num] = true;
            count++;
        }
        int mask = (1 << (k - 1)) - 1;
        for (int i = k; i < m; i++) {
            num = ((num & mask) << 1) + (cs[i] - '0');
            if (num >= N && num <= max && !visited[num]) {
                visited[num] = true;
                count++;
            }
        }
        return count == max - N + 1;
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

class Problem1016BinaryStringWithSubstringsRepresenting1ToN1 {

    public boolean queryString(String s, int n) {
        if (n == 1) {
            return s.contains("1");
        }
        int b = getBit(n), m = s.length();
        if (m < n - (1 << b) + b + 1 || m < (1 << b) - (1 << (b - 1)) + b - 1) {
            return false;
        }
        boolean[] visited = new boolean[n + 1];
        return check(s, b + 1, 1 << b, n, visited) && check(s, b, 1 << (b - 1), (1 << b) - 1, visited);
    }

    private boolean check(String s, int m, int min, int max, boolean[] visited) {
        int n = s.length();
        int num = 0;
        for (int i = 0; i < m; i++) {
            num = (num << 1) | (s.charAt(i) - '0');
        }
        int count = 0;
        if (num >= min && num <= max) {
            visited[num] = true;
            count++;
        }
        int mask = (1 << m) - 1;
        for (int i = m; i < n; i++) {
            num = ((num << 1) | (s.charAt(i) - '0')) & mask;
            if (num >= min && num <= max && !visited[num]) {
                visited[num] = true;
                count++;
            }
        }
        return count == max - min + 1;
    }

    private int getBit(int n) {
        int b = 0;
        while (n >= (1 << b)) {
            b++;
        }
        return b - 1;
    }
}
