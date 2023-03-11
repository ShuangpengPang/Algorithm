package com.shuangpeng.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question1705FindLongestSubarray（面试题17.05 字母与数字）
 * @date 2023/3/11 10:05 AM
 */
public class Question1705FindLongestSubarray {

    public String[] findLongestSubarray0(String[] array) {
        int n = array.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0, index = 0;
        for (int i = 0, cnt = 0; i < n; i++) {
            map.putIfAbsent(i - (cnt << 1), i);
            if (Character.isDigit(array[i].charAt(0))) {
                cnt++;
            }
            int j = i + 1;
            int length = j - map.getOrDefault(j - (cnt << 1), j);
            if (length > maxLength) {
                maxLength = length;
                index = j;
            }
        }
        String[] ans = new String[maxLength];
        System.arraycopy(array, index - maxLength, ans, 0, maxLength);
        return ans;
    }

    public String[] findLongestSubarray1(String[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = array.length, maxLength = 0, index = 0;
        for (int i = 0, s = 0; i < n; i++) {
            map.putIfAbsent(s, i);
            s += Character.isDigit(array[i].charAt(0)) ? 1 : -1;
            int j = i + 1, length = j - map.getOrDefault(s, j);
            if (length > maxLength) {
                maxLength = length;
                index = j;
            }
        }
        String[] ans = new String[maxLength];
        System.arraycopy(array, index - maxLength, ans, 0, maxLength);
        return ans;
    }

    public String[] findLongestSubarray2(String[] array) {
        int n = array.length;
        int[] first = new int[(n << 1) + 1];
        Arrays.fill(first, -1);
        int maxLength = 0, index = 0;
        for (int i = 0, s = n; i < n; i++) {
            if (first[s] == -1) {
                first[s] = i;
            }
            s += ((array[i].charAt(0) >> 6 & 1) << 1) - 1;
            int j = i + 1, length = j - (first[s] == -1 ? j : first[s]);
            if (length > maxLength) {
                maxLength = length;
                index = j;
            }
        }
        String[] ans = new String[maxLength];
        System.arraycopy(array, index - maxLength, ans, 0, maxLength);
        return ans;
    }

    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] first = new int[(n << 1) + 1];
        int maxLength = 0, index = 0;
        for (int i = 0, s = n; i < n; i++) {
            if (first[s] == 0) {
                first[s] = i + 1;
            }
            s += ((array[i].charAt(0) >> 6 & 1) << 1) - 1;
            int j = i + 2, length = j - (first[s] == 0 ? j : first[s]);
            if (length > maxLength) {
                maxLength = length;
                index = j - 1;
            }
        }
        String[] ans = new String[maxLength];
        System.arraycopy(array, index - maxLength, ans, 0, maxLength);
        return ans;
    }
}
