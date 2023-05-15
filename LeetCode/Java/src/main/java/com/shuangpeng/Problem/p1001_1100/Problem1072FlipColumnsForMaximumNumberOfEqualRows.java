package com.shuangpeng.Problem.p1001_1100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1072FlipColumnsForMaximumNumberOfEqualRows（按列翻转得到最大值等行数）
 * @date 2023/5/15 2:19 PM
 */
public class Problem1072FlipColumnsForMaximumNumberOfEqualRows {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>(m);
        char[] cs = new char[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cs[j] = (char) (matrix[i][j] ^ matrix[i][0]);
            }
            ans = Math.max(ans, map.merge(new String(cs), 1, Integer::sum));
        }
        return ans;
    }
}

class Problem1072FlipColumnsForMaximumNumberOfEqualRows0 {

    class Trie {
        Trie left, right;
        int cnt;
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Trie root = new Trie();
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            Trie trie = root;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == matrix[i][0]) {
                    if (trie.left == null) {
                        trie.left = new Trie();
                    }
                    trie = trie.left;
                } else {
                    if (trie.right == null) {
                        trie.right = new Trie();
                    }
                    trie = trie.right;
                }
            }
            trie.cnt++;
            ans = Math.max(ans, trie.cnt);
        }
        return ans;
    }
}
