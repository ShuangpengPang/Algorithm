package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0838PushDominoes {

    public String pushDominoes0(String dominoes) {
        int n = dominoes.length();
        char[] chars = dominoes.toCharArray();
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            char c = chars[i];
            if (c == 'R') {
                if (!stack.isEmpty()) {
                    int j = stack.get(stack.size() - 1);
                    if (dominoes.charAt(j) == 'R') {
                        for (int k = j + 1; k < i; ++k) {
                            chars[k] = 'R';
                        }
                    }
                }
                stack.add(i);
            } else if (c == 'L') {
                if (stack.isEmpty() || dominoes.charAt(stack.get(stack.size() - 1)) == 'L') {
                    int s = stack.isEmpty() ? 0 : stack.get(stack.size() - 1) + 1;
                    for (int k = s; k < i; ++k) {
                        chars[k] = 'L';
                    }
                } else {
                    int s = stack.get(stack.size() - 1) + 1;
                    int e = i - 1;
                    while (s < e) {
                        chars[s] = 'R';
                        chars[e] = 'L';
                        s++;
                        e--;
                    }
                }
                stack.add(i);
            }
        }
        if (!stack.isEmpty() && dominoes.charAt(stack.get(stack.size() - 1)) == 'R') {
            for (int j = stack.get(stack.size() - 1) + 1; j < n; ++j) {
                chars[j] = 'R';
            }
        }
        return new String(chars);
    }

    public String pushDominoes1(String dominoes) {
        int n = dominoes.length();
        int[] index = new int[n + 2];
        char[] symbol = new char[n + 2];
        index[0] = -1;
        symbol[0] = 'L';
        int len = 1;
        char[] chars = dominoes.toCharArray();
        for (int i = 0; i < n; ++i) {
            if (chars[i] != '.') {
                index[len] = i;
                symbol[len++] = chars[i];
            }
        }
        index[len] = n;
        symbol[len++] = 'R';
        for (int i = 1; i < len; ++i) {
            char c1 = symbol[i - 1], c2 = symbol[i];
            if (c1 == c2) {
                for (int j = index[i - 1] + 1; j < index[i]; ++j) {
                    chars[j] = c1;
                }
            } else if (c1 == 'R' && c2 == 'L') {
                for (int j = index[i - 1] + 1, k = index[i] - 1; j < k; j++, k--) {
                    chars[j] = 'R';
                    chars[k] = 'L';
                }
            }
        }
        return new String(chars);
    }

    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int n = chars.length;
        int[] force = new int[n];
        int previous = 0;
        for (int i = 0; i < n; ++i) {
            char c = chars[i];
            if (c == 'R') {
                force[i] = n;
            } else if (c == 'L') {
                force[i] = 0;
            } else {
                force[i] = Math.max(0, previous - 1);
            }
            previous = force[i];
        }
        previous = 0;
        for (int i = n - 1; i >= 0; --i) {
            char c = chars[i];
            if (c == 'L') {
                previous = n;
            } else if (c == 'R') {
                previous = 0;
            } else {
                previous = Math.max(0, previous - 1);
            }
            force[i] -= previous;
        }
        for (int i = 0; i < n; ++i) {
            if (force[i] > 0) {
                chars[i] = 'R';
            } else if (force[i] < 0) {
                chars[i] = 'L';
            }
        }
        return new String(chars);
    }
}
