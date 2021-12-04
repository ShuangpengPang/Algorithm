package com.shuangpeng.Problem.p0001_0100;

public class Problem0012IntegerToRoman {

    //        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000

//        - I` can be placed before `V` (5) and `X` (10) to make 4 and 9.
//                - `X` can be placed before `L` (50) and `C` (100) to make 40 and 90.
//                - `C` can be placed before `D` (500) and `M` (1000) to make 400 and 900.

    public String intToRoman0(int num) {
        int[] nums = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] strs = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        int count = num / nums[n - 1];
        for (int i = 0; i < count; ++i) {
            sb.append(strs[n - 1]);
        }
        num -= count * nums[n - 1];
        while (num > 0) {
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (num >= nums[mid]) {
                    left = mid + 1;
                } else if (num < nums[mid]) {
                    right = mid - 1;
                }
            }
            num -= nums[left - 1];
            sb.append(strs[left - 1]);
        }
        return sb.toString();
    }

    public String intToRoman(int num) {
        int[] nums = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] strs = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0 && num > 0; --i) {
            while (num >= nums[i]) {
                sb.append(strs[i]);
                num -= nums[i];
            }
        }
        return sb.toString();
    }
}
