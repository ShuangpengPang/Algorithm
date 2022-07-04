package com.shuangpeng.competition.第081场双周赛;

/**
 * @Description: Problem2315CountAsterisks（统计星号）
 * @Date 2022/7/4 1:55 PM
 * @Version 1.0
 */
public class Problem2315CountAsterisks {

    public int countAsterisks(String s) {
        int n = s.length();
        boolean outer = true;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*' && outer) {
                ans++;
            } else if (c == '|') {
                outer = !outer;
            }
        }
        return ans;
    }
}
