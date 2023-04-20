package com.shuangpeng.Problem.p1101_1200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: Problem1187MakeArrayStrictlyIncreasing（使数组严格递增）
 * @Date 2022/7/13 11:17 AM
 * @Version 1.0
 */
public class Problem1187MakeArrayStrictlyIncreasing {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        Arrays.sort(arr2);
        int left = -1;
        for (int right = 0; right < m; right++) {
            if (left == -1 || arr2[left] != arr2[right]) {
                arr2[++left] = arr2[right];
            }
        }
        m = left + 1;
        int[] dp = new int[m + 1];
        Arrays.fill(dp, 1);
        dp[m] = 0;
        int M = Integer.MAX_VALUE >> 1;
        for (int i = 1; i < n; i++) {
            int temp = M;
            if (arr1[i] > arr1[i - 1]) {
                temp = Math.min(temp, dp[m]);
            }
            int idx = binarySearch(arr2, arr1[i], m);
            if (idx >= 0) {
                temp = Math.min(temp, dp[idx]);
            }
            int old = dp[m];
            dp[m] = temp;
            for (int j = m - 1; j >= 0; j--) {
                dp[j] = M;
                if (arr2[j] > arr1[i - 1]) {
                    dp[j] = Math.min(dp[j], old + 1);
                }
                if (j > 0) {
                    dp[j] = Math.min(dp[j], dp[j - 1] + 1);
                }
            }
        }
        int ans = M;
        for (int i = 0; i <= m; i++) {
            ans = Math.min(ans, dp[i]);
        }
        return ans == M ? -1 : ans;
    }

    private int binarySearch(int[] arr, int num, int m) {
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (num <= arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
}

class Problem1187MakeArrayStrictlyIncreasing0 {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        Arrays.sort(arr2);
        int idx = 0;
        for (int i = 1; i < m; i++) {
            if (arr2[idx] != arr2[i]) {
                arr2[++idx] = arr2[i];
            }
        }
        m = idx + 1;
        int[] dp = new int[Math.min(n, m) + 1];
        Arrays.fill(dp, -1);
        dp[0] = arr1[0];
        int maxChange = 0;
        if (arr2[0] < arr1[0]) {
            dp[1] = arr2[0];
            maxChange = 1;
        }
        for (int i = 1; i < n; i++) {
            int j = maxChange;
            if (dp[j] == -1) {
                return -1;
            }
            if (j < dp.length - 1) {
                int index = binarySearch(arr2, m, dp[j]);
                if (index < m && (arr2[index] < arr1[i] || arr1[i] <= dp[j])) {
                    dp[j + 1] = arr2[index];
                    maxChange++;
                }
            }
            while (j >= 0 && dp[j] != -1) {
                int t = -1;
                if (arr1[i] > dp[j]) {
                    t = arr1[i];
                }
                if (j > 0 && dp[j - 1] != -1) {
                    int index = binarySearch(arr2, m, dp[j - 1]);
                    if (index < m) {
                        t = t == -1 ? arr2[index] : Math.min(t, arr2[index]);
                    }
                }
                dp[j] = t;
                j--;
            }
        }
        for (int i = 0; i <= maxChange; i++) {
            if (dp[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(int[] arr, int m, int num) {
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (num >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

class Problem1187MakeArrayStrictlyIncreasing1 {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        Arrays.sort(arr2);
        int idx = 0;
        for (int i = 1; i < m; i++) {
            if (arr2[idx] != arr2[i]) {
                arr2[++idx] = arr2[i];
            }
        }
        m = idx + 1;
        int[] dp = new int[Math.min(n, m) + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = arr1[0];
        int maxChange = 0;
        if (m > 0 && arr2[0] < arr1[0]) {
            dp[1] = arr2[0];
            maxChange = 1;
        }
        for (int i = 1; i < n; i++) {
            if (dp[maxChange] == Integer.MAX_VALUE) {
                return -1;
            }
            for (int j = maxChange; j >= 0; j--) {
                if (dp[j] == Integer.MAX_VALUE) {
                    break;
                }
                if (j < dp.length - 1) {
                    int index = getNextIndex(arr2, m, dp[j]);
                    if (index < m && (arr2[index] < arr1[i] || arr1[i] <= dp[j] || j < maxChange)) {
                        dp[j + 1] = Math.min(dp[j + 1], arr2[index]);
                        maxChange = Math.max(maxChange, j + 1);
                    }
                }
                if (dp[j] < arr1[i]) {
                    dp[j] = arr1[i];
                } else {
                    dp[j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return -1;
    }

    private int getNextIndex(int[] arr, int m, int num) {
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (num >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

class Problem1187MakeArrayStrictlyIncreasing2 {

    static final int INF = Integer.MAX_VALUE >> 1;

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        int[][][] memo = new int[n1][n2][2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        Arrays.sort(arr2);
        int ans = dfs(arr1, arr2, n1 - 1, n2 - 1, 1, memo);
        return ans == INF ? -1 : ans;
    }

    private int dfs(int[] arr1, int[] arr2, int i, int j, int flag, int[][][] memo) {
        if (i == 0) {
            return flag == 1 ? 0 : (j >= 0 ? 1 : INF);
        }
        if (j < 0) {
            while (i > 0 && arr1[i] > arr1[i - 1]) {
                i--;
            }
            return i == 0 ? 0 : INF;
        }
        if (memo[i][j][flag] != -1) {
            return memo[i][j][flag];
        }
        int ans = INF;
        if (flag == 1) {
            int t = j;
            while (t >= 0 && arr2[t] >= arr1[i]) {
                t--;
            }
            ans = Math.min(ans, dfs(arr1, arr2, i - 1, t, arr1[i - 1] < arr1[i] ? 1 : 0, memo));
        }
        int num = arr2[j];
        while (j > 0 && arr2[j] == arr2[j - 1]) {
            j--;
        }
        if (arr1[i - 1] < num || j > 0) {
            ans = Math.min(ans, dfs(arr1, arr2,i - 1, j - 1, arr1[i - 1] < num ? 1 : 0, memo) + 1);
        }
        memo[i][j][flag] = ans;
        return ans;
    }
}

class Problem1187MakeArrayStrictlyIncreasing3 {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int n1 = arr1.length, n2 = arr2.length, INF = Integer.MAX_VALUE >> 1;
        int[][][] dp = new int[2][n2][2];
        for (int i = 0; i < n2; i++) {
            dp[0][i][1] = 1;
        }
        boolean valid = true;
        for (int i = 1; i < n1; i++) {
            int idx = i & 1, pre = idx ^ 1;
            for (int j = 0, k = 0; j < n2; j++) {
                while (arr2[k] < arr2[j]) {
                    k++;
                }
                dp[idx][j][0] = dp[idx][j][1] = INF;
                if (arr1[i - 1] < arr1[i]) {
                    dp[idx][j][0] = dp[pre][j][0];
                }
                int p = binarySearch(arr2, arr1[i], j);
                if (p >= 0) {
                    dp[idx][j][0] = Math.min(dp[idx][j][0], dp[pre][p][1]);
                }
                if (j == 0) {
                    dp[idx][j][1] = valid && arr1[i - 1] < arr2[j] ? 1 : INF;
                } else {
                    if (arr1[i - 1] < arr2[j]) {
                        dp[idx][j][1] = Math.min(dp[idx][j][1], 1 + dp[pre][j - 1][0]);
                    }
                    if (k > 0) {
                        dp[idx][j][1] = Math.min(dp[idx][j][1], 1 + dp[pre][k - 1][1]);
                    }
                }
            }
            valid &= arr1[i] > arr1[i - 1];
        }
        int index = (n1 - 1) & 1;
        int ans = Math.min(dp[index][n2 - 1][0], dp[index][n2 - 1][1]);
        return ans == INF ? -1 : ans;
    }

    private int binarySearch(int[] arr, int num, int i) {
        int left = 0, right = i;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }
}

class Problem1187MakeArrayStrictlyIncreasing4 {

    static int INF = Integer.MAX_VALUE >> 1;

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr2) {
            set.add(num);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : set) {
            Integer greater = set.higher(num);
            map.put(num, greater == null ? INF : greater);
        }
        for (int num : arr1) {
            Integer greater = set.higher(num);
            map.put(num, greater == null ? INF : greater);
        }
        map.put(-1, set.isEmpty() ? INF : set.higher(-1));
        int n = arr1.length, m = set.size();
        int[] dp = new int[m + 1];
        Arrays.fill(dp, INF);
        dp[0] = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, m); j >= 0; j--) {
                int count = INF;
                if (dp[j] < arr1[i - 1]) {
                    count = arr1[i - 1];
                }
                if (j > 0 && dp[j - 1] < INF) {
                    count = Math.min(count, map.get(dp[j - 1]));
                }
                dp[j] = count;
            }
        }
        for (int i = 0; i <= m; i++) {
            if (dp[i] != INF) {
                return i;
            }
        }
        return -1;
    }
}
