package com.shuangpeng.Problem.p0901_1000;

import java.util.*;

/**
 * @Description: Problem0952LargestComponentSizeByCommonFactor（按公因数计算最大组件大小）
 * @Date 2022/4/26 11:28 AM
 * @Version 1.0
 */
public class Problem0952LargestComponentSizeByCommonFactor {

    public int largestComponentSize(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            int temp = num;
            int j = 2;
            while (temp >= j * j) {
                if (temp % j == 0) {
                    map.putIfAbsent(j, new ArrayList<>());
                    map.get(j).add(i);
                    while (temp % j == 0) {
                        temp /= j;
                    }
                }
                ++j;
            }
            if (temp > 1) {
                map.putIfAbsent(temp, new ArrayList<>());
                map.get(temp).add(i);
            }
        }
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
        int ans = 1;
        for (int factor : map.keySet()) {
            List<Integer> nodes = map.get(factor);
            int m = nodes.size();
            int first = nodes.get(0);
            ans = Math.max(ans, size[find(parent, first)]);
            for (int i = 1; i < m; ++i) {
                int count = union(parent, size, first, nodes.get(i));
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    private int union(int[] parent, int[] size, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            if (size[px] > size[py]) {
                parent[py] = px;
                size[px] += size[py];
            } else {
                parent[px] = py;
                size[py] += size[px];
            }
        }
        return size[find(parent, px)];
    }

    private int find(int[] parent, int x) {
        return parent[x] = (x == parent[x] ? x : find(parent, parent[x]));
    }
}

class Problem0952LargestComponentSizeByCommonFactor0 {

    public int largestComponentSize(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] parent = new int[max + 1];
        int[] size = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            parent[i] = i;
        }
        int ans = 1;
        for (int num : nums) {
            List<Integer> list = getPrime(num);
            int m = list.size();
            if (m == 0) {
                continue;
            }
            for (int i = 0; i < m - 1; i++) {
                union(parent, size, list.get(i), list.get(i + 1));
            }
            int p = find(parent, list.get(0));
            size[p]++;
            ans = Math.max(ans, size[p]);
        }
        return ans;
    }

    private void union(int[] parent, int[] size, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[px] = py;
            size[py] += size[px];
        }
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private List<Integer> getPrime(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                }
                list.add(i);
            }
        }
        if (num > 1) {
            list.add(num);
        }
        return list;
    }
}

class Problem0952LargestComponentSizeByCommonFactor1 {

    public int largestComponentSize(int[] nums) {
        int m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }
        int[] parent = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            parent[i] = i;
        }
        for (int num : nums) {
            int x = num;
            for (int i = 2; i * i <= x; i++) {
                if (x % i == 0) {
                    union(parent, num, i);
                    while (x % i == 0) {
                        x /= i;
                    }
                }
            }
            if (x > 1) {
                union(parent, num, x);
            }
        }
        int ans = 0;
        int[] size = new int[m + 1];
        for (int num : nums) {
            int p = find(parent, num);
            size[p]++;
            ans = Math.max(ans, size[p]);
        }
        return ans;
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[px] = py;
        }
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }
}

