package com.shuangpeng.competition.第261到270场周赛.第262场周赛;

import java.util.*;

public class Problem2033 {

    public int minOperations0(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        int pivot = grid[0][0];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int diff = grid[i][j] - pivot;
                if (diff % x != 0) {
                    return -1;
                }
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Comparator.comparingInt(a -> a));
        int ans = 0;
        int size = list.size();
        int first = list.get(0);
        for (int i = 1; i < size; ++i) {
            int num = list.get(i);
            ans += (num - first) / x * map.get(num);
        }
        int prev = ans;
        int count = map.get(first);
        int total = m * n;
        for (int i = 1; i < size; ++i) {
            int c = (list.get(i) - list.get(i - 1)) / x;
            prev += ((count << 1) - total) * c;
            ans = Math.min(ans, prev);
            count += map.get(list.get(i));
        }
        return ans;
    }

    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        int[] nums = new int[total];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int diff = grid[i][j] - grid[0][0];
                if (diff % x != 0) {
                    return -1;
                }
                nums[i * n + j] = diff;
            }
        }
        int pivot = getKth(nums, 0, total - 1, (total + 1) >> 1);
        int ans = 0;
        for (int num : nums) {
            ans += Math.abs(num - pivot) / x;
        }
        return ans;
    }

    private int getKth(int[] nums, int s, int e, int k) {
        int index = getIndex(nums, s, e);
        if (index == -1) {
            return nums[s];
        }
        swap(nums, index, e);
        int pivot = nums[e];
        int p = s;
        for (int i = s; i < e; ++i) {
            if (nums[i] <= pivot) {
                swap(nums, p, i);
                ++p;
            }
        }
        swap(nums, p, e);
        int c = p - s + 1;
        if (c < k) {
            return getKth(nums, p + 1, e, k - c);
        } else if (c > k) {
            return getKth(nums, s, p - 1, k);
        } else {
            return nums[p];
        }
    }

    private int getIndex(int[] nums, int s, int e) {
        int index1 = (int) (s + Math.random() * (e - s + 1));
        int count = 0;
        int index = index1;
        while (count < 5 && nums[index] == nums[index1]) {
            index = (int) (s + Math.random() * (e - s + 1));
            ++count;
        }
        if (nums[index] == nums[index1]) {
            for (int i = s; i <= e; ++i) {
                if (nums[i] != nums[index]) {
                    return i;
                }
            }
            return -1;
        }
        return index;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

//    public static void main(String[] args) {
//        try (FileReader fileReader = new FileReader("/Users/shuangpeng/Downloads/test.json")) {
//            JsonReader reader = new JsonReader(fileReader);
//            JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();
//            int m = jsonArray.size();
//            int n = jsonArray.get(0).getAsJsonArray().size();
//            int[][] grid = new int[m][n];
//            for (int i = 0; i < m; ++i) {
//                JsonArray array = jsonArray.get(i).getAsJsonArray();
//                for (int j = 0; j < n; ++j) {
//                    grid[i][j] = array.get(j).getAsInt();
//                }
//            }
//            Problem2033 a = new Problem2033();
////        int[][] grid = {{1, 5}, {2, 3}};
////        int[][] grid = {{980, 476, 644, 56}, {644, 140, 812, 308}, {812, 812, 896, 560}, {728, 476, 56, 812}};
//            a.minOperations(grid, 1);
//        } catch (Exception e) {
//            int i = 1;
//        }
//    }
}
