package com.shuangpeng.Problem;

public class Problem0645SetMismatch {

    public int[] findErrorNums0(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = nums[i];
            while (j != i + 1) {
                if (nums[j - 1] == j) {
                    break;
                }
                int temp = nums[j - 1];
                nums[j - 1] = j;
                j = temp;
            }
            nums[i] = j;
        }
        int[] answer = new int[2];
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                answer[0] = nums[i];
                answer[1] = i + 1;
            }
        }
        return answer;
    }

    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int x = 0;
        for (int i = 0; i < n; i++) {
            x ^= nums[i];
            x ^= (i + 1);
        }
        int lowbit = x & -x;
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & lowbit) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
            if (((i + 1) & lowbit) == 0) {
                a ^= (i + 1);
            } else {
                b ^= (i + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == b) {
                int temp = b;
                b = a;
                a = temp;
                break;
            } else if (nums[i] == a) {
                break;
            }
        }
        return new int[]{a, b};
    }
}
