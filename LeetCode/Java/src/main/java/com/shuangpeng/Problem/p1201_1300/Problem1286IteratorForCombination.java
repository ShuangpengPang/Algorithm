package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1286IteratorForCombination（字母组合迭代器）
 * @date 2023/6/19 2:38 PM
 */
public class Problem1286IteratorForCombination {

    class CombinationIterator {

        char[] cs;
        int[] index;
        int n;
        boolean next;

        public CombinationIterator(String characters, int combinationLength) {
            cs = characters.toCharArray();
            n = combinationLength;
            index = new int[n];
            Arrays.setAll(index, i -> i);
            next = true;
        }

        public String next() {
            char[] arr = new char[n];
            for (int i = 0; i < n; i++) {
                arr[i] = cs[index[i]];
            }
            int i = n - 1, m = cs.length;
            while (i >= 0 && index[i] == i + m - n) {
                i--;
            }
            if (i >= 0) {
                for (int j = index[i] + 1; i < n; i++, j++) {
                    index[i] = j;
                }
            } else {
                next = false;
            }
            return new String(arr);
        }

        public boolean hasNext() {
            return next;
        }
    }

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}

class Problem1286IteratorForCombination0 {

    class CombinationIterator {

        char[] cs;
        int hash, n, m, N;

        public CombinationIterator(String characters, int combinationLength) {
            cs = characters.toCharArray();
            m = cs.length;
            n = combinationLength;
            N = (1 << n) - 1;
            hash = N << m - n;
        }

        public String next() {
            char[] ans = new char[n];
            for (int i = 0, j = m - 1; i < n; i++, j--) {
                while ((hash >> j & 1) == 0) {
                    j--;
                }
                ans[i] = cs[m - j - 1];
            }
            hash--;
            while (hash >= N && Integer.bitCount(hash) != n) {
                hash--;
            }
            return new String(ans);
        }

        public boolean hasNext() {
            return hash >= N;
        }
    }
}

class Problem1286IteratorForCombination1 {

    class CombinationIterator {

        char[] cs;
        int m, n, hash = 0;

        public CombinationIterator(String characters, int combinationLength) {
            cs = characters.toCharArray();
            m = cs.length;
            n = combinationLength;
            hash = (1 << n) - 1 << m - n;
        }

        public String next() {
            char[] arr = new char[n];
            for (int i = 0, j = m - 1; i < n; i++, j--) {
                while ((hash >> j & 1) == 0) {
                    j--;
                }
                arr[i] = cs[m - j - 1];
            }
            hash &= hash + 1;
            if (hash != 0) {
                int lowBit = hash & -hash;
                hash ^= lowBit;
                int cnt = n - Integer.bitCount(hash);
                for (int i = 0; i < cnt; i++) {
                    lowBit >>= 1;
                    hash |= lowBit;
                }
            }
            return new String(arr);
        }

        public boolean hasNext() {
            return hash != 0;
        }
    }
}
