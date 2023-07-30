package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1248CountNumberOfNiceSubarrays（统计「优美子数组」）
 * @date 2023/6/14 11:11 AM
 */
public class Problem1248CountNumberOfNiceSubarrays {

    public int numberOfSubarrays0(int[] nums, int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>(n);
        list.add(-1);
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                list.add(i);
            }
        }
        list.add(n);
        int ans = 0;
        for (int i = k; i < list.size() - 1; i++) {
            ans += (list.get(i - k + 1) - list.get(i - k)) * (list.get(i + 1) - list.get(i));
        }
        return ans;
    }

    public int numberOfSubarrays1(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        for (int i = 0, c = 0; i < n; i++) {
            c += nums[i] & 1;
            cnt[c]++;
            ans += c >= k ? cnt[c - k] : 0;
        }
        return ans;
    }

    public int numberOfSubarrays2(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int p1 = 0, p2 = 0, cnt1 = 0, cnt2 = 0, i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                cnt1++;
                cnt2++;
                while (cnt2 == k) {
                    if ((nums[p2] & 1) == 1) {
                        cnt2--;
                    }
                    p2++;
                }
                while (cnt1 > k) {
                    if ((nums[p1] & 1) == 1) {
                        cnt1--;
                    }
                    p1++;
                }
            }
            ans += p2 - p1;
        }
        return ans;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0, p1 = 0, p2 = 0, c = 0; i < n; i++) {
            if ((nums[i] & 1) == 1 && ++c == k) {
                p1 = p2;
                while ((nums[p2] & 1) == 0) {
                    p2++;
                }
                p2++;
                c--;
            }
            ans += p2 - p1;
        }
        return ans;
    }
}

//class Solution {
//    public int numberOfSubarrays(int[] nums, int k) {
//        int left = 0, right = 0, oddCnt = 0, res = 0;
//        while (right < nums.length) {
//            // 右指针先走，每遇到一个奇数则 oddCnt++。
//            if ((nums[right++] & 1) == 1) {
//                oddCnt++;
//            }
//
//            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
//            if (oddCnt == k) {
//                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
//                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
//                int tmp = right;
//                while (right < nums.length && (nums[right] & 1) == 0) {
//                    right++;
//                }
//                int rightEvenCnt = right - tmp;
//                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
//                int leftEvenCnt = 0;
//                while ((nums[left] & 1) == 0) {
//                    leftEvenCnt++;
//                    left++;
//                }
//                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
//                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
//                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
//                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
//                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
//                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);
//
//                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
//                left++;
//                oddCnt--;
//            }
//
//        }
//
//        return res;
//    }
//}
