package com.shuangpeng.Problem.p2801_2900;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2856MinimumArrayLengthAfterPairRemovals（删除数对后的最小数组长度）
 * @date 2023/12/20 10:03 PM
 */
public class Problem2856MinimumArrayLengthAfterPairRemovals {

    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size(), h = n >> 1, maxLength = 1, length = 1;
        for (int i = 1; i < n; i++) {
            if (nums.get(i).equals(nums.get(i - 1))) {
                maxLength = Math.max(maxLength, ++length);
            } else {
                length = 1;
            }
        }
        return maxLength > h ? (maxLength << 1) - n : n & 1;
    }
}
