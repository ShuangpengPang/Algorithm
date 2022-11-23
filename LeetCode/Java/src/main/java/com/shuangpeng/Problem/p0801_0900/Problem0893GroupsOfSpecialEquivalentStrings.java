package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0893GroupsOfSpecialEquivalentStrings（特殊等价字符串组）
 * @date 2022/11/23 11:54 PM
 */
public class Problem0893GroupsOfSpecialEquivalentStrings {

    public int numSpecialEquivGroups(String[] words) {
        int n = words[0].length(), h = n >> 1;
        Set<String> set = new HashSet<>();
        char[] even = new char[n - h], odd = new char[h];
        for (String w : words) {
            for (int i = 0; i < n; i++) {
                char c = w.charAt(i);
                if ((i & 1) == 0) {
                    even[i >> 1] = c;
                } else {
                    odd[i >> 1] = c;
                }
            }
            Arrays.sort(even);
            Arrays.sort(odd);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int j = i >> 1;
                if ((i & 1) == 0) {
                    sb.append(even[j]);
                } else {
                    sb.append(odd[j]);
                }
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
