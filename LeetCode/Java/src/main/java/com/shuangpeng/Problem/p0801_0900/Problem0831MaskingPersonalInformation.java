package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0831MaskingPersonalInformation（隐藏个人信息）
 * @date 2022/11/16 11:53 PM
 */
public class Problem0831MaskingPersonalInformation {

    public String maskPII0(String s) {
        if (Character.isLetter(s.charAt(0))) {
            return maskEmail(s);
        } else {
            return maskNumber(s);
        }
    }

    private String maskEmail(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        boolean isDomain = false;
        for (int i = 0; i < n; i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (i == 0) {
                sb.append(c);
            } else if (!isDomain && s.charAt(i + 1) == '@') {
                sb.append("*****").append(c);
            } else if (c == '@') {
                isDomain = true;
                sb.append(c);
            } else if (isDomain) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private String maskNumber(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        StringBuilder ans = new StringBuilder();
        int cnt = sb.length() - 10;
        if (cnt > 0) {
            ans.append('+');
        }
        for (int i = 0; i < cnt; i++) {
            ans.append('*');
        }
        if (cnt > 0) {
            ans.append('-');
        }
        ans.append("***-***-").append(sb.substring(sb.length() - 4, sb.length()));
        return ans.toString();
    }

    public String maskPII1(String s) {
        int index = s.indexOf('@');
        if (index != -1) {
            s = s.toLowerCase();
            return index <= 1 ? s.toLowerCase() : s.charAt(0) + "*****" + s.charAt(index - 1) + s.substring(index);
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        int m = sb.length();
        return (m == 10 ? "" : m == 11 ? "+*-" : m == 12 ? "+**-" : "+***-") + "***-***-" + sb.substring(m - 4);
    }

    public String maskPII(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        if (Character.isLetter(s.charAt(0))) {
            sb.append(Character.toLowerCase(s.charAt(0))).append("*****");
            int i = 0;
            while (s.charAt(i + 1) != '@') {
                i++;
            }
            sb.append(Character.toLowerCase(s.charAt(i))).append('@');
            i += 2;
            while (i < n) {
                sb.append(Character.toLowerCase(s.charAt(i)));
                i++;
            }
        } else {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    tmp.append(c);
                }
            }
            int size = tmp.length();
            if (size == 10) {
                sb.append("***-***-").append(tmp.substring(size - 4));
            } else if (size == 11) {
                sb.append("+*-***-***-").append(tmp.substring(size - 4));
            } else if (size == 12) {
                sb.append("+**-***-***-").append(tmp.substring(size - 4));
            } else if (size == 13) {
                sb.append("+***-***-***-").append(tmp.substring(size - 4));
            }
        }
        return sb.toString();
    }
}
