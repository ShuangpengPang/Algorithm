package com.shuangpeng.Problem.p0601_0700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0609FindDuplicateFileInSystem（在系统中查找重复文件）
 * @date 2023/2/25 5:05 PM
 */
public class Problem0609FindDuplicateFileInSystem {

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] files = path.split(" ");
            int n = files.length;
            String directory = files[0];
            for (int i = 1; i < n; i++) {
                String file = files[i];
                String name = file.substring(0, file.indexOf('('));
                String content = file.substring(name.length() + 1, file.length() - 1);
                map.computeIfAbsent(content, k -> new ArrayList<>()).add(directory + "/" + name);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                ans.add(list);
            }
        }
        return ans;
    }
}
