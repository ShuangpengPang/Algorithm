package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2271MaximumWhiteTilesCoveredByACarpet（毯子覆盖的最多白色砖块数）
 * @date 2023/11/19 7:31 PM
 */
public class Problem2271MaximumWhiteTilesCoveredByACarpet {

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int ans = 0, n = tiles.length, N = Integer.MAX_VALUE;
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        for (int i = 0, j = 0, length = 0; i < n && j < n && ans < carpetLen; i++) {
            int right = tiles[i][0] + carpetLen - 1;
            while (j < n && tiles[j][1] <= right) {
                length += tiles[j][1] - tiles[j][0] + 1;
                j++;
            }
            ans = Math.max(ans, length + Math.max(0, right - (j < n ? tiles[j][0] : N) + 1));
            length -= tiles[i][1] - tiles[i][0] + 1;
        }
        return ans;
    }
}
