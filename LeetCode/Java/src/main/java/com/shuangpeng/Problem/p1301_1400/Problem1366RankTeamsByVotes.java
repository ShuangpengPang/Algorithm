package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1366RankTeamsByVotes（通过投票对团队排名）
 * @date 2023/8/9 11:07 AM
 */
public class Problem1366RankTeamsByVotes {

    public String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        int n = votes[0].length();
        for (String v : votes) {
            for (int i = 0; i < n; i++) {
                char c = v.charAt(i);
                map.computeIfAbsent(c, k -> new int[n])[i]++;
            }
        }
        Character[] cs = new Character[n];
        for (int i = 0; i < n; i++) {
            cs[i] = votes[0].charAt(i);
        }
        Arrays.sort(cs, (a, b) -> {
            int[] p1 = map.get(a), p2 = map.get(b);
            for (int i = 0; i < n; i++) {
                if (p1[i] != p2[i]) {
                    return p2[i] - p1[i];
                }
            }
            return a - b;
        });
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = cs[i];
        }
        return new String(arr);
    }
}
