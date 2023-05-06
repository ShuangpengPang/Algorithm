package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1419MinimumNumberOfFrogsCroaking（数青蛙）
 * @date 2023/5/6 10:00 AM
 */
public class Problem1419MinimumNumberOfFrogsCroaking {

    public int minNumberOfFrogs(String croakOfFrogs) {
        int[] next = new int[6];
        next[0] = Integer.MAX_VALUE;
        String s = "croak";
        int[] ids = new int[26];
        for (int i = 0; i < 5; i++) {
            ids[s.charAt(i) - 'a'] = i + 1;
        }
        int n = croakOfFrogs.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int j = ids[croakOfFrogs.charAt(i) - 'a'];
            if (next[j] == next[j - 1]) {
                return -1;
            }
            next[j]++;
            ans = Math.max(ans, next[1] - next[5]);
        }
        return next[1] == next[5] ? ans : -1;
    }
}

class Problem1419MinimumNumberOfFrogsCroaking0 {

    static int[] ids = new int[26];
    static {
        String s = "croak";
        for (int i = 0; i < 5; i++) {
            ids[s.charAt(i) - 'a'] = i;
        }
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int[] cnt = new int[5];
        for (char c : croakOfFrogs.toCharArray()) {
            int id = ids[c - 'a'], p = id == 0 ? 4 : id - 1;
            if (cnt[p] > 0) {
                cnt[p]--;
            } else if (id != 0) {
                return -1;
            }
            cnt[id]++;
        }
        for (int i = 0; i < 4; i++) {
            if (cnt[i] != 0) {
                return -1;
            }
        }
        return cnt[4];
    }
}
