package com.shuangpeng.Problem.p0901_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0929UniqueEmailAddresses（独特的电子邮件地址）
 * @Date 2022/6/4 11:45 AM
 * @Version 1.0
 */
public class Problem0929UniqueEmailAddresses {

    public int numUniqueEmails(String[] emails) {
        int n = emails.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            String email = emails[i];
            String[] strs = email.split("@");
            String localName = strs[0], domain = strs[1];
            StringBuilder sb = new StringBuilder();
            int m = localName.length();
            for (int j = 0; j < m; ++j) {
                char c = localName.charAt(j);
                if (c == '.') {
                    continue;
                } else if (c == '+') {
                    break;
                } else {
                    sb.append(c);
                }
            }
            sb.append('@');
            sb.append(domain);
            set.add(sb.toString());
        }
        return set.size();
    }
}
