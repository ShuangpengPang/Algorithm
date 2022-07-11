package com.shuangpeng.Problem.p1101_1200;

/**
 * @Description: Problem1163LastSubstringInLexicographicalOrder（按字典序排在最后的子串）
 * @Date 2022/7/9 2:35 PM
 * @Version 1.0
 */
public class Problem1163LastSubstringInLexicographicalOrder {

    // TLE
    public String lastSubstring0(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int index = divide(chars, 0, n - 1);
        char[] dest = new char[n - index];
        System.arraycopy(chars, index, dest, 0, n - index);
        return new String(dest);
    }

    private int divide(char[] chars, int s, int e) {
        if (s == e) {
            return s;
        }
        int m = s + ((e - s) >> 1);
        int left = divide(chars, s, m);
        if (left < m) {
            int index = divide(chars, left + 1, e);
            left = getMax(chars, left, index, e);
            if (index > m) {
                return left;
            }
        }
        int right = divide(chars, m + 1, e);
        return getMax(chars, left, right, e);
    }

    private int getMax(char[] chars, int left, int right, int e) {
        int i = left, j = right;
        while (j <= e) {
            if (chars[i] > chars[j]) {
                return left;
            } else if (chars[i] < chars[j]) {
                return right;
            }
            i++;
            j++;
        }
        return left;
    }

    // TLE
    public String lastSubstring1(String s) {
        int n = s.length();
        int l = 0, r = 1, step = 0;
        while (r + step < n) {
            char c1 = s.charAt(l + step), c2 = s.charAt(r + step);
            if (c1 == c2) {
                step++;
            } else if (c1 < c2) {
                l = r++;
                step = 0;
            } else {
                r += step + 1;
                step = 0;
            }
        }
        return s.substring(l);
    }

    public String lastSubstring(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int start = 0, end = 1;
        while (end < n) {
            int step = 0;
            while (end + step < n) {
                char c1 = chars[start + step], c2 = chars[end + step];
                if (c2 > chars[start]) {
                    start = end + step;
                    end = start + 1;
                    break;
                } else if (c1 > c2) {
                    end += step + 1;
                    break;
                } else if (c1 < c2) {
                    start = end;
                    end++;
                    break;
                } else {
                    step++;
                    if (end + step == n) {
                        end += step;
                        break;
                    }
                }
            }
        }
        return s.substring(start);
    }
}
