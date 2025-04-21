package com.shuangpeng.Problem.p2701_2800;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2747CountZeroRequestServers（统计没有收到请求的服务器数目）
 * @date 2023/12/12 4:25 PM
 */
public class Problem2747CountZeroRequestServers {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        int m = queries.length, length = logs.length;
        Integer[] ids = new Integer[m];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparing(a -> queries[a]));
        int[] ans = new int[m], cnt = new int[n];
        int c = n;
        for (int i = 0, l = 0, r = 0; i < m; i++) {
            int id = ids[i], left = queries[id] - x;
            while (r < length && logs[r][1] <= queries[id]) {
                int s = logs[r][0] - 1;
                if (cnt[s]++ == 0) {
                    c--;
                }
                r++;
            }
            while (l < length && logs[l][1] < left) {
                int s = logs[l][0] - 1;
                if (--cnt[s] == 0) {
                    c++;
                }
                l++;
            }
            ans[id] = c;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem2747CountZeroRequestServers a = new Problem2747CountZeroRequestServers();
//        int[][] logs = {{2,4},{2,1},{1,2},{3,1}};
//        int[] queries = {3, 4};
//        a.countServers(3, logs, 2, queries);
//    }
}
