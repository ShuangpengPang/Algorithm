package com.shuangpeng.competition.第299场周赛;

/**
 * @Description: Problem2320CountNumberOfWaysToPlaceHouses（统计放置房子的方式数）
 * @Date 2022/7/7 4:35 PM
 * @Version 1.0
 */
public class Problem2320CountNumberOfWaysToPlaceHouses {

    public int countHousePlacements(int n) {
        int M = (int) 1e9 + 7;
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            a = (a + b) % M;
            b = (a - b + M) % M;
        }
        int sum = (a + b) % M;
        return (int) ((long) sum * sum % M);
    }
}
