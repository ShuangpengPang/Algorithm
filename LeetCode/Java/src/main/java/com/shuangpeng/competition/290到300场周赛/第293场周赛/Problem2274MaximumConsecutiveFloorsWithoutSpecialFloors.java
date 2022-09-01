package com.shuangpeng.competition.第293场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2274MaximumConsecutiveFloorsWithoutSpecialFloors（不含特殊楼层的最大连续楼层数）
 * @Date 2022/6/25 2:31 PM
 * @Version 1.0
 */
public class Problem2274MaximumConsecutiveFloorsWithoutSpecialFloors {

    // 比赛时写法
    public int maxConsecutive0(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int ans = 0;
        int n = special.length;
        for (int start = bottom, i = 0; i < n && special[i] <= top; ++i) {
            if (special[i] < start) {
                continue;
            }
            ans = Math.max(ans, special[i] - start);
            start = special[i] + 1;
        }
        if (special[n - 1] < top) {
            ans = Math.max(ans, top - special[n - 1]);
        }
        return ans;
    }

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int ans = 0;
        int prev = bottom - 1;
        for (int s : special) {
            ans = Math.max(ans, s - prev - 1);
            prev = s;
        }
        ans = Math.max(ans, top - special[special.length - 1]);
        return ans;
    }
}
