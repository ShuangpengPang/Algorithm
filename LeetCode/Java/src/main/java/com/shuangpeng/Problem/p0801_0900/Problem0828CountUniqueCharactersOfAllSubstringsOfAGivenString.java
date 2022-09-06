package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

/**
 * @Description:d（统计子串中的唯一字符
 * @Date 2022/9/6 11:14 AM
 **/
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

    public int uniqueLetterString1(String s) {
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

    public int uniqueLetterString2(String s) {
        List<Integer>[] indices = new List[26];
        for (int i = 0; i < 26; i++) {
            indices[i] = new ArrayList<>();
            indices[i].add(-1);
        }
        int n = s.length();
        for (int i = 0; i < n; i++) {
            indices[s.charAt(i) - 'A'].add(i);
        }
        for (int i = 0; i < 26; i++) {
            indices[i].add(n);
        }
        int ans = 0;
        for (List<Integer> idx : indices) {
            int m = idx.size();
            for (int i = 1; i < m - 1; i++) {
                int index = idx.get(i);
                int left = index - idx.get(i - 1), right = idx.get(i + 1) - index;
                ans += left * right;
            }
        }
        return ans;
    }

    public int uniqueLetterString3(String s) {
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int n = s.length();
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'A';
            prev[i] = last[j];
            last[j] = i;
        }
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            int j = prev[i];
            cnt += i - j - (j >= 0 ? j - prev[j] : 0);
            ans += cnt;
        }
        return ans;
    }

    public int uniqueLetterString(String s) {
        char[] cs = s.toCharArray();
        int[] last = new int[26], prevLast = new int[26];
        Arrays.fill(last, -1);
        Arrays.fill(prevLast, -1);
        int ans = 0, cnt = 0, n = cs.length;
        for (int i = 0; i < n; i++) {
            int j = cs[i] - 'A';
            cnt += i + prevLast[j] - (last[j] << 1);
            ans += cnt;
            prevLast[j] = last[j];
            last[j] = i;
        }
        return ans;
    }
}
