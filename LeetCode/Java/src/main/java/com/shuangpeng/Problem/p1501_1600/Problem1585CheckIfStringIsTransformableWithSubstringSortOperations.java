package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1585CheckIfStringIsTransformableWithSubstringSortOperations（检查字符串是否可以通过排序子字符串得到另外一个字符串）
 * @Date 2022/9/1 3:54 PM
 * @Version 1.0
 */
public class Problem1585CheckIfStringIsTransformableWithSubstringSortOperations {

    public boolean isTransformable(String s, String t) {
        int n = s.length();
        List<Integer>[] lists = new List[10];
        Arrays.setAll(lists, e -> new ArrayList<>());
        for (int i = n - 1; i >= 0; i--) {
            lists[s.charAt(i) - '0'].add(i);
        }
        for (int i = 0; i < n; i++) {
            int d = t.charAt(i) - '0';
            if (lists[d].isEmpty()) {
                return false;
            }
            int idx = lists[d].get(lists[d].size() - 1);
            for (int j = 0; j < d; j++) {
                if (!lists[j].isEmpty() && lists[j].get(lists[j].size() - 1) < idx) {
                    return false;
                }
            }
            lists[d].remove(lists[d].size() - 1);
        }
        return true;
    }
}
