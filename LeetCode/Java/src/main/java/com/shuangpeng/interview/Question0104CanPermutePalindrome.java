package com.shuangpeng.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0104CanPermutePalindrome（面试题 01.04 回文排列）
 * @date 2024/5/24 4:34 PM
 */
public class Question0104CanPermutePalindrome {

    // 错误做法，并非都是小写字母
//    public boolean canPermutePalindrome0(String s) {
//        int mask = 0;
//        for (char c : s.toCharArray()) {
//            mask ^= 1 << c - 'a';
//        }
//        return (mask & (mask - 1)) == 0;
//    }

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int cnt = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) ^ 1);
            cnt += (map.get(c) << 1) - 1;
        }
        return cnt < 2;
    }
}
