package com.shuangpeng;

public class Problem0005LongestPalindromicSubstring {

    // 马拉车算法
    public String longestPalindrome0(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append('#').append(s.charAt(i));
        }
        stringBuilder.append('#');
//        String string = stringBuilder.toString();
        char[] chars = stringBuilder.toString().toCharArray();
        int[] array = new int[chars.length];
        array[0] = 1;
        int center = 0;
        int radius = array[center] - 1;
        int left = center - radius;
        int right = center + radius;
        int max = 1;
        int maxCenter = 1;
        for (int i = 1; i < array.length; i++) {
            if (i < right) {
                int mirror = 2 * center - i;
                int l = mirror - (array[mirror] - 1);
                if (l > left) {
                    array[i] = array[mirror];
                } else if (l == left) {
                    int count = 0;
                    int start = right + 1;
                    int cor = 2 * i - start;
                    while ((cor >= 0) && (start < array.length) && (chars[start] == chars[cor])) {
                        count++;
                        start++;
                        cor--;
                    }
                    array[i] = array[mirror] + count;
                    center = i;
                    radius = array[i] - 1;
                    left = i - radius;
                    right = i + radius;
                } else if (l < left) {
                    array[i] = right - i + 1;
                    center = i;
                    radius = array[i] - 1;
                    left = i - radius;
                    right = i + radius;
                }
            } else {
                int count = 0;
                int r = i + 1;
                int l = 2 * i - r;
                while ((l >= 0) && (r < array.length) && (chars[l] == chars[r])) {
                    count++;
                    r++;
                    l--;
                }
                array[i] = 1 + count;
                center = i;
                radius = array[i] - 1;
                left = i - radius;
                right = i + radius;
            }
            if (array[i] > max) {
                max = array[i];
                maxCenter = i;
            }
        }
        int rad = array[maxCenter] - 1;
        int start = maxCenter - rad;
        int end = maxCenter + rad;
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (chars[i] != '#') {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    // 马拉车算法（简化）
    public String longestPalindrome3(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append('#').append(s.charAt(i));
        }
        stringBuilder.append('#');
        char[] chars = stringBuilder.toString().toCharArray();
        int[] array = new int[chars.length];
        array[0] = 1;
        int center = 0;
        int left = 0;
        int right = 0;
        int max = 1;
        int start = 1;
        for (int i = 1; i < array.length; i++) {
            if (i < right) {
                int mirror = 2 * center - i;
                int len = right - i + 1;
                array[i] = Math.min(len, array[mirror]);
                if ((array[i] < len) || (array[mirror] > len)) {
                    continue;
                }
            }
            int r = i + array[i];
            int l = 2 * i - r;
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                array[i]++;
                r++;
                l--;
            }
            center = i;
            left = l + 1;
            right = r - 1;
            if (right - left + 1 > max) {
                max = right - left + 1;
                start = left;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < start + max; i++) {
            if (chars[i] != '#') {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    // 动态规划
    public String longestPalindrome1(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        int max = 1;
        int index = 0;
        for (int i = 0; i < length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                if (max == 1) {
                    max = 2;
                    index = i;
                }
                dp[i][i + 1] = true;
            }
        }
        for (int d = 2; d < length; d++) {
            for (int i = 0; i < length - d; i++) {
                if (chars[i] == chars[i + d] && dp[i + 1][i + d - 1]) {
                    if (d + 1 > max) {
                        max = d + 1;
                        index = i;
                    }
                    dp[i][i + d] = true;
                }
            }
        }
        return s.substring(index, index + max);
    }

    // 中心扩展法
    public String longestPalindrome2(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int max = 1;
        int index = 0;
        for (int i = 0; i < length; i++) {
            int j = 1;
            while (i - j >= 0 && i + j < length && chars[i - j] == chars[i + j]) {
                j++;
            }
            j--;
            int l = 1 + 2 * j;
            if (l > max) {
                max = l;
                index = i - j;
            }
            if (i + 1 < length && chars[i] == chars[i + 1]) {
                j = 1;
                while (i - j >= 0 && i + j + 1 < length && chars[i - j] == chars[i + j + 1]) {
                    j++;
                }
                j--;
                l = 2 + 2 * j;
                if (l > max) {
                    max = l;
                    index = i - j;
                }
            }
        }
        return s.substring(index, index + max);
    }

    // 中心扩展法
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int max = 1;
        int index = 0;
        for (int i = 0; i < length; i++) {
            int length1 = expandAroundCenter(chars, i, i);
            int length2 = expandAroundCenter(chars, i, i + 1);
            int len = Math.max(length1, length2);
            if (len > max) {
                max = len;
                index = i - (len + 1) / 2 + 1;
            }
        }
        return s.substring(index, index + max);
    }

    public int expandAroundCenter(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }


//    public static void main(String[] args) {
//        Problem0005LongestPalindromicSubstring a = new Problem0005LongestPalindromicSubstring();
//        a.longestPalindrome("ccc");
//
////        Problem0005LongestPalindromicSubstring a = new Problem0005LongestPalindromicSubstring();
////        int[][] array = {
////                {1, 3, 4, 8, 10},
////                {12, 15, 17, 20, 23}
////        };
////        long start = System.currentTimeMillis();
////        System.err.println("result is: " + a.findElement(array, 25));
////        System.err.println("用时： " + (System.currentTimeMillis() - start));
//    }

    public boolean findElement(int[][] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }
        int rows = array.length;
        int cols = array[0].length;

        int start = 0;
        int end = rows * cols - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int r = getRowIndex(mid, cols);
            int c = getColIndex(mid, cols);
            int data = array[r][c];
            if (data == target) {
                return true;
            } else if (target < data) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public int getRowIndex(int index, int cols) {
        int number = index + 1;
        int r = number / cols;
        if (number % cols == 0) {
            return r - 1;
        } else {
            return r;
        }
    }

    public int getColIndex(int index, int cols) {
        int number = index + 1;
        if (number % cols == 0) {
            return cols - 1;
        }
        return number % cols - 1;
    }
}
