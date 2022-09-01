package com.shuangpeng.competition.第301到310场周赛.第302场周赛;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @Description: Problem2343QueryKthSmallestTrimmedNumber（裁剪数字后查询第K小的数字）
 * @Date 2022/7/23 12:02 PM
 * @Version 1.0
 */
public class Problem2343QueryKthSmallestTrimmedNumber {

    // 比赛时写法
    public int[] smallestTrimmedNumbers0(String[] nums, int[][] queries) {
        int n = nums.length, l = nums[0].length(), m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int k = q[0], t = q[1];
            String[][] str = new String[n][2];
            for (int j = 0; j < n; j++) {
                str[j][0] = nums[j].substring(l - t);
                str[j][1] = "" + j;
            }
            for (int j = 1; j < n; j++) {
                String[] temp = str[j];
                String s = str[j][0];
                int x = j - 1;
                boolean find = false;
                while (x >= 0 && !find) {
                    String s1 = str[x][0];
                    int y = 0;
                    while (y < t) {
                        char c1 = s1.charAt(y), c2 = s.charAt(y);
                        if (c1 < c2) {
                            find = true;
                            break;
                        } else if (c1 > c2) {
                            break;
                        }
                        y++;
                    }
                    if (y == t) {
                        find = true;
                    }
                    if (!find) {
                        str[x + 1] = str[x];
                    } else {
                        break;
                    }
                    x--;
                }
                str[x + 1] = temp;
            }
            ans[i] = Integer.parseInt(str[k - 1][1]);
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers1(String[] nums, int[][] queries) {
        int m = nums.length, n = queries.length, len = nums[0].length();
        int[] ans = new int[n];
        for (int q = 0; q  < n; q++) {
            int k = queries[q][0], t = queries[q][1];
            String[] str = new String[m];
            for (int i = 0; i < m; i++) {
                str[i] = nums[i].substring(len - t);
            }
            List<Integer> indices = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                indices.add(i);
            }
            for (int i = 0; i < t && indices.size() > 1; i++) {
                List<Integer>[] lists = new List[10];
                Arrays.setAll(lists, e -> new ArrayList<>());
                for (int j : indices) {
                    lists[str[j].charAt(i) - '0'].add(j);
                }
                int idx = 0;
                while (idx < 10) {
                    if (k > lists[idx].size()) {
                        k -= lists[idx].size();
                    } else {
                        break;
                    }
                    idx++;
                }
                indices = lists[idx];
            }
            ans[q] = indices.get(k - 1);
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers2(String[] nums, int[][] queries) {
        int n = queries.length, m = nums.length, len = nums[0].length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int t = queries[i][1];
            Integer[] ids = new Integer[m];
            for (int j = 0; j < m; j++) {
                ids[j] = j;
            }
            Arrays.sort(ids, Comparator.comparing(a -> nums[a].substring(len - t)));
            ans[i] = ids[queries[i][0] - 1];
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers3(String[] nums, int[][] queries) {
        int n = nums.length, l = nums[0].length(), m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int k = q[0], t = q[1];
            String[][] str = new String[n][2];
            for (int j = 0; j < n; j++) {
                str[j][0] = nums[j].substring(l - t);
                str[j][1] = "" + j;
            }
            Arrays.sort(str, (a, b) -> {
                int x = 0;
                String s1 = a[0], s2 = b[0];
                while (x < t) {
                    char c1 = s1.charAt(x), c2 = s2.charAt(x);
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    x++;
                }
                return 0;
            });
            ans[i] = Integer.parseInt(str[k - 1][1]);
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers4(String[] nums, int[][] queries) {
        int n = queries.length, m = nums.length, len = nums[0].length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int t = queries[i][1];
            List<Integer> ids = new ArrayList<>(Arrays.asList(IntStream.range(0, m).boxed().toArray(Integer[]::new)));
            Collections.sort(ids, Comparator.comparing(a -> nums[a].substring(len - t)));
            ans[i] = ids.get(queries[i][0] - 1);
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers5(String[] nums, int[][] queries) {
        int n = queries.length, m = nums.length, len = nums[0].length();
        Integer[] qIds = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(qIds, Comparator.comparingInt(a -> queries[a][1]));
        Integer[] ids = IntStream.range(0, m).boxed().toArray(Integer[]::new);
        int[] ans = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int index = qIds[i];
            int t = queries[index][1];
            for (int j = len - prev - 1; j >= len - t; j--) {
                int p = j;
                Arrays.sort(ids, Comparator.comparing(a -> nums[a].charAt(p)));
            }
            prev = t;
            ans[index] = ids[queries[index][0] - 1];
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = queries.length, m = nums.length, len = nums[0].length();
        Integer[] qIds = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(qIds, Comparator.comparingInt(a -> queries[a][1]));
        Integer[] ids = IntStream.range(0, m).boxed().toArray(Integer[]::new);
        int[] ans = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int idx = qIds[i], t = queries[idx][1], k = queries[idx][0];
            for (int j = len - prev - 1; j >= len - t; j--) {
                ids = radixSort(nums, ids, j);
            }
            prev = t;
            ans[idx] = ids[k - 1];
        }
        return ans;
    }

    private Integer[] radixSort(String[] nums, Integer[] ids, int p) {
        int N = 10;
        List<Integer>[] lists = new List[N];
        Arrays.setAll(lists, e -> new ArrayList<>());
        for (int i : ids) {
            lists[nums[i].charAt(p) - '0'].add(i);
        }
        int n = ids.length;
        Integer[] ans = new Integer[n];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j : lists[i]) {
                ans[idx++] = j;
            }
        }
        return ans;
    }
}
