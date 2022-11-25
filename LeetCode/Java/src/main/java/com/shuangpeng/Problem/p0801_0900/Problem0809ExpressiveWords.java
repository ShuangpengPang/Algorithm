package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0809ExpressiveWords（情感丰富的文字）
 * @Date 2022/10/31 3:05 PM
 * @Version 1.0
 */
public class Problem0809ExpressiveWords {

    public int expressiveWords(String s, String[] words) {
        int n = s.length();
        List<Integer> list = new ArrayList<>(), count = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            if (i == 0 || j != s.charAt(i - 1) - 'a') {
                list.add(j);
                count.add(1);
            } else {
                count.set(count.size() - 1, count.get(count.size() - 1) + 1);
            }
        }
        int ans = 0;
        for (String w : words) {
            int m = w.length();
            if (m > n || w.charAt(0) != s.charAt(0)) {
                continue;
            }
            int idx = 0, cnt = 1;
            boolean valid = true;
            for (int i = 1; i < m && valid; i++) {
                int j = w.charAt(i - 1) - 'a', k = w.charAt(i) - 'a';
                if (j != k) {
                    if (idx >= count.size() - 1 || cnt > count.get(idx)
                            || cnt < count.get(idx) && count.get(idx) < 3 || list.get(idx + 1) != k) {
                        valid = false;
                        break;
                    }
                    idx++;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            int last = count.get(count.size() - 1);
            if (valid && idx == count.size() - 1 && (cnt == last || cnt < last && last >= 3)) {
                ans++;
            }
        }
        return ans;
    }
}
