package com.shuangpeng.Problem.p1401_1500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1487MakingFileNamesUnique（保证文件名唯一）
 * @date 2023/3/3 10:09 AM
 */
public class Problem1487MakingFileNamesUnique {

    public String[] getFolderNames0(String[] names) {
        int n = names.length;
        String[] ans = new String[n];
        Map<String, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            String name = names[i];
            int count = map.getOrDefault(name, 0);
            if (count == 0) {
                ans[i] = name;
            } else {
                while (map.containsKey(name + '(' + count + ')')) {
                    count++;
                }
                ans[i] = name + '(' + count + ')';
                map.put(name, count + 1);
            }
            map.put(ans[i], 1);
        }
        return ans;
    }

    public String[] getFolderNames(String[] names) {
        int n = names.length;
        Map<String, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            if (map.containsKey(names[i])) {
                int num = map.get(names[i]);
                while (map.containsKey(names[i] + "(" + num + ")")) {
                    num++;
                }
                map.put(names[i], num + 1);
                names[i] += "(" + num + ")";
            }
            map.put(names[i], 1);
        }
        return names;
    }
}
