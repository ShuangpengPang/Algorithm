package com.shuangpeng.competition.第301到310场周赛.第304场周赛;

/**
 * @Description: Problem2358MaximumNumberOfGroupsEnteringACompetition（分组的最大数量）
 * @Date 2022/8/6 5:17 PM
 * @Version 1.0
 */
public class Problem2358MaximumNumberOfGroupsEnteringACompetition {

    public int maximumGroups(int[] grades) {
        int n = grades.length << 1;
        int left = 1, right = n;
        while (left <= right) {
            int x = left + (right - left >> 1);
            if ((long) x * (x + 1) <= n) {
                left = x + 1;
            } else {
                right = x - 1;
            }
        }
        return left - 1;
    }
}
