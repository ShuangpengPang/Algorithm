package com.shuangpeng.competition.第298场周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem2311LongestBinarySubsequenceLessThanOrEqualToK（小于等于K的最长二进制子序列）
 * @Date 2022/6/30 7:31 PM
 * @Version 1.0
 */
public class Problem2311LongestBinarySubsequenceLessThanOrEqualToK {

    public int longestSubsequence(String s, int k) {
        List<Integer> list = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - '0';
            int size = list.size();
            if (size == 0) {
                list.add(j);
                continue;
            }
            if (list.get(size - 1) * 2 + j <= k) {
                list.add(list.get(size - 1) * 2 + j);
            }
            for (int t = size - 2; t >= 0; t--) {
                int num = list.get(t) * 2 + j;
                if (num <= k) {
                    list.set(t + 1, Math.min(num, list.get(t + 1)));
                }
            }
            list.set(0, Math.min(list.get(0), j));
        }
        return list.size();
    }

//    public int longestSubsequence(String s, int k) {
//        int n = s.length();
//        int ans = 0;
//        for(char c : s.toCharArray()) {
//            ans += '1'-c;
//        }
//        long val = 0;
//        for(int i=0;i<n;i++) {
//            if(s.charAt(n-1-i)=='1') {
//                if((val+=(1L<<i))<=k) {
//                    ans++;
//                }else {
//                    break;
//                }
//            }
//        }
//        return ans;
//    }
}
