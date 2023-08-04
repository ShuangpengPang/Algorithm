package com.shuangpeng.Problem.p2701_2800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2788SplitStringsBySeparator（按分隔符拆分字符串）
 * @date 2023/8/4 5:44 PM
 */
public class Problem2788SplitStringsBySeparator {

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            String[] strs = w.split("\\" + separator);
            for (String s : strs) {
                if (!s.isEmpty()) {
                    ans.add(s);
                }
            }
        }
        return ans;
    }
}
