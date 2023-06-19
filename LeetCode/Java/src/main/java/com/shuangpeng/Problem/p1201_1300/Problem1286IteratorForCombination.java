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
        int[] idx;
        boolean next;

        public CombinationIterator(String characters, int combinationLength) {
            cs = characters.toCharArray();
            idx = new int[combinationLength];
            Arrays.setAll(idx, i -> i);
            next = true;
        }

        public String next() {
            char[] arr = new char[idx.length];
            for (int i = 0; i < idx.length; i++) {
                arr[i] = cs[idx[i]];
            }
            String ans = new String(arr);
            int index = getIndex();
            if (index >= 0) {
                idx[index]++;
                for (int k = index + 1; k < idx.length; k++) {
                    idx[k] = idx[k - 1] + 1;
                }
            } else {
                next = false;
            }
            return ans;
        }

        public boolean hasNext() {
            return next;
        }

        private int getIndex() {
            int i = cs.length - 1, j = idx.length - 1;
            while (j >= 0 && i == idx[j]) {
                i--;
                j--;
            }
            return j;
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
