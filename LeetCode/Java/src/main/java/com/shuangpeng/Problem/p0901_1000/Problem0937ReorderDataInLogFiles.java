package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @Description: Problem0937ReorderDataInLogFiles
 * @Date 2022/5/5 7:26 PM
 * @Version 1.0
 */
public class Problem0937ReorderDataInLogFiles {

    public String[] reorderLogFiles0(String[] logs) {
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

    public String[] reorderLogFiles(String[] logs) {
        int length = logs.length;
        Pair[] arr = new Pair[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Pair(logs[i], i);
        }
        Arrays.sort(arr, (a, b) -> logCompare(a, b));
        String[] reordered = new String[length];
        for (int i = 0; i < length; i++) {
            reordered[i] = arr[i].log;
        }
        return reordered;
    }

    public int logCompare(Pair pair1, Pair pair2) {
        String log1 = pair1.log, log2 = pair2.log;
        int index1 = pair1.index, index2 = pair2.index;
        String[] split1 = log1.split(" ", 2);
        String[] split2 = log2.split(" ", 2);
        boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
        boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
        if (isDigit1 && isDigit2) {
            return index1 - index2;
        }
        if (!isDigit1 && !isDigit2) {
            int sc = split1[1].compareTo(split2[1]);
            if (sc != 0) {
                return sc;
            }
            return split1[0].compareTo(split2[0]);
        }
        return isDigit1 ? 1 : -1;
    }

    class Pair {
        String log;
        int index;

        public Pair(String log, int index) {
            this.log = log;
            this.index = index;
        }
    }
}
