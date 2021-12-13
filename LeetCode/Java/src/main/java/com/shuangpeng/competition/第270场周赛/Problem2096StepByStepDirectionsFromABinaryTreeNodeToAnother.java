package com.shuangpeng.competition.第270场周赛;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem2096StepByStepDirectionsFromABinaryTreeNodeToAnother {

    // 比赛时写法
    public String getDirections0(TreeNode root, int startValue, int destValue) {
        List<Integer> ans1 = new ArrayList<>();
        List<Character> ansDirs1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        List<Character> ansDirs2 = new ArrayList<>();
        dfs(root, startValue, new ArrayList<>(), ans1, 'L', new ArrayList<>(), ansDirs1);
        dfs(root, destValue, new ArrayList<>(), ans2, 'L', new ArrayList<>(), ansDirs2);
        int i = 0;
        while (i + 1 < ans1.size() && i + 1 < ans2.size() && ans1.get(i + 1).equals(ans2.get(i + 1))) {
            ++i;
        }
        StringBuilder sb = new StringBuilder();
        if (i == ans1.size() - 1) {
            for (int j = i + 1; j < ansDirs2.size(); ++j) {
                sb.append(ansDirs2.get(j));
            }
        } else if (i == ans2.size() - 1) {
            for (int j = i + 1; j < ansDirs1.size(); ++j) {
                sb.append('U');
            }
        } else {
            for (int j = 0; j < ans1.size() - i - 1; ++j) {
                sb.append('U');
            }
            for (int j = i + 1; j < ansDirs2.size(); ++j) {
                sb.append(ansDirs2.get(j));
            }
        }
        return sb.toString();
    }

    private void dfs(TreeNode root, int num, List<Integer> list, List<Integer> ans, char dir, List<Character> dirs, List<Character> ansDirs) {
        if (root == null) {
            return;
        }
        if (root.val == num) {
            list.add(num);
            int n = list.size();
            for (int i = 0; i < n; ++i) {
                ans.add(list.get(i));
            }
            dirs.add(dir);
            for (int i = 0; i < n; ++i) {
                ansDirs.add(dirs.get(i));
            }
            return;
        }
        list.add(root.val);
        dirs.add(dir);
        dfs(root.left, num, list, ans, 'L', dirs, ansDirs);
        if (ans.size() == 0) {
            dfs(root.right, num, list, ans, 'R', dirs, ansDirs);
        }
        dirs.remove(dirs.size() - 1);
        list.remove(list.size() - 1);
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder path1 = new StringBuilder();
        StringBuilder path2 = new StringBuilder();
        dfs(root, startValue, destValue, 'L', new StringBuilder(), new StringBuilder(), path1, path2);
        int n1 = path1.length(), n2 = path2.length();
        int p = 0;
        while (p + 1 < n1 && p + 1 < n2 && path1.charAt(p + 1) == path2.charAt(p + 1)) {
            ++p;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n1 - p - 1; ++i) {
            ans.append('U');
        }
        for (int i = p + 1; i < n2; ++i) {
            ans.append(path2.charAt(i));
        }
        return ans.toString();
    }

    private void dfs(TreeNode root, int startValue, int destValue, char direction,
                     StringBuilder sb1, StringBuilder sb2, StringBuilder path1, StringBuilder path2) {
        if (root == null) {
            return;
        }
        if (path1.length() > 0 && path2.length() > 0) {
            return;
        }
        sb1.append(direction);
        sb2.append(direction);
        if (root.val == startValue) {
            int n = sb1.length();
            for (int i = 0; i < n; ++i) {
                path1.append(sb1.charAt(i));
            }
        }
        if (root.val == destValue) {
            int n = sb2.length();
            for (int i = 0; i < n; ++i) {
                path2.append(sb2.charAt(i));
            }
        }
        dfs(root.left, startValue, destValue, 'L', sb1, sb2, path1, path2);
        dfs(root.right, startValue, destValue, 'R', sb1, sb2, path1, path2);
        sb2.deleteCharAt(sb2.length() - 1);
        sb1.deleteCharAt(sb1.length() - 1);
    }
}
