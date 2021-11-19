package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0869ReorderedPowerOf2 {

    static Set<String> set = getSet();
    public static Set<String> getSet() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 31; ++i) {
            set.add(toString(1 << i));
        }
        return set;
    }

    private static String toString(int n) {
        List<Character> list = new ArrayList<>();
        while (n > 0) {
            list.add((char) ((n % 10) + '0'));
            n /= 10;
        }
        list.sort(Comparator.comparingInt(a -> a));
        int size = list.size();
        char[] chars = new char[size];
        for (int i = 0; i < size; ++i) {
            chars[i] = list.get(i);
        }
        return new String(chars);
    }

    public boolean reorderedPowerOf2_0(int n) {
        return set.contains(toString(n));
    }

    public boolean reorderedPowerOf2(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        Arrays.sort(chars);
        return backtrack(chars, 0, 0, new boolean[chars.length]);
    }

    private boolean backtrack(char[] chars, int index, int num, boolean[] visited) {
        int n = chars.length;
        if (index == n) {
            return (num & (num - 1)) == 0;
        }
        for (int i = 0; i < n; ++i) {
            char c = chars[i];
            if ((num == 0 && c == '0') || visited[i] || (i > 0 && !visited[i - 1] && chars[i] == chars[i - 1])) {
                continue;
            }
            visited[i] = true;
            if (backtrack(chars, index + 1, num * 10 + (c - '0'), visited)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
}
