package com.shuangpeng.competition.第290到300场周赛.第296场周赛;

/**
 * @Description: Problem2293MinMaxGame（极大极小游戏）
 * @Date 2022/6/11 3:29 PM
 * @Version 1.0
 */
public class Problem2293MinMaxGame {

    // 比赛时写法
    public int minMaxGame0(int[] nums) {
        while (nums.length > 1) {
            int n = nums.length;
            int h = n >> 1;
            int[] temp = new int[h];
            for (int i = 0; i < h; ++i) {
                if (i % 2 == 0) {
                    temp[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    temp[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
            nums = temp;
        }
        return nums[0];
    }

    public int minMaxGame1(int[] nums) {
        while (nums.length > 1) {
            int n = nums.length >> 1;
            int[] temp = new int[n];
            for (int i = 0; i < n; ++i) {
                temp[i] = (i & 1) == 0 ? Math.min(nums[i << 1], nums[(i << 1) + 1]) : Math.max(nums[i << 1], nums[(i << 1) + 1]);
            }
            nums = temp;
        }
        return nums[0];
    }

    public int minMaxGame2(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            int h = n >> 1;
            for (int i = 0; i < h; ++i) {
                int j = i << 1;
                nums[i] = (i & 1) == 0 ? Math.min(nums[j], nums[j + 1]) : Math.max(nums[j], nums[j + 1]);
            }
            n = h;
        }
        return nums[0];
    }

    public int minMaxGame3(int[] nums) {
        while (nums.length > 1) {
            int n = nums.length;
            int[] tmp = new int[n >> 1];
            for (int i = 0; i < n; i += 2) {
                int j = i >> 1;
                tmp[j] = (j & 1) == 0 ? Math.min(nums[i], nums[i + 1]) : Math.max(nums[i], nums[i + 1]);
            }
            nums = tmp;
        }
        return nums[0];
    }

    public int minMaxGame(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            int h = n >> 1;
            for (int i = 0; i < h; i++) {
                nums[i] = (i & 1) == 0 ? Math.min(nums[i << 1], nums[(i << 1) + 1]) : Math.max(nums[i << 1], nums[(i << 1) + 1]);
            }
            n = h;
        }
        return nums[0];
    }
}
