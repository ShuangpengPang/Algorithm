package com.shuangpeng.Problem.p0901_1000;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0962MaximumWidthRamp（最大宽度坡）
 * @date 2023/3/19 11:38 PM
 */
public class Problem0962MaximumWidthRamp {

    public int maxWidthRamp0(int[] nums) {
        TreeSet<Pair<Integer, Integer>> set = new TreeSet<>((a, b) -> {
            int aNum = a.getKey(), aIndex = a.getValue();
            int bNum = b.getKey(), bIndex = b.getValue();
            if (aNum != bNum) {
                return aNum - bNum;
            }
            return bIndex - aIndex;
        });
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> pair = set.floor(new Pair<>(nums[i], -1));
            if (pair != null) {
                ans = Math.max(ans, i - pair.getValue());
            } else {
                set.add(new Pair<>(nums[i], i));
            }
        }
        return ans;
    }

    public int maxWidthRamp1(int[] nums) {
        TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int[] arr = {nums[i], i};
            int[] value = set.floor(arr);
            if (value != null) {
                ans = Math.max(ans, i - value[1]);
            } else {
                set.add(arr);
            }
        }
        return ans;
    }

    public int maxWidthRamp2(int[] nums) {
        int ans = 0, n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(nums[i]);
            if (entry != null) {
                ans = Math.max(ans, i - entry.getValue());
            } else {
                map.put(nums[i], i);
            }
        }
        return ans;
    }

    public int maxWidthRamp3(int[] nums) {
        int n = nums.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> {
            if (nums[a] != nums[b]) {
                return nums[a] - nums[b];
            }
            return a - b;
        });
        int m = n, ans = 0;
        for (int id : ids) {
            ans = Math.max(ans, id - m);
            m = Math.min(m, id);
        }
        return ans;
    }

    public int maxWidthRamp(int[] nums) {
        int n = nums.length, top = 1;
        int[] stack = new int[n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int left = 0, right = top - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (nums[stack[mid]] > nums[i]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans = Math.max(ans, left < top ? i - stack[left] : 0);
            if (nums[i] < nums[stack[top - 1]]) {
                stack[top++] = i;
            }
        }
        return ans;
    }

    //    public static void main(String[] args) {
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
//        executor.setKeepAliveTime(7 * 1000, TimeUnit.MILLISECONDS);
//        executor.allowCoreThreadTimeOut(true);
//        for (int k = 0; k < 2; k++) {
//            System.err.println("main time: " + new Date(System.currentTimeMillis()));
//            for (int i = 0; i < 20; i++) {
//                int j = i;
//                RunnableScheduledFuture task = (RunnableScheduledFuture<?>) executor.schedule(() -> {
//                    System.err.println("activeCount: " + executor.getActiveCount() + ", pollSize: " + executor.getPoolSize());
//                    System.err.println("startTime: " + j + ", " + new Date(System.currentTimeMillis()));
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.err.println("endTime: " + j + ", " + new Date(System.currentTimeMillis()));
//                }, 0, TimeUnit.SECONDS);
//                try {
//                    Thread.sleep(200);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                Thread.sleep(6 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.err.println("==============");
//            for (int i = 0; i < 10; i++) {
//                System.err.println("---------activeCount: " + executor.getPoolSize());
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
