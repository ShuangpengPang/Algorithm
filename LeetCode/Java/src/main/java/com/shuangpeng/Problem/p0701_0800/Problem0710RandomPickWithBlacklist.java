package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

/**
 * @Description: Problem0710RandomPickWithBlacklist（黑名单中的随机数）
 * @Date 2022/6/27 2:18 PM
 * @Version 1.0
 */
public class Problem0710RandomPickWithBlacklist {

    class Solution {

        Random random;
        int[] blacklist;
        int m;

        public Solution(int n, int[] blacklist) {
            Arrays.sort(blacklist);
            this.random = new Random();
            this.m = n - blacklist.length;
            this.blacklist = blacklist;
        }

        public int pick() {
            int num = this.random.nextInt(this.m);
            int left = 0, right = this.blacklist.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (num + mid < this.blacklist[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return num + left;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
}

class Problem0710RandomPickWithBlacklist0 {
    class Solution {

        Random random;
        Map<Integer, Integer> map;
        int m;

        public Solution(int n, int[] blacklist) {
            random = new Random();
            m = n - blacklist.length;
            Set<Integer> set = new HashSet<>();
            for (int num : blacklist) {
                if (num >= m) {
                    set.add(num);
                }
            }
            map = new HashMap<>();
            int i = m;
            for (int num : blacklist) {
                if (num < m) {
                    while (set.contains(i)) {
                        i++;
                    }
                    map.put(num, i);
                    i++;
                }
            }
        }

        public int pick() {
            int num = random.nextInt(m);
            return map.getOrDefault(num, num);
        }
    }
}

class Problem0710RandomPickWithBlacklist1 {
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        int len = 0;

        public Solution(int n, int[] blacklist) {
            int lenb = blacklist.length;
            len = n - lenb;
            int[] isout = new int[lenb];
            for (int j : blacklist) {
                if (j >= len) {
                    isout[j - len] = 1;
                }
            }
            int leftindex = 0;
            for (int j : blacklist) {
                if (j < len) {
                    while (isout[leftindex] == 1) {
                        leftindex++;
                    }
                    map.put(j, leftindex + len);
                    leftindex++;
                }
            }
        }

        public int pick() {
            int index = (int) (Math.random() * len);
            return map.getOrDefault(index, index);
        }
    }
}


