package com.shuangpeng;

public class Problem0075SortColor {

    // 计数排序
    public void sortColors0(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
            } else if (nums[i] == 1) {
                count1++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

//    public static void main(String[] args) {
//        Problem0075SortColor a = new Problem0075SortColor();
//        int[] nums = {2,0,2,1,1,0};
//        a.sortColors(nums);
//    }

    // 单指针
    public void sortColors1(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int zero = -1;
        int one = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
                swap(nums, zero, i);
                one++;
                if (zero != one) {
                    swap(nums, one, i);
                }
            } else if (nums[i] == 1) {
                one++;
                swap(nums, one, i);
            }
        }
    }

//    public static void main(String[] args) {
//        Problem0075SortColor a = new Problem0075SortColor();
////        int[] nums = {2,2,1,2,1,1,1,0,0,2,1,0,2,1,2,2,1,1,1,1,1,0,2,0,2,0,2,2,1,0,2,1,0,2,1,2,0,0,0,0,2,1,1,2,0,1,2,2,0,0,2,2,0,1,0,1,0,0,1,1,1,0,0,2,2,2,1,0,0,2,1,0,1,0,2,2,1,2,1,1,2,1,1,0,0,2,1,0,0};
//        int[] nums = {1, 2, 0};
//        a.sortColors(nums);
//    }

    public void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    // 双指针
//    public void sortColors(int[] nums) {
//        if (nums.length == 1) {
//            return;
//        }
//        int i = 0;
//        int j = nums.length - 1;
//        int left = i;
//        int right = j;
//        while (i <= right || left <= j) {
//            while (i < nums.length && nums[i] == 1) {
//                i++;
//            }
//            while (j >= 0 && nums[j] == 1) {
//                j--;
//            }
//            // nums[i] = 0、2 || i = nums.length
//            // nums[j] = 0、2 || j = -1
////            if (i > j || i >= nums.length || j < 0) {
////                return;
////            }
//
//            // 00, 02, 20, 22
//            if (nums[i] == 2 && nums[j] == 0) {
//                swap(nums, left, j);
//                if (left != i && right != j) {
//                    swap(nums, right, i);
//                    right--;
//                    j--;
//                } else if (left == i && right == j) {
//                    right--;
//                    j--;
//                } else if (left == i) {
//                    swap(nums, j, right);
//                    right--;
//                    j--;
////                } else if (right == j) {
//                }
//                left++;
//                i++;
//                continue;
//            }
//            if (nums[i] == 0) {
//                swap(nums, left, i);
//                left++;
//                i++;
//            }
//            if (nums[j] == 2) {
//                swap(nums, right, j);
//                right--;
//                j--;
//            }
//        }
//    }
}
