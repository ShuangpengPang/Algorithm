package com.shuangpeng.Problem;

public class Problem0457CircularArrayLoop {

    public boolean circularArrayLoop0(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] % n == 0) {
                continue;
            }
            int j = i;
            int k = ((j + nums[j]) % n + n) % n;
            int s = 0;
            while (k != i && nums[j] * nums[k] > 0 && s < n) {
                j = k;
                k = ((j + nums[j]) % n + n) % n;
                s++;
            }
            if (k == i) {
                return true;
            }
        }
        return false;
    }

    public boolean circularArrayLoop1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] >= -1000 && nums[i] <= 1000) {
                int sign = (nums[i] > 0 ? 1 : -1);
                int factor = sign * (i + 1) * 1000;
                int j = i;
                int k = ((j + nums[j]) % n + n) % n;
                while (j != k && nums[j] * nums[k] > 0 && nums[k] >= -1000 && nums[k] <= 1000) {
                    nums[j] += factor;
                    j = k;
                    k = ((j + nums[j]) % n + n) % n;
                }
                if (nums[j] * nums[k] <= 0 || j == k) {
                    nums[i] = 0;
                } else if ((sign > 0 && nums[k] > factor) || (sign < 0 && nums[k] < factor)) {
                    return true;
                } else {
                    nums[i] = 0;
                }
            }
        }
        return false;
    }

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = getNext(nums, i);
            int fast = getNext(nums, slow);
            while (slow != fast && slow != -1 && fast != -1) {
                slow = getNext(nums, slow);
                fast = getNext(nums, getNext(nums, fast));
            }
            if (slow == fast && slow != -1) {
                return true;
            }
            slow = i;
            while (slow != -1) {
                nums[slow] = 0;
                slow = getNext(nums, slow);
            }
        }
        return false;
    }

    private int getNext(int[] nums, int curr) {
        if (curr == -1) {
            return -1;
        }
        int n = nums.length;
        int i = ((curr + nums[curr]) % n + n) % n;
        if (i == curr || nums[i] * nums[curr] <= 0) {
            return -1;
        }
        return i;
    }

//    public boolean circularArrayLoop(int[] nums) {
//        int n = nums.length;
//        for (int i = 0; i < n; ++i) {
//            if (nums[i] == 0) {
//                continue;
//            }
//
//        }
//    }


//    public static void main(String[] args) {
//        Problem0457CircularArrayLoop a = new Problem0457CircularArrayLoop();
//        a.circularArrayLoop(new int[]{2, -1, 1, 2, 2});
//    }
}
