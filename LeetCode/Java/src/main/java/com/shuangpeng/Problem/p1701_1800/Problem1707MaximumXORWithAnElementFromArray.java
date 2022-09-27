package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1707MaximumXORWithAnElementFromArray（与数组中元素的最大异或值）
 * @Date 2022/9/27 11:53 AM
 * @Version 1.0
 */
public class Problem1707MaximumXORWithAnElementFromArray {

    class Trie {
        Trie[] tries;

        Trie() {
            tries = new Trie[2];
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int m = nums.length, n = queries.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = queries[i][0];
            arr[i][1] = queries[i][1];
            arr[i][2] = i;
        }
        Arrays.sort(nums);
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
        Trie root = new Trie();
        int mask = getHighestBit(nums[m - 1]);
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            int x = arr[i][0], y = arr[i][1];
            while (j < m && nums[j] <= y) {
                addNum(root, nums[j], mask);
                j++;
            }
            ans[arr[i][2]] = j == 0 ? -1 : getMaxNum(root, x, mask);
        }
        return ans;
    }

    private int getHighestBit(int num) {
        int n = 1;
        while (num >= (n << 1)) {
            n <<= 1;
        }
        return n;
    }

    private void addNum(Trie root, int num, int mask) {
        Trie trie = root;
        while (mask != 0) {
            int b = (num & mask) == 0 ? 0 : 1;
            if (trie.tries[b] == null) {
                trie.tries[b] = new Trie();
            }
            trie = trie.tries[b];
            mask >>= 1;
        }
    }

    private int getMaxNum(Trie root, int x, int mask) {
        int num = 0;
        Trie trie = root;
        while (mask != 0) {
            int b = (x & mask) == 0 ? 0 : 1;
            b ^= 1;
            if (trie.tries[b] == null) {
                b ^= 1;
            }
            if (b == 1) {
                num |= mask;
            }
            trie = trie.tries[b];
            mask >>= 1;
        }
        return x ^ num;
    }
}

class Problem1707MaximumXORWithAnElementFromArray0 {

    class Trie {
        static final int L = 30;
        Trie[] tries = new Trie[2];
        int min = Integer.MAX_VALUE;

        public void insert(int num) {
            min = Math.min(min, num);
            Trie trie = this;
            for (int i = L - 1; i >= 0; i--) {
                int b = (num >> i) & 1;
                if (trie.tries[b] == null) {
                    trie.tries[b] = new Trie();
                }
                trie = trie.tries[b];
                trie.min = Math.min(trie.min, num);
            }
        }

        public int getMaxXor(int x, int m) {
            Trie trie = this;
            int ans = 0;
            for (int i = L - 1; i >= 0; i--) {
                int b = (x >> i) & 1;
                if (trie.tries[b ^ 1] != null && trie.tries[b ^ 1].min <= m) {
                    ans |= 1 << i;
                    b ^= 1;
                }
                trie = trie.tries[b];
            }
            return ans;
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie root = new Trie();
        for (int num : nums) {
            root.insert(num);
        }
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int x = queries[i][0], m = queries[i][1];
            ans[i] = root.min > m ? -1 : root.getMaxXor(x, m);
        }
        return ans;
    }
}
