package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem1074NumberOfSubmatricesThatSumToTarget（元素和为目标值的子矩阵数量）
 * @Date 2022/5/17 7:03 PM
 * @Version 1.0
 */
public class Problem1074NumberOfSubmatricesThatSumToTarget {

    // x1, y1   x2, y2
    // (x1 - 1, y2)   (x2, y1 - 1)
    // F(x2, y2) - F(x1 - 1, y2) - F(x2, y1 - 1) + F(x1 - 1, y1 - 1) = target
    // F(x1 - 1, y1 - 1) - F(x1 - 1, y2) + F(x2, y2) - F(x2, y1 - 1) = target
    // F(x2, y2) - F(x2, y1 - 1) - (F(x1 - 1, y2) - F(x1 - 1, y1 - 1)) = target

    public int numSubmatrixSumTarget0(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int r = 1; r <= n; ++r) {
            for (int l = 0; l < r; ++l) {
                Map<Integer, Integer> map = new HashMap<>(m + 1);
                map.put(0, 1);
                for (int i = 1; i <= m; ++i) {
                    int sum = preSum[i][r] - preSum[i][l];
                    ans += map.getOrDefault(sum - target, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return ans;
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans=0;
        for(int i=0;i<m;i++){//上边界
            int[] temp = new int[n];
            for(int j=i;j<m;j++){//下边界
                for(int k=0;k<n;k++){
                    //列前缀和
                    temp[k]+=matrix[j][k];
                }
                ans+=search(temp,target);
            }
        }
        return ans;
    }

    public int search(int[] nums,int target){
        Count map = new Count(nums.length);
        map.put(0);
        int cnt=0;
        int sum=0;
        for(int x:nums){
            sum+=x;//行前回合
            cnt+=map.get(sum-target);
            map.put(sum);
        }
        return cnt;
    }

    public static final int HASH_NUM = 1000_000_007;

    public static class Count{
        int[] keys;
        int[] counts;
        int mask;

        Count(int size){
            size = 2<<Integer.SIZE - Integer.numberOfLeadingZeros(size);
            this.keys = new int[size];
            this.counts = new int[size];
            this.mask = size-1;
        }

        public void put(int key){
            int idx = hash(key);
            for(;counts[idx]>0;idx=(idx+1)&mask){
                if(keys[idx]==key){
                    counts[idx]++;
                    return;
                }
            }

            keys[idx]=key;
            counts[idx]=1;
        }

        public int get(int key){
            int idx = hash(key);
            for(;counts[idx]>0;idx=(idx+1)&mask){
                if(keys[idx]==key){
                    return counts[idx];
                }
            }
            return 0;
        }

        public int hash(int key){
            return (key*HASH_NUM)&mask;
        }

        public void clear(){
            Arrays.fill(counts,0);
        }
    }
}
