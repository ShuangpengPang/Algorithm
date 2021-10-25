package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0229MajorityElementII {

    public List<Integer> majorityElement0(int[] nums) {
        int n = nums.length;
        int first = 0, second = 0, firstCount = 0, secondCount = 0;
        for (int num : nums) {
            if (firstCount == 0 || secondCount == 0) {
                if (firstCount == 0 && secondCount == 0) {
                    first = num;
                    ++firstCount;
                } else if (firstCount == 0 && num != second) {
                    first = num;
                    ++firstCount;
                } else if (secondCount == 0 && num != first) {
                    second = num;
                    ++secondCount;
                } else if (num == first) {
                    ++firstCount;
                } else if (num == second) {
                    ++secondCount;
                }
            } else if (num == first) {
                ++firstCount;
            } else if (num == second) {
                ++secondCount;
            } else {
                --firstCount;
                --secondCount;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (firstCount == 0 && secondCount == 0) {
            return ans;
        }
        firstCount = 0;
        secondCount = 0;
        for (int num : nums) {
            if (num == first) {
                ++firstCount;
            } else if (num == second) {
                ++secondCount;
            }
        }
        if (firstCount > n / 3) {
            ans.add(first);
        }
        if (secondCount > n / 3) {
            ans.add(second);
        }
        return ans;
    }

    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int first = 0, second = 0, firstCount = 0, secondCount = 0;
        for (int num : nums) {
            if (num == first && firstCount > 0) {
                ++firstCount;
            } else if (num == second && secondCount > 0) {
                ++secondCount;
            } else if (firstCount == 0) {
                first = num;
                firstCount = 1;
            } else if (secondCount == 0) {
                second = num;
                secondCount = 1;
            } else {
                --firstCount;
                --secondCount;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (firstCount == 0 && secondCount == 0) {
            return ans;
        }
        firstCount = 0;
        secondCount = 0;
        for (int num : nums) {
            if (num == first) {
                ++firstCount;
            } else if (num == second) {
                ++secondCount;
            }
        }
        if (firstCount > n / 3) {
            ans.add(first);
        }
        if (secondCount > n / 3) {
            ans.add(second);
        }
        return ans;
    }
}
