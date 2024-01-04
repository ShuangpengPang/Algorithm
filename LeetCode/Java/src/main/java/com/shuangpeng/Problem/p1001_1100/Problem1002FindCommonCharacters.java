package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1002FindCommonCharacters（查找共用字符）
 * @date 2024/1/4 10:49 AM
 */
public class Problem1002FindCommonCharacters {

    public List<String> commonChars(String[] words) {
        int[] cnt = new int[26], tmp = new int[26];
        Arrays.fill(cnt, Integer.MAX_VALUE);
        for (String w : words) {
            Arrays.fill(tmp, 0);
            for (int i = 0; i < w.length(); i++) {
                tmp[w.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                cnt[i] = Math.min(cnt[i], tmp[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                ans.add("" + (char) (i + 'a'));
            }
        }
        return ans;
    }
}
