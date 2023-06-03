package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1079LetterTilePossibilities（活字印刷）
 * @date 2023/5/18 4:39 PM
 */
public class Problem1079LetterTilePossibilities {

    char[] cs;
    boolean[] visited;
    int n;
    int count;

    public int numTilePossibilities(String tiles) {
        cs = tiles.toCharArray();
        n = cs.length;
        visited = new boolean[n];
        Arrays.sort(cs);
        count = 0;
        for (int i = 1; i <= n; i++) {
            dfs(0, new char[i]);
        }
        return count;
    }

    private void dfs(int index, char[] ans) {
        if (index == ans.length) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && cs[i] == cs[i - 1])) {
                continue;
            }
            visited[i] = true;
            ans[index] = cs[i];
            dfs(index + 1, ans);
            visited[i] = false;
        }
    }
}

class Problem1079LetterTilePossibilities0 {

    public int numTilePossibilities0(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : tiles.toCharArray()) {
            count.merge(c, 1, Integer::sum);
        }
        return dfs(count, tiles.length()) - 1;
    }

    private int dfs(Map<Character, Integer> count, int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 1;
        for (char c : count.keySet()) {
            int cnt = count.get(c);
            if (cnt > 0) {
                count.put(c, cnt - 1);
                ans += dfs(count, n - 1);
                count.put(c, cnt);
            }
        }
        return ans;
    }
}

class Problem1079LetterTilePossibilities1 {

    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : tiles.toCharArray()) {
            count.merge(c, 1, Integer::sum);
        }
        return dfs(count);
    }

    private int dfs(Map<Character, Integer> map) {
        int ans = 0;
        for (char c : map.keySet()) {
            int cnt = map.get(c);
            if (cnt > 0) {
                ans++;
                map.put(c, cnt - 1);
                ans += dfs(map);
                map.put(c, cnt);
            }
        }
        return ans;
    }
}

class Problem1079LetterTilePossibilities2 {

    public int numTilePossibilities(String tiles) {
        int[] cnt = new int[26];
        for (char c : tiles.toCharArray()) {
            cnt[c - 'A']++;
        }
        return dfs(cnt, tiles.length(), false);
    }

    private int dfs(int[] cnt, int n, boolean hasData) {
        if (n == 0) {
            return hasData ? 1 : 0;
        }
        int ans = hasData ? 0 : dfs(cnt, n - 1, false);
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                ans += dfs(cnt, n - 1, true);
                cnt[i]++;
            }
        }
        return ans;
    }
}

class Problem1079LetterTilePossibilities3 {

    static final int N = 8;
    static int[][] c = new int[8][N];
    static {
        for (int i = 0; i < 8; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
    }

    public int numTilePossibilities(String tiles) {
        int[] cnt = new int[26];
        for (char c : tiles.toCharArray()) {
            cnt[c - 'A']++;
        }
        int n = tiles.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0, count = 0; i < 26; i++) {
            count += cnt[i];
            for (int j = count; j > 0; j--) {
                for (int k = 1; k <= Math.min(j, cnt[i]); k++) {
                    dp[j] += dp[j - k] * c[j][k];
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dp[i];
        }
        return ans;
    }
}

class Problem1079LetterTilePossibilities4 {

    class Trie{
        Trie children[];
        public Trie(){
            children=new Trie[26];
        }
    }

    int ans=0;
    public int numTilePossibilities(String tiles) {
        find(0,new Trie(),tiles);
        return ans;
    }
    void find(int mask,Trie trie,String t){
        for(int i=0;i<t.length();i++){
            if((mask>>i&1)==0){
                int a=t.charAt(i)-'A';
                if(trie.children[a]==null){
                    ans++;
                    trie.children[a]=new Trie();
                }
                find(mask^(1<<i),trie.children[a],t);
            }
        }
    }
}
