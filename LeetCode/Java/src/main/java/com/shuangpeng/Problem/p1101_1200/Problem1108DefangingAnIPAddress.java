package com.shuangpeng.Problem.p1101_1200;

/**
 * @Description: Problem1108DefangingAnIPAddress（IP地址无效化）
 * @Date 2022/6/21 4:37 PM
 * @Version 1.0
 */
public class Problem1108DefangingAnIPAddress {

    public String defangIPaddr0(String address) {
        return address.replaceAll("\\.", "\\[\\.\\]");
    }

    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        int n = address.length();
        for (int i = 0; i < n; ++i) {
            char c = address.charAt(i);
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
