package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1255MaximumScoreWordsFormedByLetters（得分最高的单词集合）
 * @Date 2022/7/27 5:14 PM
 * @Version 1.0
 */
public class Problem1255MaximumScoreWordsFormedByLetters {

    int n, ans;
    String[] words;
    char[] letters;
    int[] score;
    int[][] cnt;
    int[] target;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        this.n = words.length;
        this.words = words;
        this.letters = letters;
        this.score = score;
        this.cnt = new int[n][26];
        this.target = new int[26];
        for (int i = 0; i < n; i++) {
            String w = words[i];
            int len = w.length();
            for (int j = 0; j < len; j++) {
                cnt[i][w.charAt(j) - 'a']++;
            }
        }
        for (char c : letters) {
            target[c - 'a']++;
        }
        ans = 0;
        dfs(0, new int[26]);
        return ans;
    }

    private void dfs(int i, int[] count) {
        int res = getScore(count);
        if (res == -1) {
            return;
        }
        if (i == n) {
            ans = Math.max(ans, res);
            return;
        }
        updateCount(count, i, 1);
        dfs(i + 1, count);
        updateCount(count, i, -1);
        dfs(i + 1, count);
    }

    private int getScore(int[] count) {
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > target[i]) {
                return -1;
            }
            res += count[i] * score[i];
        }
        return res;
    }

    private void updateCount(int[] count, int idx, int sign) {
        for (int i = 0; i < 26; i++) {
            count[i] += sign * cnt[idx][i];
        }
    }
}

class Problem1255MaximumScoreWordsFormedByLetters0 {

    String[] words;
    char[] letters;
    int[] score;
    int[][] cnt;
    int ans, n;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        this.words = words;
        this.letters = letters;
        this.score = score;
        this.n = words.length;
        this.ans = 0;
        cnt = new int[n][26];
        for (int i = 0; i < n; i++) {
            String w = words[i];
            int m = w.length();
            for (int j = 0; j < m; j++) {
                cnt[i][w.charAt(j) - 'a']++;
            }
        }
        int[] map = new int[26];
        for (char c : letters) {
            map[c - 'a']++;
        }
        dfs(map, 0, 0);
        return ans;
    }

    private void dfs(int[] map, int idx, int sum) {
        if (idx == n) {
            ans = Math.max(ans, sum);
            return;
        }
        boolean valid = true;
        int h = 0;
        for (int i = 0; i < 26; i++) {
            map[i] -= cnt[idx][i];
            h += cnt[idx][i] * score[i];
            if (map[i] < 0) {
                valid = false;
            }
        }
        if (valid) {
            dfs(map, idx + 1, sum + h);
        }
        for (int i = 0; i < 26; i++) {
            map[i] += cnt[idx][i];
        }
        dfs(map, idx + 1, sum);
    }
}

class Problem1255MaximumScoreWordsFormedByLetters1 {

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length, N = 26, M = 1 << n;
        int[][] cnt = new int[n][N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                cnt[i][words[i].charAt(j) - 'a']++;
            }
        }
        int[] total = new int[N];
        for (char c : letters) {
            total[c - 'a']++;
        }
        int ans = 0;
        for (int i = 1; i < M; i++) {
            int[] copy = total.clone();
            int s = 0;
            for (int j = 0; j < n && s >= 0; j++) {
                if (((i >> j) & 1) == 1) {
                    for (int k = 0; k < N; k++) {
                        if (cnt[j][k] > copy[k]) {
                            s = -1;
                            break;
                        }
                        copy[k] -= cnt[j][k];
                        s += cnt[j][k] * score[k];
                    }
                }
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }
}

class Problem1255MaximumScoreWordsFormedByLetters2 {

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] cnt = new int[26];
        for (char c : letters) {
            cnt[c - 'a']++;
        }
        int n = words.length, M = 1 << n, ans = 0;
        for (int i = 1; i < M; i++) {
            int s = 0;
            int[] copy = cnt.clone();
            for (int j = 0; j < n && s >= 0; j++) {
                if (((i >> j) & 1) == 0) {
                    continue;
                }
                for (int k = 0; k < words[j].length(); k++) {
                    int c = words[j].charAt(k) - 'a';
                    if (copy[c] <= 0) {
                        s = -1;
                        break;
                    }
                    s += score[c];
                    copy[c]--;
                }
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
