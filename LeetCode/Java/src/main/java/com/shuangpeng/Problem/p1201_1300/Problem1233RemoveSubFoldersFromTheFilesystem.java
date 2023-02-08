package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1233RemoveSubFoldersFromTheFilesystem（删除子文件夹）
 * @date 2023/2/8 4:58 PM
 */
public class Problem1233RemoveSubFoldersFromTheFilesystem {

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparingInt(a -> a.length()));
        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (String f : folder) {
            StringBuilder sb = new StringBuilder("/");
            boolean isSubFolder = false;
            int n = f.length(), i = 1;
            while (i < n) {
                while (i < n && f.charAt(i) != '/') {
                    sb.append(f.charAt(i));
                    i++;
                }
                if (set.contains(sb.toString())) {
                    isSubFolder = true;
                    break;
                }
                if (i < n) {
                    sb.append('/');
                }
                i++;
            }
            if (!isSubFolder) {
                String s = sb.toString();
                set.add(s);
                ans.add(s);
            }
        }
        return ans;
    }
}
