package com.shuangpeng.Problem;

import java.util.*;

public class Problem0140WordBreakII {

    public List<String> wordBreak0(String s, List<String> wordDict) {
        if (s == null || wordDict == null || s.length() == 0 || wordDict.size() == 0) {
            return new ArrayList<>();
        }
        List<String> answer = new ArrayList<>();
        backtrack(s, 0, new StringBuilder(), wordDict, answer);
        return answer;
    }

    public void backtrack(String s, int start, StringBuilder builder, List<String> wordDict, List<String> answer) {
        if (start > s.length()) {
            return;
        }
        if (start == s.length()) {
            answer.add(builder.toString().trim());
            return;
        }
        int size = wordDict.size();
        int length = builder.length();
        for (int i = 0; i < size; i++) {
            if (s.startsWith(wordDict.get(i), start)) {
                String word = wordDict.get(i);
                builder.append(' ');
                builder.append(word);
                backtrack(s, start + word.length(), builder, wordDict, answer);
                builder.delete(length, length + word.length() + 1);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || s.length() == 0 || wordDict.size() == 0) {
            return new ArrayList<>();
        }
        List<List<String>> lists = backtrack(s, new HashSet<>(wordDict), 0, new HashMap<>());
        List<String> answer = new ArrayList<>(lists.size());
        for (List<String> list : lists) {
            answer.add(String.join(" ", list));
        }
        return answer;
    }

    public List<List<String>> backtrack(String s, HashSet<String> wordSet, int start, Map<Integer, List<List<String>>> map) {
        if (!map.containsKey(start)) {
            List<List<String>> list = new LinkedList<>();
            int length = s.length();
            for (int i = start + 1; i <= length; i++) {
                String str = s.substring(start, i);
                if (wordSet.contains(str)) {
                    if (start + str.length() == length) {
                        List<String> item = new LinkedList<>();
                        item.add(str);
                        list.add(item);
                    } else {
                        List<List<String>> lists = backtrack(s, wordSet, start + str.length(), map);
                        for (List<String> item : lists) {
                            List<String> itemList = new LinkedList<>(item);
                            itemList.add(0, str);
                            list.add(itemList);
                        }
                    }
                }
            }
            map.put(start, list);
        }
        return map.get(start);
    }
}
