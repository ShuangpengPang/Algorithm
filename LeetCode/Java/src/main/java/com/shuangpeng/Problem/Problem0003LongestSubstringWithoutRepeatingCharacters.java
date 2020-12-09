package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.Set;

public class Problem0003LongestSubstringWithoutRepeatingCharacters {

//    pwwkew

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int max = 1;
        int left = 0;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int right = 1; right < length; right++) {
            char ch = s.charAt(right);
            if (!set.contains(ch)) {
                set.add(ch);
                max = Math.max(max, right - left + 1);
            } else {
                while (s.charAt(left) != ch) {
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;
            }
        }
        return max;
    }
}
