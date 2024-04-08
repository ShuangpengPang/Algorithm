package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3048EarliestSecondToMarkIndicesI（标记所有下标的最早秒数I）
 * @date 2024/3/6 2:44 PM
 */

//单算移关与，异或逻条赋
public class Problem3048EarliestSecondToMarkIndicesI {

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = changeIndices.length, left = nums.length, right = n;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(nums, changeIndices, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left <= n ? left : -1;
    }

    private boolean check(int[] nums, int[] changeIndices, int m) {
        int n = nums.length;
        int[] lastIndex = new int[n], indices = new int[n];
        int index = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            int idx = changeIndices[i] - 1;
            if (lastIndex[idx] == 0) {
                lastIndex[idx] = i;
                indices[index--] = idx;
            }
        }
        if (index >= 0) {
            return false;
        }
        int[] copy = nums.clone();
        index = 0;
        for (int i = 0; i < m && index < n; i++) {
            int idx = indices[index];
            if (i > lastIndex[idx]) {
                return false;
            }
            if (copy[idx] > 0) {
                copy[idx]--;
            } else {
                index++;
            }
        }
        return index == n;
    }
}

class Problem3048EarliestSecondToMarkIndicesI0 {

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = changeIndices.length, left = nums.length, right = n;
        int[] lastTime = new int[nums.length];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(nums, changeIndices, mid, lastTime)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left <= n ? left : -1;
    }

    private boolean check(int[] nums, int[] changeIndices, int m, int[] lastTime) {
        Arrays.fill(lastTime, -1);
        for (int i = 0; i < m; i++) {
            lastTime[changeIndices[i] - 1] = i;
        }
        for (int t : lastTime) {
            if (t == -1) {
                return false;
            }
        }
        for (int i = 0, cnt = 0; i < m; i++) {
            int idx = changeIndices[i] - 1;
            if (lastTime[idx] == i) {
                if (cnt < nums[idx]) {
                    return false;
                }
                cnt -= nums[idx];
            } else {
                cnt++;
            }
        }
        return true;
    }
}

class Problem3048EarliestSecondToMarkIndicesI1 {

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = changeIndices.length, left = nums.length, right = n;
        int[] lastTime = new int[nums.length];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(nums, changeIndices, mid, lastTime)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left <= n ? left : -1;
    }

    private boolean check(int[] nums, int[] changeIndices, int m, int[] lastTime) {
        int total = 0, cnt = 0;
        for (int i = m - 1; i >= 0 && total <= i + 1; i--) {
            int idx = changeIndices[i] - 1;
            if (lastTime[idx] != m) {
                lastTime[idx] = m;
                total += nums[idx];
                cnt++;
            } else if (total > 0) {
                total--;
            }
        }
        return total == 0 && cnt == nums.length;
    }
}

// https://leetcode.cn/problems/earliest-second-to-mark-indices-i/solutions/2660586/om-tan-xin-ce-lue-by-quantfund-j7w7/
//https://leetcode.com/problems/earliest-second-to-mark-indices-i/discuss/4793604/A-O(m)-solution-with-explanation-and-codes
//int dp[2000];
//class Solution {
//    public:
//    int earliestSecondToMarkIndices(vector<int>& nums, vector<int>& tars) {
//        ios::sync_with_stdio(false);
//        cin.tie(nullptr);
//        long sum=0;
//        int n=nums.size(), m=tars.size(), cur=0, rem=0, cnt=0;
//        for(int i=0; i<n; dp[i++]=INT_MAX) {}
//        for(; cur<m && cnt<n; ++cur) {
//            if(dp[--tars[cur]]<rem) {
//                --cnt;
//                sum-=nums[tars[cur]];
//            }
//            dp[tars[cur]]=cur;
//            for(; cnt<n && rem<=cur; ++rem) {
//                if(dp[tars[rem]]==rem) {
//                    if(sum+cnt+nums[tars[rem]]<=rem) {
//                        sum+=nums[tars[rem]];
//                        ++cnt;
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//        return cnt==n? rem : -1;
//    }
//};
