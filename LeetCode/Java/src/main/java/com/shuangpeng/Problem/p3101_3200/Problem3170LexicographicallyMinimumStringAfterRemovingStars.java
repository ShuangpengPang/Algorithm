package com.shuangpeng.Problem.p3101_3200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3170LexicographicallyMinimumStringAfterRemovingStars（删除星号以后字典序最小的字符串）
 * @date 2024/6/13 12:47 AM
 */
public class Problem3170LexicographicallyMinimumStringAfterRemovingStars {

    public String clearStars0(String s) {
        char[] cs = s.toCharArray();
        List<Integer>[] indices = new List[26];
        Arrays.setAll(indices, i -> new ArrayList<>());
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '*') {
                for (List<Integer> list : indices) {
                    if (!list.isEmpty()) {
                        cs[list.get(list.size() - 1)] = '#';
                        list.remove(list.size() - 1);
                        break;
                    }
                }
                cs[i] = '#';
            } else {
                indices[cs[i] - 'a'].add(i);
            }
        }
        int last = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] != '#') {
                cs[last++] = cs[i];
            }
        }
        return new String(cs, 0, last);
    }

    public String clearStars1(String s) {
        char[] cs = s.toCharArray();
        List<Integer>[] indices = new List[26];
        Arrays.setAll(indices, i -> new ArrayList<>());
        int n = cs.length, mask = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '*')  {
                if (mask != 0) {
                    int idx = Integer.numberOfTrailingZeros(mask);
                    cs[indices[idx].remove(indices[idx].size() - 1)] = '*';
                    if (indices[idx].isEmpty()) {
                        mask ^= 1 << idx;
                    }
                }
            } else {
                indices[cs[i] - 'a'].add(i);
                mask |= 1 << cs[i] - 'a';
            }
        }
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] != '*') {
                cs[p++] = cs[i];
            }
        }
        return new String(cs, 0, p);
    }

    public String clearStars(String s) {
        int len = s.length();
        boolean[] isCleared = new boolean[len];
        int clearCount = 0;
        int[] prevIndices = new int[len];
        int[] lastIndices = new int[26];
        Arrays.fill(lastIndices, -1);
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c == '*') {
                int j = 0;
                for(; lastIndices[j] < 0; j++);
                isCleared[i] = true;
                isCleared[lastIndices[j]] = true;
                clearCount += 2;
                lastIndices[j] = prevIndices[lastIndices[j]];
            } else {
                int j = c - 'a';
                prevIndices[i] = lastIndices[j];
                lastIndices[j] = i;
            }
        }

        char[] result = new char[len - clearCount];
        for(int i = 0, j = 0; i < len; i++) {
            if(isCleared[i]) {
                continue;
            }
            result[j++] = s.charAt(i);
        }
        return new String(result);
    }
}
