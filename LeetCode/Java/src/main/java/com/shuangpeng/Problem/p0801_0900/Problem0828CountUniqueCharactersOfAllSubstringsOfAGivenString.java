package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0828CountUniqueCharactersOfAllSubstringsOfAGivenString {

    public int uniqueLetterString0(String s) {
        int n = s.length();
        int N = 26;
        int[] index = new int[N];
        Arrays.fill(index, -1);
        int[] previous = new int[n];
        for (int i = 0; i < n; ++i) {
            previous[i] = index[s.charAt(i) - 'A'];
            index[s.charAt(i) - 'A'] = i;
        }
        int total = 1;
        int count = 1;
        for (int i = 1; i < n; ++i) {
            int j = previous[i];
            int k = j == -1 ? -1 : previous[j];
            count += i + k - (j << 1);
            total += count;
        }
        return total;
    }

    public int uniqueLetterString(String s) {
        int n = s.length();
        Map<Character, List<Integer>> indexMap = new HashMap<>(26);
        for (int i = 0; i < n; ++i) {
            indexMap.computeIfAbsent(s.charAt(i), x -> new ArrayList<>()).add(i);
        }
        final int M = (int) 1e9 + 7;
        long answer = 0;
        for (char c : indexMap.keySet()) {
            List<Integer> index = indexMap.get(c);
            int size = index.size();
            for (int i = 0; i < size; ++i) {
                int start = i == 0 ? -1 : index.get(i - 1);
                int end = i == size - 1 ? n : index.get(i + 1);
                answer += (index.get(i) - start) * (end - index.get(i));
            }
            answer %= M;
        }
        return (int) answer;
    }
}
