package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2914MinimumNumberOfChangesToMakeBinaryStringBeautiful（使二进制字符串变美丽的最少修改次数）
 * @date 2024/1/6 6:47 PM
 */
public class Problem2914MinimumNumberOfChangesToMakeBinaryStringBeautiful {

    public int minChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i += 2) {
            ans += s.charAt(i) - '0' ^ s.charAt(i - 1) - '0';
        }
        return ans;
    }
}
