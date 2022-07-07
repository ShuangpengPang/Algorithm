package com.shuangpeng.competition.第081场双周赛;

/**
 * @Description: Problem2317MaximumXORAfterOperations（操作后的最大异或和）
 * @Date 2022/7/4 3:17 PM
 * @Version 1.0
 */
public class Problem2317MaximumXORAfterOperations {

    // 比赛时写法
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans |= num;
        }
        return ans;
    }
}
