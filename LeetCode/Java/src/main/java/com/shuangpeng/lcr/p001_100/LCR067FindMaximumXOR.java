package com.shuangpeng.lcr.p001_100;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR067FindMaximumXOR（数组中两个数的最大异或值）
 * @date 2024/6/30 7:56 PM
 */
public class LCR067FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        int ans = 0, mask = 0, tmp = 0;
        for (int i = 1 << 30; i > 0; i >>= 1) {
            tmp = ans | i;
            mask |= i;
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains((num & mask) ^ tmp)) {
                    ans = tmp;
                    break;
                }
                set.add(num & mask);
            }
        }
        return ans;
    }
}

class LCR067FindMaximumXOR0 {

    class Trie {
        Trie[] tries = new Trie[2];
    }

    public int findMaximumXOR(int[] nums) {
        Trie root = new Trie();
        int ans = 0;
        for (int num : nums) {
            int value = 0;
            Trie trie = root, node = root;
            for (int i = 30; i >= 0; i--) {
                int b = num >> i & 1, r = b ^ 1;
                if (trie.tries[b] == null) {
                    trie.tries[b] = new Trie();
                }
                trie = trie.tries[b];
                if (node.tries[r] != null) {
                    value = value << 1 | 1;
                    node = node.tries[r];
                } else {
                    value <<= 1;
                    node = node.tries[b];
                }
            }
            ans = Math.max(ans, value);
        }
        return ans;
    }
}
