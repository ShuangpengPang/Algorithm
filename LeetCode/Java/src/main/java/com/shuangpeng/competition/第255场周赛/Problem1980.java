package com.shuangpeng.competition.第255场周赛;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem1980 {

    public String findDifferentBinaryString0(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>(n);
        for (String num : nums) {
            set.add(num);
        }
        char[] chars = new char[n];
        Arrays.fill(chars, '0');
        String string = null;
        for (int i = 0; i <= n; ++i) {
            string = new String(chars);
            if (!set.contains(string)) {
                break;
            }
            chars[i] = '1';
        }
        return string;
    }

    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] chars = new char[n];
        for (int i = 0; i < n; ++i) {
            if (nums[i].charAt(i) == '0') {
                chars[i] = '1';
            } else {
                chars[i] = '0';
            }
        }
        return new String(chars);
    }
}
