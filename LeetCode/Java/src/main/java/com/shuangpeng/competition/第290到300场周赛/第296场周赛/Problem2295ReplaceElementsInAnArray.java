package com.shuangpeng.competition.第290到300场周赛.第296场周赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem2295ReplaceElementsInAnArray（替换数组中的元素）
 * @Date 2022/6/11 4:43 PM
 * @Version 1.0
 */
public class Problem2295ReplaceElementsInAnArray {

    // 比赛时写法
    public int[] arrayChange0(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        for (int[] opt : operations) {
            int a = opt[0], b = opt[1];
            List<Integer> list = map.getOrDefault(b, new ArrayList<>());
            for (int index : map.get(a)) {
                nums[index] = b;
                list.add(index);
            }
            map.remove(a);
            map.put(b, list);
        }
        return nums;
    }

    public int[] arrayChange1(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            map.put(nums[i], i);
        }
        for (int[] opt : operations) {
            int num1 = opt[0], num2 = opt[1];
            int index = map.get(num1);
            nums[index] = num2;
            map.remove(num1);
            map.put(num2, index);
        }
        return nums;
    }

    public int[] arrayChange(int[] nums, int[][] operations) {
        int i,n=nums.length,max=0;
        for(int t:nums){
            max=Math.max(t,max);
        }
        for(i=0;i<operations.length;i++){
            max=Math.max(operations[i][1],max);
        }
        int[] ls=new int[max+1];
        for(i=0;i<n;i++){
            ls[nums[i]]=i;
        }
        for(i=0;i<operations.length;i++){
            ls[operations[i][1]]=ls[operations[i][0]];
            nums[ls[operations[i][0]]]=operations[i][1];
            ls[operations[i][0]]=0;
        }
        return nums;
    }
}
