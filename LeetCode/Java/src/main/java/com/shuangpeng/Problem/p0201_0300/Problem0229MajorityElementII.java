package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.List;

/***
 * @description:
 * @param null : 
 * @return null
 * @date 2025/2/24 3:29 PM
 **/
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

class Problem0229MajorityElementII0 {

    public List<Integer> majorityElement(int[] nums) {
        int x = 0, y = 0, cntX = 0, cntY = 0;
        for (int i = 0, n = nums.length; i < n; i++) {
            if (cntX == 0 && nums[i] != y || nums[i] == x) {
                x = nums[i];
                cntX++;
            } else if (cntY == 0 && nums[i] != x || nums[i] == y) {
                y = nums[i];
                cntY++;
            } else {
                --cntX;
                --cntY;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (cntX > 0 && check(nums, x)) {
            ans.add(x);
        }
        if (cntY > 0 && check(nums, y)) {
            ans.add(y);
        }
        return ans;
    }

    private boolean check(int[] nums, int num) {
        int cnt = 0;
        for (int x : nums) {
            if (x == num) {
                cnt++;
            }
        }
        return cnt * 3 > nums.length;
    }
}
