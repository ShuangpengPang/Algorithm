package com.shuangpeng.competition.第293场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: Problem2273FindResultantArrayAfterRemovingAnagrams（移除字母异位词后的结果数组）
 * @Date 2022/6/25 12:12 PM
 * @Version 1.0
 */
public class Problem2273FindResultantArrayAfterRemovingAnagrams {

    // 比赛时写法
    public List<String> removeAnagrams0(String[] words) {
        List<String> ans = new LinkedList<>();
        for (String w : words) {
            ans.add(w);
        }
        boolean find = true;
        while (ans.size() > 1 && find) {
            int n = ans.size();
            find = false;
            for (int i = 1; i < n; ++i) {
                char[] c1 = ans.get(i - 1).toCharArray();
                char[] c2 = ans.get(i).toCharArray();
                Arrays.sort(c1);
                Arrays.sort(c2);
                if (new String(c1).equals(new String(c2))) {
                    ans.remove(i);
                    find = true;
                    break;
                }
            }
        }
        return ans;
    }

    public List<String> removeAnagrams1(String[] words) {
        String s = "";
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            char[] chars = w.toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);
            if (!s.equals(str)) {
                s = str;
                ans.add(w);
            }
        }
        return ans;
    }

    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        int[] prev = countArray(words[0]);
        for (int i = 1; i < n; i++) {
            int[] curr = countArray(words[i]);
            boolean equal = true;
            for (int j = 0; j < 26; j++) {
                if (prev[j] != curr[j]) {
                    equal = false;
                    break;
                }
            }
            if (!equal) {
                ans.add(words[i]);
                prev = curr;
            }
        }
        return ans;
    }

    private int[] countArray(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        return cnt;
    }
}
