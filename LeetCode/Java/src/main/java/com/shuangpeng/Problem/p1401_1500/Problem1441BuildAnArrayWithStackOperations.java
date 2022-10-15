package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1441BuildAnArrayWithStackOperations（用栈操作构建数组）
 * @Date 2022/10/15 5:38 PM
 * @Version 1.0
 */
public class Problem1441BuildAnArrayWithStackOperations {

    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int prev = 0;
        for (int num : target) {
            int cnt = num - prev - 1;
            for (int i = 0; i < cnt; i++) {
                ans.add("Push");
            }
            for (int i = 0; i < cnt; i++) {
                ans.add("Pop");
            }
            ans.add("Push");
            prev = num;
        }
        return ans;
    }
}
