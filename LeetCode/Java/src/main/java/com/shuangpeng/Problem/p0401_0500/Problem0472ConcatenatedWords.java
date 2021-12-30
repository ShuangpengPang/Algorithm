package com.shuangpeng.Problem.p0401_0500;

import java.util.*;

public class Problem0472ConcatenatedWords {

    class Trie {
        boolean flag;
        Map<Character, Trie> trieMap;

        public Trie() {
            trieMap = new HashMap<>();
            flag = false;
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        int n = words.length;
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue;
            }
            Trie trie = root;
            int size = word.length();
            for (int j = 0; j < size; j++) {
                char c = word.charAt(j);
                Map<Character, Trie> map = trie.trieMap;
                if (!map.containsKey(c)) {
                    map.put(c, new Trie());
                }
                trie = map.get(c);
            }
            trie.flag = true;
        }
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!words[i].isEmpty() && backtrack(words[i], root, 0, false)) {
                answer.add(words[i]);
            }
        }
        return answer;
    }

    private boolean backtrack(String word, Trie root, int start, boolean valid) {
        int n = word.length();
        Trie trie = root;
        for (int i = start; i < n; i++) {
            Map<Character, Trie> map = trie.trieMap;
            char c = word.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            trie = map.get(c);
            if (trie.flag && backtrack(word, root, i + 1, true)) {
                return true;
            }
        }
        return trie.flag && valid;
    }

//    public static void main(String[] args) {
//        Problem0472ConcatenatedWords a = new Problem0472ConcatenatedWords();
//        a.findAllConcatenatedWordsInADict(new String[]{"q", ""});
//    }
}

class Problem0472ConcatenatedWords0 {
    class Trie {

        final int N = 26;
        boolean isWord;
        Trie[] tries;

        Trie() {
            isWord = false;
            tries = new Trie[N];
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie root = new Trie();
        Set<String> set = new HashSet<>();
        for (String w : words) {
            set.add(w);
            Trie trie = root;
            int n = w.length();
            for (int i = 0; i < n; ++i) {
                int j = w.charAt(i) - 'a';
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
            }
            trie.isWord = true;
        }
        List<String> ans = new ArrayList<>();
        Map<String, Boolean> memo = new HashMap<>();
        for (String w : words) {
            if (check(w, set, root, memo)) {
                ans.add(w);
            }
        }
        return ans;
    }

    private boolean check(String w, Set<String> set, Trie root, Map<String, Boolean> memo) {
        Boolean result = memo.get(w);
        if (result != null) {
            return result;
        }
        int n = w.length();
        Trie trie = root;
        for (int i = 0; i < n - 1; ++i) {
            int j = w.charAt(i) - 'a';
            trie = trie.tries[j];
            if (trie == null) {
                memo.put(w, false);
                return false;
            }
            if (trie.isWord) {
                String s = w.substring(i + 1);
                if (set.contains(s) || check(s, set, root, memo)) {
                    memo.put(w, true);
                    return true;
                }
            }
        }
        memo.put(w, false);
        return false;
    }
}

class Problem0472ConcatenatedWords1 {
    class Trie {

        final int N = 26;
        boolean isWord;
        Trie[] tries;

        Trie() {
            isWord = false;
            tries = new Trie[N];
        }
    }

    public List<String> findAllConcatenatedWordsInADict0(String[] words) {
        Trie root = new Trie();
        Arrays.sort(words, Comparator.comparingInt(a -> a.length()));
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String w : words) {
            if (check(w, set, root)) {
                ans.add(w);
            } else {
                set.add(w);
                insert(w, root);
            }
        }
        return ans;
    }

    private void insert(String w, Trie trie) {
        int n = w.length();
        for (int i = 0; i < n; ++i) {
            int j = w.charAt(i) - 'a';
            if (trie.tries[j] == null) {
                trie.tries[j] = new Trie();
            }
            trie = trie.tries[j];
        }
        trie.isWord = true;
    }

    private boolean check(String w, Set<String> set, Trie root) {
        int n = w.length();
        Trie trie = root;
        for (int i = 0; i < n - 1; ++i) {
            int j = w.charAt(i) - 'a';
            trie = trie.tries[j];
            if (trie == null) {
                return false;
            }
            if (trie.isWord) {
                String s = w.substring(i + 1);
                if (set.contains(s) || check(s, set, root)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 动态规划
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        final int M = 331, K = 131;
        Set<Long> set = new HashSet<>();
        for (String w : words) {
            long hash = 0;
            int n = w.length();
            for (int i = 0; i < n; ++i) {
                hash = hash * M + w.charAt(i) + K;
            }
            set.add(hash);
        }
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            if (check(w, set)) {
                ans.add(w);
            }
        }
        return ans;
    }

    boolean check(String s, Set<Long> set) {
        final int M = 331, K = 131;
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[i] == -1) {
                continue;
            }
            long hash = 0;
            for (int j = i; j < n; ++j) {
                hash = hash * M + s.charAt(j) + K;
                if (set.contains(hash)) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[i] + 1);
                }
            }
        }
        return dp[n] > 1;
    }
}
