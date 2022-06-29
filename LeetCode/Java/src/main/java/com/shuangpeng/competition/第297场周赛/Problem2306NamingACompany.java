package com.shuangpeng.competition.第297场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2306NamingACompany（公司命名）
 * @Date 2022/6/29 1:52 PM
 * @Version 1.0
 */
public class Problem2306NamingACompany {

    public long distinctNames(String[] ideas) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : ideas) {
            String str = s.substring(1);
            map.put(str, map.getOrDefault(str, 0) | 1 << (s.charAt(0) - 'a'));
        }
        int N = 26;
        int[][] cnt = new int[N][N];
        long ans = 0;
        for (int h : map.values()) {
            for (int i = 0; i < N; i++) {
                if ((h & (1 << i)) == 0) {
                    for (int j = 0; j < N; j++) {
                        if ((h & (1 << j)) != 0) {
                            cnt[i][j]++;
                        }
                    }
                } else {
                    for (int j = 0; j < N; j++) {
                        if ((h & (1 << j)) == 0) {
                            ans += cnt[i][j];
                        }
                    }
                }
            }
        }
        return ans << 1;
    }
}
