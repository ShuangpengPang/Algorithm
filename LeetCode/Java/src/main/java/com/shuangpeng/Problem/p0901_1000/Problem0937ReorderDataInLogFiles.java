package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @Description: Problem0937ReorderDataInLogFiles
 * @Date 2022/5/5 7:26 PM
 * @Version 1.0
 */
public class Problem0937ReorderDataInLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        int idx = n - 1;
        for (int i = n - 1; i >= 0; --i) {
            String log = logs[i];
            char c = log.charAt(log.indexOf(" ") + 1);
            if (c >= '0' && c <= '9') {
                swap(logs, i, idx);
                --idx;
            }
        }
        Arrays.sort(logs, 0, idx + 1, (a, b) -> {
            int idx1 = a.indexOf(" "), idx2 = b.indexOf(" ");
            int ans = a.substring(idx1 + 1).compareTo(b.substring(idx2 + 1));
            if (ans != 0) {
                return ans;
            }
            return a.substring(0, idx1).compareTo(b.substring(0, idx2));
        });
        return logs;
    }

    private void swap(String[] logs, int i, int j) {
        if (i != j) {
            String log = logs[i];
            logs[i] = logs[j];
            logs[j] = log;
        }
    }
}
