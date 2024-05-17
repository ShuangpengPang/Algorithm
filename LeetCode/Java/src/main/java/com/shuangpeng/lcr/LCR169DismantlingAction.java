package com.shuangpeng.lcr;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR169DismantlingAction（招式拆解II）
 * @date 2024/5/17 11:54 AM
 */
public class LCR169DismantlingAction {

    public char dismantlingAction0(String arr) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        for (char c : arr.toCharArray()) {
            map.put(c, !map.containsKey(c));
        }
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (entry.getValue() == true) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public char dismantlingAction(String arr) {
        int[] first = new int[26];
        int n = arr.length(), N = n + 1;
        char[] cs = arr.toCharArray();
        for (int i = 0; i < n; i++) {
            int c = cs[i] - 'a';
            first[c] = first[c] == 0 ? i + 1 : N;
        }
        int min = N;
        char ans = ' ';
        for (int i = 0; i < 26; i++) {
            if (first[i] != 0 && first[i] < min) {
                min = first[i];
                ans = (char) (i + 'a');
            }
        }
        return ans;
    }
}
