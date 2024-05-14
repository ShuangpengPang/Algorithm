package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR101CanPartition（分割等和子集）
 * @date 2024/5/12 9:29 PM
 */
public class LCR101CanPartition {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int n = nums.length, i = 0; i < n && !dp[target]; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}

class LCR101CanPartition0 {

    boolean[] vis;
    public boolean canPartition(int[] nums) {
        //背包问题 - 从物品选择出价值是sum / 2的商品
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 == 1){
            return false;
        }
        int n = nums.length;
        int target = sum / 2;
        vis = new boolean[target + 1];
        return dfs(nums,0, 0, target);
    }
    public boolean dfs(int[] nums, int idx, int cur, int target){
        if(cur == target){
            //商品重量 = target
            return true;
        }
        if(cur > target || vis[cur]){
            return false;
        }
        vis[cur] = true;
        for(int i = idx; i < nums.length; i++){
            cur += nums[i];
            if(dfs(nums, i + 1, cur, target)){
                return true;
            }
            cur -= nums[i];
        }
        return false;
    }

}
