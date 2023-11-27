package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2536IncrementSubmatricesByOne（子矩阵元素加1）
 * @date 2023/11/27 3:30 PM
 */
public class Problem2536IncrementSubmatricesByOne {

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] ans = new int[n][n];
        for (int[] q : queries) {
            ans[q[0]][q[1]] += 1;
            if (q[3] < n - 1) {
                ans[q[0]][q[3] + 1] += -1;
            }
            if (q[2] < n - 1) {
                ans[q[2] + 1][q[1]] += -1;
            }
            if (q[2] < n - 1 && q[3] < n - 1) {
                ans[q[2] + 1][q[3] + 1] += 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] += (i == 0 ? 0 : ans[i - 1][j]) + (j == 0 ? 0 : ans[i][j - 1]);
                ans[i][j] -= (i > 0 && j > 0 ? ans[i - 1][j - 1] : 0);
            }
        }
        return ans;
    }
}

//二维树状数组
class SegmentTree2D{
    int n, m;
    int[][] tree;

    public SegmentTree2D(int n, int m){
        this.n = n;
        this.m = m;
        tree = new int[n + 1][m + 1];
    }

    int lowbit(int x){
        return x & (-x);
    }

    void update(int x, int y, int val){
        while(x <= n){
            int y1 = y;
            while(y1 <= m){
                tree[x][y1] += val;
                y1 += lowbit(y1);
            }
            x += lowbit(x);
        }
    }

    int query(int x, int y){
        int res = 0;
        while(x > 0){
            int y1 = y;
            while(y1 > 0){
                res += tree[x][y1];
                y1 -= lowbit(y1);
            }
            x -= lowbit(x);
        }
        return res;
    }
}

class Problem2536IncrementSubmatricesByOne0 {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        SegmentTree2D seg = new SegmentTree2D(n, n);
        for(int[] q: queries){
            seg.update(q[0] + 1, q[1] + 1, 1);
            seg.update(q[2] + 2, q[1] + 1, -1);
            seg.update(q[0] + 1, q[3] + 2, -1);
            seg.update(q[2] + 2, q[3] + 2, 1);
        }
        int[][] res = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                res[i][j] = seg.query(i + 1, j + 1);
            }
        }
        return res;
    }
}
