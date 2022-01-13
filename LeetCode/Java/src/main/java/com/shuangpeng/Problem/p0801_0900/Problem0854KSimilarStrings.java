package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0854KSimilarStrings {

    public int kSimilarity(String s1, String s2) {
        int length = s1.length();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        long hash = 0;
        for (int i = 0; i < length; ++i) {
            int c1 = s1.charAt(i) - 'a' + 1, c2 = s2.charAt(i) - 'a' + 1;
            if (c1 != c2) {
                list1.add(c1);
                list2.add(c2);
                hash = hash * 7 + c1;
            }
        }
        return dfs(list1, list2, 0, hash, new HashMap<>());
    }

    private int dfs(List<Integer> list1, List<Integer> list2, int i, long hash, Map<Long, Integer> memo) {
        int n = list1.size();
        if (i == n) {
            return 0;
        }
        int m = memo.getOrDefault(hash, -1);
        if (m != -1) {
            return m;
        }
        int num1 = list1.get(i), num2 = list2.get(i);
        if (num1 == num2) {
            return dfs(list1, list2, i + 1, hash, memo);
        }
        int ans = n;
        for (int j = i + 1; j < n; ++j) {
            if (list1.get(j) == num2) {
                swap(list1, i, j);
                long h = hash + (long) (Math.pow(7, n - i - 1) - Math.pow(7, n - j - 1)) * (num2 - num1);
                ans = Math.min(ans, 1 + dfs(list1, list2, i + 1, h, memo));
                swap(list1, i, j);
            }
        }
        memo.put(hash, ans);
        return ans;
    }

    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    String[] alphabet = new String[]{"a", "b", "c", "d", "e", "f"};
    Map<String, Integer> memo;

    public int kSimilarity0(String A, String B) {
        if (A.equals(B)) return 0;
        int N = A.length();
        memo = new HashMap();
        int ans = 0;

        int[] count = new int[alphabet.length * alphabet.length];
        for (int i = 0; i < N; ++i)
            if (A.charAt(i) != B.charAt(i)) {
                count[alphabet.length * (A.charAt(i) - 'a') + (B.charAt(i) - 'a')]++;
                ans++;
            }

        List<int[]> possibles = new ArrayList();
        // Enumerate over every cycle
        for (int size = 2; size <= alphabet.length; ++size)
            search: for (String cycle: permutations(alphabet, 0, size)) {
                // Check if cycle is canonical
                for (int i = 1; i < size; ++i)
                    if (cycle.charAt(i) < cycle.charAt(0))
                        continue search;

                // Add count to possibles
                int[] row = new int[count.length];
                for (int i = 0; i < size; ++i) {
                    int u = cycle.charAt(i) - 'a';
                    int v = cycle.charAt((i+1) % size) - 'a';
                    row[alphabet.length * u + v]++;
                }
                possibles.add(row);
            }

        int[] ZERO = new int[count.length];
        memo.put(Arrays.toString(ZERO), 0);
        return ans - numCycles(possibles, count);
    }

    public int numCycles(List<int[]> possibles, int[] count) {
        String countS = Arrays.toString(count);
        if (memo.containsKey(countS)) return memo.get(countS);

        int ans = Integer.MIN_VALUE;
        search: for (int[] row: possibles) {
            int[] count2 = count.clone();
            for (int i = 0; i < row.length; ++i) {
                if (count2[i] >= row[i])
                    count2[i] -= row[i];
                else
                    continue search;
            }
            ans = Math.max(ans, 1 + numCycles(possibles, count2));
        }

        memo.put(countS, ans);
        return ans;
    }

    public List<String> permutations(String[] alphabet, int used, int size) {
        List<String> ans = new ArrayList();
        if (size == 0) {
            ans.add(new String(""));
            return ans;
        }

        for (int b = 0; b < alphabet.length; ++b)
            if (((used >> b) & 1) == 0)
                for (String rest: permutations(alphabet, used | (1 << b), size - 1))
                    ans.add(alphabet[b] + rest);
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0854KSimilarStrings a = new Problem0854KSimilarStrings();
//        int ans = a.kSimilarity("abc", "bca");
//        int i = 1;
//    }
}
