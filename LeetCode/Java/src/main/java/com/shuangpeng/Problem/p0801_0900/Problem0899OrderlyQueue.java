package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 有序队列（899）
 * @Date 2022/8/3 7:31 PM
 **/
public class Problem0899OrderlyQueue {

    public String orderlyQueue0(String s, int k) {
        char[] chars = s.toCharArray();
        if (k == 1) {
            List<Character> list = new LinkedList<>();
            for (char c : chars) {
                list.add(c);
            }
            int n = s.length();
            for (int i = 0; i < n - 1; ++i) {
                char c = list.remove(0);
                list.add(c);
                if (compare(list, chars)) {
                    for (int j = 0; j < n; ++j) {
                        chars[j] = list.get(j);
                    }
                }
            }
        } else {
            Arrays.sort(chars);
        }
        return new String(chars);
    }

    private boolean compare(List<Character> list, char[] chars) {
        int i = 0;
        for (char c : list) {
            if (c < chars[i]) {
                return true;
            } else if (c > chars[i]) {
                return false;
            }
            ++i;
        }
        return false;
    }

    public String orderlyQueue1(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (k == 1) {
            char[] ans = chars.clone();
            for (int i = 1; i < n; i++) {
                compare(chars, i, ans);
            }
            return new String(ans);
        }
        Arrays.sort(chars);
        return new String(chars);
    }

    private void compare(char[] arr1, int idx, char[] arr2) {
        int n = arr2.length;
        boolean ans = false;
        for (int i = 0, j = idx; i < n; i++) {
            if (arr1[j] < arr2[i]) {
                ans = true;
                break;
            } else if (arr1[j] > arr2[i]) {
                break;
            }
            j++;
            if (j == n) {
                j = 0;
            }
        }
        if (ans) {
            for (int i = 0, j = idx; i < n; i++) {
                arr2[i] = arr1[j];
                j++;
                if (j == n) {
                    j = 0;
                }
            }
        }
    }

    public String orderlyQueue2(String s, int k) {
        int n = s.length();
        if (k == 1) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 1; i < n; i++) {
                sb.append(sb.charAt(0)).deleteCharAt(0);
                String str = sb.toString();
                if (str.compareTo(s) < 0) {
                    s = str;
                }
            }
            return s;
        }
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            while (cnt[i]-- > 0) {
                chars[idx++] = (char) ('a' + i);
            }
        }
        return new String(chars);
    }

    public String orderlyQueue(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        if (k == 1) {
            int i = 0, j = 1, a = 0;
            while (i < n && j < n && a < n) {
                char c1 = cs[(i + a) % n], c2 = cs[(j + a) % n];
                if (c1 != c2) {
                    if (c1 > c2) {
                        i = i + a + 1;
                    } else {
                        j = j + a + 1;
                    }
                    if (i == j) {
                        j++;
                    }
                    a = 0;
                } else {
                    a++;
                }
            }
            int idx = Math.min(i, j);
            return s.substring(idx) + s.substring(0, idx);
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[cs[i] - 'a']++;
        }
        char[] ans = new char[n];
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            while (cnt[i]-- > 0) {
                ans[idx++] = (char) (i + 'a');
            }
        }
        return new String(ans);
    }
}

class Solution {
    public String orderlyQueue(String s, int _k) {
        char[] cs = s.toCharArray();
        if (_k == 1) {
            int i = 0, j = 1, k = 0, n = cs.length;
            while (i < n && j < n && k < n) {
                char a = cs[(i + k) % n], b = cs[(j + k) % n];
                if (a == b) k++;
                else {
                    if (a > b) i += k + 1;
                    else j += k + 1;
                    if (i == j) i++;
                    k = 0;
                }
            }
            i = Math.min(i, j);
            return s.substring(i) + s.substring(0, i);
        } else {
            Arrays.sort(cs);
            return String.valueOf(cs);
        }
    }
}

