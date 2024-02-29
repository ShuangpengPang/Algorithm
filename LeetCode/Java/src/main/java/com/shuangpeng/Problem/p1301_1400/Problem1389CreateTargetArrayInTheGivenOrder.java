package com.shuangpeng.Problem.p1301_1400;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1389CreateTargetArrayInTheGivenOrder（按既定顺序创建目标数组）
 * @date 2024/2/29 11:54 PM
 */
public class Problem1389CreateTargetArrayInTheGivenOrder {

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            list.add(index[i], nums[i]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
