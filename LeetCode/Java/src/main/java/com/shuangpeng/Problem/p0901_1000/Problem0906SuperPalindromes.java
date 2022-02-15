package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description:
 * @Date 2022/2/15 2:58 PM
 * @Version 1.0
 */
public class Problem0906SuperPalindromes {

    public int superpalindromesInRange(String left, String right) {
        long min = (long) Math.sqrt(Long.parseLong(left));
        long max = (long) Math.sqrt(Long.parseLong(right));
        int ans = 0;
        for (long i = getPalindrome(min); i <= max; i = getNextPalindrome(i)) {
            if (isPalindrome(i * i)) {
                ++ans;
            }
        }
        return ans;
    }

    private long getPalindrome(long num) {
        char[] chars = Long.toString(num).toCharArray();
        int n = chars.length;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            if (chars[i] != chars[j]) {
                chars[j] = chars[i];
            }
        }
        long ans = Long.parseLong(new String(chars));
        while (ans < num) {
            ans = getNextPalindrome(ans);
        }
        return ans;
    }

    private long getNextPalindrome(long num) {
        char[] chars = Long.toString(num).toCharArray();
        int n = chars.length;
        boolean flag = true;
        for (int i = 0; i < n; ++i) {
            if (chars[i] != '9') {
                flag = false;
                break;
            }
        }
        if (flag) {
            return num + 2;
        }
        for (int i = (n - 1) / 2; i >= 0; --i) {
            if (chars[i] != '9') {
                ++chars[i];
                int j = n - i - 1;
                if (i != j) {
                    chars[j] = chars[i];
                }
                for (int k = i + 1; k < j; ++k) {
                    chars[k] = '0';
                }
                break;
            }
        }
        return Long.parseLong(new String(chars));
    }

    private boolean isPalindrome(long num) {
        String s = Long.toString(num);
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        Problem0906SuperPalindromes a = new Problem0906SuperPalindromes();
//        a.superpalindromesInRange("11695", "23815");
//    }
}
