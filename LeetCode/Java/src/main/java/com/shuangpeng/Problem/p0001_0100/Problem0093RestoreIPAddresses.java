package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

public class Problem0093RestoreIPAddresses {

    public List<String> restoreIpAddresses0(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, new StringBuilder(), 0, 0, ans);
        return ans;
    }

    private void dfs(String s, StringBuilder result, int index, int order, List<String> ans) {
        int n = s.length();
        if (order == 4) {
            if (index == n) {
                ans.add(result.toString());
            }
            return;
        }
        if (n - index > (4 - order) * 3 || n - index < 4 - order) {
            return;
        }
        if (index > 0) {
            result.append('.');
        }
        if (s.charAt(index) == '0') {
            result.append('0');
            dfs(s, result, index + 1, order + 1, ans);
            result.deleteCharAt(result.length() - 1);
        } else {
            int num = 0;
            for (int i = index; i < n; ++i) {
                num = num * 10 + s.charAt(i) - '0';
                if (num > 255) {
                    break;
                }
                int idx = result.length();
                result.append(num);
                dfs(s, result, i + 1, order + 1, ans);
                result.delete(idx, idx + i - index + 1);
            }
        }
        if (index > 0) {
            result.deleteCharAt(result.length() - 1);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, new int[4], ans);
        return ans;
    }

    private void dfs(String s, int start, int order, int[] nums, List<String> ans) {
        final int segmentCount = nums.length;
        int n = s.length();
        if (order == segmentCount) {
            if (start == n) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < segmentCount; ++i) {
                    sb.append(nums[i]);
                    if (i != segmentCount - 1) {
                        sb.append('.');
                    }
                }
                ans.add(sb.toString());
            }
            return;
        }
        if (start >= n) {
            return;
        }
        if (s.charAt(start) == '0') {
            nums[order] = 0;
            dfs(s, start + 1, order + 1, nums, ans);
            return;
        }
        int num = 0;
        for (int i = start; i < n; ++i) {
            num = num * 10 + s.charAt(i) - '0';
            if (num > 255) {
                break;
            }
            nums[order] = num;
            dfs(s, i + 1, order + 1, nums, ans);
        }
    }
}
