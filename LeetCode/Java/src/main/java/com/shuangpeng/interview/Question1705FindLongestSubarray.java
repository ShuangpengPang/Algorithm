package com.shuangpeng.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question1705FindLongestSubarray（面试题17.05 字母与数字）
 * @date 2023/3/11 10:05 AM
 */
public class Question1705FindLongestSubarray {

    public String[] findLongestSubarray(String[] array) {
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
}
