package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * @Description: Problem0652FindDuplicateSubtrees（寻找重复的子树）
 * @Date 2022/9/5 10:07 AM
 * @Version 1.0
 */
public class Problem0652FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, new HashMap<>(), ans);
        return ans;
    }

    private String dfs(TreeNode root, Map<String, Integer> map, List<TreeNode> ans) {
        if (root == null) {
            return "";
        }
        String s = root.val + "(" + dfs(root.left, map, ans) + ")(" + dfs(root.right, map, ans) + ")";
        int cnt = map.getOrDefault(s, 0) + 1;
        map.put(s, cnt);
        if (cnt == 2) {
            ans.add(root);
        }
        return s;
    }
}

class Problem0652FindDuplicateSubtrees0 {

    Map<String, Pair<TreeNode, Integer>> map;
    Set<TreeNode> set;
    int idx;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        set = new HashSet<>();
        idx = 0;
        dfs(root);
        return new ArrayList<>(set);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        String hash = Arrays.toString(new int[]{root.val, dfs(root.left), dfs(root.right)});
        if (map.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = map.get(hash);
            set.add(pair.getKey());
            return pair.getValue();
        }
        map.put(hash, new Pair<>(root, ++idx));
        return idx;
    }
}

