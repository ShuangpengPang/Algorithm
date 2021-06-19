package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1239MaximumLengthOfConcatenatedStringWithUniqueCharacters {

    public int maxLength0(List<String> arr) {
        int size = arr.size();
        int n = 26;
        List<boolean[]> list = new ArrayList<>();
        int maxLength = 0;
        for (int i = 0; i < size; i++) {
            String string = arr.get(i);
            int length = string.length();
            if (length > n) {
                continue;
            }
            boolean[] map = new boolean[n];
            boolean isValid = true;
            int max = 0;
            for (int j = 0; j < length; j++) {
                int k = string.charAt(j) - 'a';
                if (map[k]) {
                    isValid = false;
                    break;
                } else {
                    map[k] = true;
                    max++;
                }
            }
            if (!isValid) {
                continue;
            }
            maxLength = Math.max(maxLength, check(list, map, max));
        }
        return maxLength;
    }

    private int check(List<boolean[]> list, boolean[] map, int max) {
        int n = 26;
        int size = list.size();
        int maxLength = max;
        for (int i = 0; i < size; i++) {
            int length = max;
            boolean[] copy = Arrays.copyOf(map, n);
            boolean[] array = list.get(i);
            boolean isValid = true;
            for (int j = 0; j < n; j++) {
                if (copy[j] && array[j]) {
                    isValid = false;
                    break;
                } else if (array[j]) {
                    copy[j] = true;
                    length++;
                }
            }
            if (!isValid) {
                continue;
            }
            maxLength = Math.max(maxLength, length);
            list.add(copy);
        }
        list.add(map);
        return maxLength;
    }

    public int maxLength1(List<String> arr) {
        int ans = 0;
        List<Integer> masks = new ArrayList<Integer>();
        masks.add(0);
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) { // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                    mask = 0;
                    break;
                }
                mask |= 1 << ch; // 将 ch 加入 mask 中
            }
            if (mask == 0) {
                continue;
            }
            int n = masks.size();
            for (int i = 0; i < n; ++i) {
                int m = masks.get(i);
                if ((m & mask) == 0) { // m 和 mask 无公共元素
                    masks.add(m | mask);
                    ans = Math.max(ans, Integer.bitCount(m | mask));
                }
            }
        }
        return ans;
    }

    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        int answer = 0;
        for (String string : arr) {
            int mask = 0;
            int size = string.length();
            for (int i = 0; i < size; i++) {
                int j = string.charAt(i) - 'a';
                if (((mask >> j) & 1) != 0) {
                    mask = 0;
                    break;
                }
                mask |= (1 << j);
            }
            if (mask == 0) {
                continue;
            }
            int length = masks.size();
            for (int i = 0; i < length; i++) {
                int m = masks.get(i);
                if ((mask & m) == 0) {
                    masks.add(mask | m);
                    answer = Math.max(answer, Integer.bitCount(mask | m));
                }
            }
        }
        return answer;
    }
}
