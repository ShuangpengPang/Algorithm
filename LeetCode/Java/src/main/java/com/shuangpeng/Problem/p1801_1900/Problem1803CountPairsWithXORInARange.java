package com.shuangpeng.Problem.p1801_1900;

/**
 * @Description: Problem1803CountPairsWithXORInARange（统计异或值在范围内的数对有多少）
 * @Date 2022/10/14 6:13 PM
 * @Version 1.0
 */
public class Problem1803CountPairsWithXORInARange {
    static int N = 14;

    class Trie {
        Trie[] tries = new Trie[2];
        int cnt;

        void add(int num) {
            Trie trie = this;
            trie.cnt++;
            for (int i = N; i >= 0; i--) {
                int j = ((num >> i) & 1) == 0 ? 0 : 1;
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
                trie.cnt++;
            }
        }

        int getCount(int num, int bit, int value, int low, int high) {
            int max = value | (1 << (bit + 1)) - 1;
            if (value >= low && max <= high) {
                return cnt;
            }
            if (max < low || value > high) {
                return 0;
            }
            int b = (num >> bit) & 1;
            int ans = 0;
            if (tries[0] != null) {
                ans += tries[0].getCount(num, bit - 1, value | b << bit, low, high);
            }
            if (tries[1] != null) {
                ans += tries[1].getCount(num, bit - 1, value | (b ^ 1) << bit, low, high);
            }
            return ans;
        }
    }

    Trie root;

    public int countPairs(int[] nums, int low, int high) {
        int n = nums.length, ans = 0;
        root = new Trie();
        for (int num : nums) {
            ans += root.getCount(num, N, 0, low, high);
            root.add(num);
        }
        return ans;
    }
}

class Problem1803CountPairsWithXORInARange0 {

    static int N = 14;

    class Trie {
        Trie[] tries = new Trie[2];
        int cnt;

        void insert(int num) {
            cnt++;
            Trie trie = this;
            for (int i = N; i >= 0; i--) {
                int j = (num >> i) & 1;
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
                trie.cnt++;
            }
        }

        int query(int num, int limit) {
            Trie trie = this;
            int ans = 0;
            for (int i = N; i >= 0 && trie != null; i--) {
                int b1 = (num >> i) & 1, b2 = (limit >> i) & 1;
                if (b2 == 1) {
                    if (trie.tries[b1] != null) {
                        ans += trie.tries[b1].cnt;
                    }
                    trie = trie.tries[b1 ^ 1];
                } else {
                    trie = trie.tries[b1];
                }
            }
            return ans + (trie == null ? 0 : trie.cnt);
        }
    }

    public int countPairs(int[] nums, int low, int high) {
        int ans = 0;
        Trie trie = new Trie();
        for (int num : nums) {
            ans += trie.query(num, high) - trie.query(num, low - 1);
            trie.insert(num);
        }
        return ans;
    }
}
