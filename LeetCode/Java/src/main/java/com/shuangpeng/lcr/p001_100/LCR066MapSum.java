package com.shuangpeng.lcr.p001_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR066MapSum（键值映射）
 * @date 2024/6/27 10:24 AM
 */
public class LCR066MapSum {

    class MapSum {

        class Trie {
            private Trie[] tries = new Trie[26];
            private int sum = 0;
        }

        private Map<String, Integer> map;
        private Trie root;

        /** Initialize your data structure here. */
        public MapSum() {
            map = new HashMap<>();
            root = new Trie();
        }

        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            Trie trie = root;
            for (char ch : key.toCharArray()) {
                int c = ch - 'a';
                if (trie.tries[c] == null) {
                    trie.tries[c] = new Trie();
                }
                trie = trie.tries[c];
                trie.sum += delta;
            }
        }

        public int sum(String prefix) {
            Trie trie = root;
            for (char ch : prefix.toCharArray()) {
                int c = ch - 'a';
                if (trie.tries[c] == null) {
                    return 0;
                }
                trie = trie.tries[c];
            }
            return trie.sum;
        }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
}
