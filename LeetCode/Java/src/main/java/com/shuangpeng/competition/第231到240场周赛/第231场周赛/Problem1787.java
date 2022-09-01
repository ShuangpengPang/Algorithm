package com.shuangpeng.competition.第231到240场周赛.第231场周赛;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem1787 {

//    public int minChanges0(int[] nums, int k) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int n = nums.length;
//        int[][] dp = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            int j = i + k - 1;
//            int result = 0;
//            for (int m = i; m <= j; m++) {
//                result = result ^ nums[m];
//            }
//            dp[i][j] = result == 0 ? 0 : 1;
//        }
//
//    }

//    int minChanges(vector<int>& nums, int k) {
//        int n = nums.size();
//        vector<unordered_map<int, int>> groups(k);
//        vector<int> size(k);
//        for (int i = 0; i < k; ++i) {
//            for (int j = i; j < n; j += k) {
//                groups[i][nums[j]]++;
//                size[i]++;
//            }
//        }
//
//        vector<int> dp(1 << 10, INF);
//        dp[0] = 0;
//        for (int i = 0; i < k; ++i) {
//            int lo = *min_element(dp.begin(), dp.end());
//            vector<int> ndp(1 << 10, lo + size[i]);
//            for (int j = 0; j < (1 << 10); ++j) {
//                if (dp[j] == INF)
//                    continue;
//                for (auto [p, freq] : groups[i]) {
//                    int nxt = p ^ j;
//                    ndp[nxt] = min(ndp[nxt], dp[j] + size[i] - freq);
//                }
//            }
//            dp = move(ndp);
//        }
//
//        return dp[0];
//    }

    int[][] cnt;
    int[] sz;
    int[] nums;
    int k;
    public int cost(int pos,int num){
        return sz[pos]-cnt[pos][num];
    }
    Integer[][] memo;
    public int dfs(int pos,int num){//[0,pos]组之前异或值为num的cost
        if(pos==0)return cost(pos,num);
        if(memo[pos][num]!=null)return memo[pos][num];

        int res = Integer.MAX_VALUE/2;
        for(int i=pos;i<nums.length;i+=k){//枚举[0,1023]可以覆盖了所有情况，但是会超时
            //枚举这个组里的值x，那么前面需要值num^x，更新代价
            int x = nums[i];
            res = Math.min(res,cost(pos,x)+dfs(pos-1,num^x));
        }
        memo[pos][num] = res;
        return memo[pos][num];
    }
    public int[] get_min_cost(int k){//计算第k组的最小cost
        //res[0]==cost res[1]==众数个数
        int min_cost = Integer.MAX_VALUE/2;
        int mass_cnt = Integer.MAX_VALUE/2;
        for(int i=0;i<1024;i++){
            if(cost(k,i)<min_cost){
                min_cost = cost(k,i);
                mass_cnt = cnt[k][i];
            }
        }
        return new int[]{min_cost,mass_cnt};
    }
    public int minChanges0(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        int n = nums.length;
        cnt = new int[k][1024];
        sz =  new int[k];
        memo = new Integer[k][1024];
        for(int i=0;i<k;i++){
            for(int j=i;j<n;j+=k){
                cnt[i][nums[j]]++;
                sz[i]++;
            }
        }
        //dp从已有的数进行转移，先dp
        int res = dfs(k-1,0);

        //如果dp转移的时候枚举[0,1024]，虽然可以覆盖所有情况
        //但是复杂度变成k*n*1024了
        //只使用已有的数进行转移，可以将1024降为m，k*m=n，总复杂度就变成n^2了。
        //但是会漏一种情况，有的组选择了新的数，此时这个组的cost为 (组内元素个数)。
        //那么这种组有几个呢？
        //可能是0个。可能是...

        //贪心打补丁
        int sum_cost = 0;
        int cost_i = -1;
        int id = -1;
        int mass_cnt = Integer.MAX_VALUE/2;//众数
        for(int i=0;i<k;i++){
            int[] t = get_min_cost(i);//得到第i组的最小cost
            sum_cost += t[0];
            if(t[1]<mass_cnt){
                mass_cnt = t[1];
                id = i;
            }
        }
        //找到那个众数个数最小的组
        //本来的cost_i = sz[i]-mass_cnt 变成 sz[i]
        res = Math.min(sum_cost+mass_cnt,res);
        return res;
    }

    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        final int N = 1 << 10;
        final int INF = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[2][N];
        Arrays.fill(dp[0], INF);
        dp[0][0] = 0;
        for (int i = 0; i < k; ++i) {
            int idx = 1 - i % 2;
            Map<Integer, Integer> map = new HashMap<>();
            int size = 0;
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                ++size;
            }
            int minValue = Arrays.stream(dp[1 - idx]).min().getAsInt();
            Arrays.fill(dp[idx], size + minValue);
            for (int j = 0; j < N; ++j) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    dp[idx][j] = Math.min(dp[idx][j], dp[1 - idx][j ^ entry.getKey()] + size - entry.getValue());
                }
            }
        }
        return dp[k % 2][0];
    }
}
