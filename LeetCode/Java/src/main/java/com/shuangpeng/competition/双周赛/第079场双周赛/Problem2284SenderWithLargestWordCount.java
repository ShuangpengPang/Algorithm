package com.shuangpeng.competition.双周赛.第079场双周赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem2284SenderWithLargestWordCount（最多单词数的发件人）
 * @Date 2022/6/24 11:13 AM
 * @Version 1.0
 */
public class Problem2284SenderWithLargestWordCount {

    // 比赛时写法
    class Info {
        String name;
        int count;

        Info(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    public String largestWordCount0(String[] messages, String[] senders) {
        int n = messages.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(senders[i], map.getOrDefault(senders[i], 0) + messages[i].split(" ").length);
        }
        int size = map.size();
        List<Info> list = new ArrayList<>(size);
        for (String name : map.keySet()) {
            list.add(new Info(name, map.get(name)));
        }
        list.sort((a, b) -> {
            if (a.count != b.count) {
                return a.count - b.count;
            }
            return a.name.compareTo(b.name);
        });
        return list.get(size - 1).name;
    }

    public String largestWordCount1(String[] messages, String[] senders) {
        int n = messages.length;
        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            String sender = senders[i];
            int count = map.getOrDefault(sender, 0) + messages[i].split(" ").length;
            map.put(sender, count);
            maxCount = Math.max(maxCount, count);
        }
        String ans = "";
        for (String sender : map.keySet()) {
            if (map.get(sender) == maxCount) {
                if (ans.compareTo(sender) < 0) {
                    ans = sender;
                }
            }
        }
        return ans;
    }

    public String largestWordCount2(String[] messages, String[] senders) {
        int n = messages.length;
        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            String sender = senders[i];
            int count = map.getOrDefault(sender, 0) + messages[i].split(" ").length;
            map.put(sender, count);
            maxCount = Math.max(maxCount, count);
        }
        String ans = "";
        for (String sender : map.keySet()) {
            if (map.get(sender) == maxCount) {
                if (compare(ans, sender) < 0) {
                    ans = sender;
                }
            }
        }
        return ans;
    }

    private int compare(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int i = 0;
        for (; i < n1 && i < n2; i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (c1 == c2) {
                continue;
            }
            if (c1 >= 'A' && c1 <= 'Z' && c2 >= 'A' && c2 <= 'Z') {
                return c1 - c2;
            } else if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                return c1 - c2;
            } else if (c1 >= 'A' && c1 <= 'Z') {
                return -1;
            } else {
                return 1;
            }
        }
        return n1 - n2;
    }

    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new HashMap<>();
        int n = messages.length;
        String ans = "";
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            String sender = senders[i];
            int count = map.getOrDefault(sender, 0) + messages[i].split(" ").length;
            map.put(sender, count);
            if (count > maxCount) {
                maxCount = count;
                ans = sender;
            } else if (count == maxCount && ans.compareTo(sender) < 0) {
                ans = sender;
            }
        }
        return ans;
    }
}
