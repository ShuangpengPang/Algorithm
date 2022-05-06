package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0982TriplesWithBitwiseANDEqualToZero
 * @Date 2022/4/28 7:06 PM
 * @Version 1.0
 */
public class Problem0982TriplesWithBitwiseANDEqualToZero {

    class Trie {
        int count;
        Trie[] tries;

        Trie() {
            tries = new Trie[2];
            count = 0;
        }
    }

    private void add(Trie root, int num, int c) {
        for (int i = 0; i < 16; ++i) {
            int j = (num >> i) & 1;
            if (root.tries[j] == null) {
                root.tries[j] = new Trie();
            }
            root = root.tries[j];
        }
        root.count += c;
    }

    public int countTriplets0(int[] nums) {
        Trie root = new Trie();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            add(root, num, 1);
            for (int j = i + 1; j < n; ++j) {
                add(root, num & nums[j], 2);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dfs(root, nums[i], 0);
        }
        return ans;
    }

    private int dfs(Trie trie, int num, int i) {
        if (trie == null) {
            return 0;
        }
        if (i == 16) {
            return trie.count;
        }
        if (((num >> i) & 1) == 1) {
            return dfs(trie.tries[0], num, i + 1);
        }
        return dfs(trie.tries[0], num, i + 1) + dfs(trie.tries[1], num, i + 1);
    }

    public int countTriplets(int[] nums) {
        int k = 1;
        for (int num : nums) {
            while (k <= num) {
                k <<= 1;
            }
        }
        int[] memo = new int[k];
        for (int num : nums) {
            int mask = num ^ (k - 1);
            int i = mask;
            ++memo[0];
            while (i > 0) {
                ++memo[i];
                i = (i - 1) & mask;
            }
        }
        int ans = 0;
        for (int i : nums) {
            for (int j : nums) {
                ans += memo[i & j];
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0982TriplesWithBitwiseANDEqualToZero a =new Problem0982TriplesWithBitwiseANDEqualToZero();
//        int[] nums = {2, 1, 3};
//        a.countTriplets(nums);
//    }
}
