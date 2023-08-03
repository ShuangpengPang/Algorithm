package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0722RemoveComments（删除注释）
 * @date 2023/8/3 10:15 AM
 */
public class Problem0722RemoveComments {

    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean isBlock = false;
        for (String w : source) {
            int m = w.length();
            for (int i = 0; i < m; i++) {
                char c1 = w.charAt(i), c2 = i + 1 < m ? w.charAt(i + 1) : ' ';
                if (!isBlock) {
                    if (c1 == '/' && c2 == '/') {
                        break;
                    } else if (c1 == '/' && c2 == '*') {
                        isBlock = true;
                        i++;
                    } else {
                        sb.append(c1);
                    }
                } else if (c1 == '*' && c2 == '/') {
                    isBlock = false;
                    i++;
                }
            }
            if (!isBlock && sb.length() > 0) {
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        return ans;
    }
}
