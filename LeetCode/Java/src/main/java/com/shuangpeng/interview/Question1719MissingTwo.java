package com.shuangpeng.interview;

/**
 * @Description: Question1719MissingTwo（消失的两个数字）
 * @Date 2022/9/26 11:27 AM
 * @Version 1.0
 */
public class Question1719MissingTwo {

    public int[] missingTwo0(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] <= n && nums[i] != i + 1) {
                int j = nums[i] - 1;
                nums[i] = nums[j];
                nums[j] = j + 1;
            }
        }
        int a = -1, b = -1, c = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                if (a == -1) {
                    a = i + 1;
                } else {
                    b = i + 1;
                }
                c = nums[i];
            }
        }
        if (a == -1) {
            a = n + 1;
            b = n + 2;
        } else if (b == -1) {
            b = (n << 1) + 3 - c;
        }
        return new int[]{a, b};
    }

    public int[] missingTwo1(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n + 2; i++) {
            xor ^= i;
        }
        int lowBit = xor & (-xor);
        int x1 = 0, x2 = 0;
        for (int num : nums) {
            if ((num & lowBit) == 0) {
                x1 ^= num;
            } else {
                x2 ^= num;
            }
        }
        for (int i = 1; i <= n + 2; i++) {
            if ((i & lowBit) == 0) {
                x1 ^= i;
            } else {
                x2 ^= i;
            }
        }
        return new int[]{x1, x2};
    }

    public int[] missingTwo2(int[] nums) {
        int n = nums.length + 2, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int sumTwo = ((n + 1) * n >> 1) - sum, h = sumTwo >> 1;
        sum = 0;
        for (int num : nums) {
            if (num <= h) {
                sum += num;
            }
        }
        int x = ((h + 1) * h >> 1) - sum;
        return new int[]{x, sumTwo - x};
    }

    public int[] missingTwo(int[] nums) {
        int n = nums.length, a = -1, b = -1;
        for (int i = 0; i < n; i++) {
            while (nums[i] != -1 && nums[i] != i + 1) {
                int j = nums[i] - 1;
                if (j < n) {
                    nums[i] = nums[j];
                    nums[j] = j + 1;
                } else if (j == n) {
                    nums[i] = a;
                    a = j + 1;
                } else {
                    nums[i] = b;
                    b = j + 1;
                }
            }
        }
        int x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == -1) {
                if (x == -1) {
                    x = i + 1;
                } else {
                    y = i + 1;
                }
            }
        }
        if (a == -1) {
            if (x == -1) {
                x = n + 1;
            } else {
                y = n + 1;
            }
        }
        if (b == -1) {
            y = n + 2;
        }
        return new int[]{x, y};
    }
}
