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
        Trie[] tries = new Trie[2];
        int count;
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Trie root = new Trie();
        int m = matrix.length, n = matrix[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            Trie trie = root;
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j] ^ matrix[i][0];
                if (trie.tries[num] == null) {
                    trie.tries[num] = new Trie();
                }
                trie = trie.tries[num];
            }
            trie.count++;
            ans = Math.max(ans, trie.count);
        }
        return ans;
    }
}

class Problem1072FlipColumnsForMaximumNumberOfEqualRows1 {

    public int maxEqualRowsAfterFlips(int[][] matrix) {

        int len = matrix.length;
        int[] index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i;
        }
        return radixSort(matrix, index, 0, len - 1, 1);
    }

    // base on the first element at each line
    public int radixSort(int[][] arr, int[] index, int start, int end, int cur) {
        if (start > end) return 0;
        if (cur == arr[0].length) return end - start + 1;

        int i = start, j = end;

        while (i <= j) {
            while(i <= j && arr[index[i]][cur] == arr[index[i]][0]) i++;
            while(i <= j && arr[index[j]][cur] != arr[index[j]][0]) j--;
            if (i > j) break;
            // Swap.
            int temp = index[i];
            index[i] = index[j];
            index[j] = temp;
        }

        return Math.max(
                radixSort(arr, index, start, j, cur + 1),
                radixSort(arr, index, i, end, cur + 1)
        );
    }
}
