package com.shuangpeng.Problem.p0401_0500;

/**
 * @Description: Problem0468ValidateIPAddress（验证IP地址）
 * @Date 2022/5/29 3:20 PM
 * @Version 1.0
 */
public class Problem0468ValidateIPAddress {

    public String validIPAddress(String queryIP) {
        if (queryIP.indexOf('.') != -1) {
            if (checkIPv4(queryIP)) {
                return "IPv4";
            } else {
                return "Neither";
            }
        } else if (queryIP.indexOf(':') != -1) {
            if (checkIPv6(queryIP)) {
                return "IPv6";
            } else {
                return "Neither";
            }
        } else {
            return "Neither";
        }
    }

    private boolean checkIPv4(String ip) {
        if (ip.length() == 0 || ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') {
            return false;
        }
        String[] strs = ip.split("\\.");
        if (strs.length != 4) {
            return false;
        }
        for (String s : strs) {
            int n = s.length();
            if (n == 0 || n > 3 || (s.charAt(0) == '0' && n != 1)) {
                return false;
            }
            int num = 0;
            for (int i = 0; i < n; ++i) {
                int j = s.charAt(i) - '0';
                if (j < 0 || j > 9) {
                    return false;
                }
                num = num * 10 + j;
            }
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIPv6(String ip) {
        if (ip.length() == 0 || ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':') {
            return false;
        }
        String[] strs = ip.split(":");
        if (strs.length != 8) {
            return false;
        }
        for (String s : strs) {
            int n = s.length();
            if (n < 1 || n > 4) {
                return false;
            }
            for (int i = 0; i < n; ++i) {
                char c = s.charAt(i);
                if (!(c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F')) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        Problem0468ValidateIPAddress a = new Problem0468ValidateIPAddress();
//        a.validIPAddress("172.16.254.1");
//    }
}
