package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1537GetTheMaximumScore（最大得分）
 * @Date 2022/8/29 4:47 PM
 * @Version 1.0
 */
public class Problem1537GetTheMaximumScore {

    public int maxSum(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int s1 = 0, s2 = 0, i1 = 0, i2 = 0;
        long sum = 0;
        while (i1 < n1 && i2 < n2) {
            while (i1 < n1 && i2 < n2 && nums1[i1] != nums2[i2]) {
                while (i1 < n1 && nums1[i1] < nums2[i2]) {
                    i1++;
                }
                if (i1 >= n1 || nums1[i1] == nums2[i2]) {
                    break;
                }
                while (i2 < n2 && nums1[i1] > nums2[i2]) {
                    i2++;
                }
            }
            int t1 = i1 >= n1 || i2 >= n2 ? n1 : i1 + 1;
            int t2 = i1 >= n1 || i2 >= n2 ? n2 : i2 + 1;
            sum += Math.max(getSum(nums1, s1, t1), getSum(nums2, s2, t2));
            s1 = i1 = t1;
            s2 = i2 = t2;
        }
        sum += getSum(nums1, s1, n1);
        sum += getSum(nums2, s2, n2);
        return (int) (sum % (long) (1e9 + 7));
    }

    private long getSum(int[] nums, int s, int e) {
        long sum = 0;
        for (int i = s; i < e; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
