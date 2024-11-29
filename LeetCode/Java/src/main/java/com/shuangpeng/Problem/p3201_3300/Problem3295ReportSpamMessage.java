package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3295ReportSpamMessage（举报垃圾信息）
 * @date 2024/11/29 7:04 PM
 */
public class Problem3295ReportSpamMessage {

    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> set = new HashSet<String>(Arrays.asList(bannedWords));
        int count = 0;
        for (String m : message) {
            if (set.contains(m) && ++count == 2) {
                return true;
            }
        }
        return false;
    }
}
