package com.shuangpeng.Problem.p0701_0800;

public class Problem0765CouplesHoldingHands {

    public int minSwapsCouples0(int[] row) {
        int n = row.length;
        int[] indices = new int[n];
        for (int i = 0; i < n; ++i) {
            indices[row[i]] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i += 2) {
            if (row[i] >> 1 == row[i + 1] >> 1) {
                continue;
            }
            int p = row[i] + ((row[i] & 1) == 0 ? 1 : -1);
            swap(row, indices, i + 1, indices[p]);
            ++ans;
        }
        return ans;
    }

    private void swap(int[] row, int[] indices, int i, int j) {
        indices[row[i]] = j;
        indices[row[j]] = i;
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;
    }

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int m = n >> 1;
        int[] parent = new int[m];
        for (int i = 0; i < m; ++i) {
            parent[i] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i += 2) {
            int j = row[i] >> 1, k = row[i + 1] >> 1;
            int pJ = findParent(parent, j);
            int pK = findParent(parent, k);
            if (pJ != pK) {
                parent[pJ] = pK;
                ++ans;
            }
        }
        return ans;
    }

    private int findParent(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        parent[i] = findParent(parent, parent[i]);
        return parent[i];
    }
}
