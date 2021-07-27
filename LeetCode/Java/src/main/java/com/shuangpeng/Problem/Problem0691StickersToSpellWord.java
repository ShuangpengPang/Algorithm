package com.shuangpeng.Problem;

import java.util.*;

public class Problem0691StickersToSpellWord {

    public int minStickers0(String[] stickers, String target) {
        List<int[]> stickerList = new ArrayList<>(stickers.length);
        final int N = 26;
        for (String sticker : stickers) {
            int[] counts = new int[N];
            for (char c : sticker.toCharArray()) {
                counts[c - 'a']++;
            }
            stickerList.add(counts);
        }
        int[] targetCounts = new int[N];
        for (char c : target.toCharArray()) {
            targetCounts[c - 'a']++;
        }
        int minValue = dfs(stickerList, targetCounts, new HashMap<>(), N);
        return minValue >= (Integer.MAX_VALUE >> 1) ? -1 : minValue;
    }

    private int dfs(List<int[]> stickerList, int[] targetCounts, Map<int[], Integer> memo, int N) {
        if (memo.containsKey(targetCounts)) {
            return memo.get(targetCounts);
        }
        for (int[] sticker : stickerList) {
            if (check(sticker, targetCounts, N)) {
                memo.put(targetCounts, 1);
                return 1;
            }
        }
        int minValue = Integer.MAX_VALUE >> 1;
        for (int[] sticker : stickerList) {
            int[] copyCounts = Arrays.copyOf(targetCounts, targetCounts.length);
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                int count = Math.min(copyCounts[i], sticker[i]);
                if (count > 0) {
                    copyCounts[i] -= Math.min(copyCounts[i], sticker[i]);
                    flag = true;
                }
            }
            if (flag) {
                minValue = Math.min(minValue, dfs(stickerList, copyCounts, memo, N) + 1);
                if (minValue == 2) {
                    break;
                }
            }
        }
        memo.put(targetCounts, minValue);
        return minValue;
    }

    private boolean check(int[] counts, int[] targetCounts, int N) {
        for (int i = 0; i < N; i++) {
            if (targetCounts[i] > counts[i]) {
                return false;
            }
        }
        return true;
    }

    public int minStickers(String[] stickers, String target) {
        int m = target.length();
        final int MAX = 1 << m;
        int[] dp = new int[MAX];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (String sticker : stickers) {
            for (int status = 0; status < MAX; status++) {
                if (dp[status] == -1) {
                    continue;
                }
                int s = status;
                for (char c : sticker.toCharArray()) {
                    for (int i = 0; i < m; i++) {
                        if (target.charAt(i) == c && ((s & (1 << i)) == 0)) {
                            s |= 1 << i;
                            break;
                        }
                    }
                }
                dp[s] = dp[s] == -1 ? dp[status] + 1 : Math.min(dp[s], dp[status] + 1);
            }
        }
        return dp[MAX - 1];
    }
}
