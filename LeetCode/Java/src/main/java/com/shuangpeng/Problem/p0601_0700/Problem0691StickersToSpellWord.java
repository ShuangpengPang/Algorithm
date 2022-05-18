package com.shuangpeng.Problem.p0601_0700;

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

    public int minStickers1(String[] stickers, String target) {
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

    public int minStickers2(String[] stickers, String target) {
        int n = stickers.length;
        List<int[]>[] lists = new List[n];
        for (int i = 0; i < n; ++i) {
            lists[i] = getCountList(stickers[i]);
        }
        List<int[]> targetList = getCountList(target);
        int ans = dfs(lists, encode(targetList), new HashMap<>());
        return ans == Integer.MAX_VALUE >> 1 ? -1 : ans;
    }

    private int dfs(List<int[]>[] lists, String s, Map<String, Integer> memo) {
        if (s.isEmpty()) {
            return 0;
        }
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        List<int[]> targetList = decode(s);
        int n = targetList.size();
        List<int[]> copyList = new ArrayList<>(n);
        int ans = Integer.MAX_VALUE >> 1;
        for (List<int[]> list : lists) {
            copyList.clear();
            for (int i = 0; i < n; ++i) {
                int[] pair = targetList.get(i);
                copyList.add(new int[]{pair[0], pair[1]});
            }
            boolean find = false;
            for (int[] pair1 : copyList) {
                for (int[] pair2 : list) {
                    if (pair1[0] == pair2[0]) {
                        pair1[1] -= Math.min(pair1[1], pair2[1]);
                        find = true;
                        break;
                    } else if (pair1[0] < pair2[0]) {
                        break;
                    }
                }
            }
            if (find) {
                ans = Math.min(ans, 1 + dfs(lists, encode(copyList), memo));
            }
        }
        memo.put(s, ans);
        return ans;
    }

    private List<int[]> getCountList(String s) {
        List<int[]> list = new ArrayList<>();
        final int N = 26;
        int[] array = new int[N];
        int m = s.length();
        for (int j = 0; j < m; ++j) {
            ++array[s.charAt(j) - 'a'];
        }
        for (int j = 0; j < N; ++j) {
            if (array[j] > 0) {
                list.add(new int[]{j, array[j]});
            }
        }
        return list;
    }

    private String encode(List<int[]> list) {
        StringBuilder sb = new StringBuilder();
        int n = list.size();
        boolean isFirst = true;
        for (int i = 0; i < n; ++i) {
            int[] pair = list.get(i);
            if (pair[1] == 0) {
                continue;
            }
            if (!isFirst) {
                sb.append('_');
            } else {
                isFirst = false;
            }
            sb.append(pair[0]).append(':').append(pair[1]);
        }
        return sb.toString();
    }

    private List<int[]> decode(String s) {
        String[] strs = s.split("_");
        int n = strs.length;
        List<int[]> list = new ArrayList<>(n);
        for (String str : strs) {
            String[] ss = str.split(":");
            list.add(new int[]{Integer.parseInt(ss[0]), Integer.parseInt(ss[1])});
        }
        return list;
    }

    public int minStickers3(String[] stickers, String target) {
        int n = target.length();
        int m = 1 << n;
        int[] memo = new int[m];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        int ans = dp(stickers, target.toCharArray(), m - 1, memo);
        return ans == n + 1 ? -1 : ans;
    }

    private int dp(String[] stickers, char[] target, int mask, int[] memo) {
        int m = target.length;
        if (memo[mask] == -1) {
            int ans = m + 1;
            for (String s : stickers) {
                int left = mask;
                int[] cnt = new int[26];
                int n = s.length();
                for (int i = 0; i < n; ++i) {
                    ++cnt[s.charAt(i) - 'a'];
                }
                for (int i = 0; i < m; ++i) {
                    if ((mask >> i & 1) == 1 && cnt[target[i] - 'a'] > 0) {
                        left ^= 1 << i;
                        --cnt[target[i] - 'a'];
                    }
                }
                if (left < mask) {
                    ans = Math.min(ans, dp(stickers, target, left, memo) + 1);
                }
            }
            memo[mask] = ans;
        }
        return memo[mask];
    }

    public int minStickers4(String[] stickers, String target) {
        int N = 26;
        int m = stickers.length, n = target.length();
        int[][] counts = new int[m][N];
        for (int i = 0; i < m; ++i) {
            int len = stickers[i].length();
            for (int j = 0; j < len; ++j) {
                ++counts[i][stickers[i].charAt(j) - 'a'];
            }
        }
        List<Integer>[] map = new List[N];
        for (int i = 0; i < N; ++i) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < N; ++j) {
                if (counts[i][j] > 0) {
                    map[j].add(i);
                }
            }
        }
        int M = 1 << n;
        int INF = (int) 1e9;
        int[] memo = new int[M];
        Arrays.fill(memo, INF);
        memo[0] = 0;
        char[] chars = target.toCharArray();
        for (int i = 0; i < M; ++i) {
            if (memo[i] != INF) {
                int idx = -1;
                for (int j = 0; j < n; ++j) {
                    if ((i & (1 << j)) == 0) {
                        idx = j;
                        break;
                    }
                }
                if (idx == -1) {
                    break;
                }
                for (int j : map[chars[idx] - 'a']) {
                    int[] cnt = counts[j].clone();
                    int mask = i;
                    for (int k = 0; k < n; ++k) {
                        if ((i & (1 << k)) == 0 && cnt[chars[k] - 'a'] > 0) {
                            --cnt[chars[k] - 'a'];
                            mask |= 1 << k;
                        }
                    }
                    memo[mask] = Math.min(memo[mask], memo[i] + 1);
                }
            }
        }
        return memo[M - 1] == INF ? -1 : memo[M - 1];
    }

    public int minStickers(String[] stickers, String target) {
        int N = 26;
        int[] counts = new int[N];
        for (char c : target.toCharArray()) {
            ++counts[c - 'a'];
        }
        int n = 0;
        int[] index = new int[N];
        for (int i = 0; i < N; ++i) {
            index[i] = counts[i] > 0 ? n++ : -1;
        }
        int[] targetCounts = new int[n];
        int t = 0;
        for (int i : counts) {
            if (i > 0) {
                targetCounts[t++] = i;
            }
        }
        int m = stickers.length;
        int[][] stickerCount = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (char c : stickers[i].toCharArray()) {
                int j = index[c - 'a'];
                if (j >= 0) {
                    ++stickerCount[i][j];
                }
            }
        }
        int start = removeDominatedSticker(stickerCount);
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(targetCounts);
        visited.add(toString(targetCounts));
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                int[] cnt = queue.poll();
                for (int j = start; j < m; ++j) {
                    int[] sticker = stickerCount[j];
                    int[] copy = cnt.clone();
                    for (int k = 0, c = 0; k < n; ++k) {
                        copy[k] = Math.max(0, copy[k] - sticker[k]);
                        if (copy[k] == 0) {
                            ++c;
                            if (c == n) {
                                return step;
                            }
                        }
                    }
                    if (visited.add(toString(copy))) {
                        queue.offer(copy);
                    }
                }
            }
        }
        return -1;
    }

    private int removeDominatedSticker(int[][] stickerCount) {
        int m = stickerCount.length, n = stickerCount[0].length;
        int start = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = start; j < m; ++j) {
                if (i != j) {
                    int k = 0;
                    while (k < n && stickerCount[i][k] <= stickerCount[j][k]) {
                        ++k;
                    }
                    if (k == n) {
                        int[] temp = stickerCount[i];
                        stickerCount[i] = stickerCount[start];
                        stickerCount[start++] = temp;
                        break;
                    }
                }
            }
        }
        return start;
    }

    private String toString(int[] cnt) {
        StringBuilder sb = new StringBuilder();
        for (int c : cnt) {
            sb.append((char) c);
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        TreeSet<Integer> set = new TreeSet<Integer>();
//        TreeSet<Integer> subSet = new TreeSet<Integer>();
//        for(int i=606;i<613;i++){
//            if(i%2==0){
//                set.add(i);
//            }
//        }
//        subSet = (TreeSet)set.subSet(608,true,611,true);
//        set.add(609);
//        System.out.println(set+" "+subSet);
//    }
}
