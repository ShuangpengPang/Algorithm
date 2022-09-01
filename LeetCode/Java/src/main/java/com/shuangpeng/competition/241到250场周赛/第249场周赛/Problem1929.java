package com.shuangpeng.competition.第249场周赛;

public class Problem1929 {

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] answer = new int[2 * n];
        for (int i = 0; i < n; i++) {
            answer[i] = nums[i];
            answer[i + n] = nums[i];
        }
        return answer;
    }
}
