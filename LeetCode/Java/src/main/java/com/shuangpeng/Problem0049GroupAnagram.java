package com.shuangpeng;

import java.util.*;

public class Problem0049GroupAnagram {

    public List<List<String>> groupAnagrams0(String[] strs) {
        // ["eat","tea","tan","ate","nat","bat"]
//        [["bat"],["nat","tan"],["ate","eat","tea"]]
        List<List<String>> result = new ArrayList<>();
        if (strs.length == 1) {
            if (strs[0] == null || strs[0].length() == 0) {
                List<String> item = new ArrayList<>();
                item.add("");
                result.add(item);
                return result;
            }
        }
        for (String str : strs) {
            boolean isMatch = false;
            for (List<String> list : result) {
                if (list.size() > 0) {
                    String s = list.get(0);
                    if (s.length() == str.length()) {
                        StringBuilder stringBuilder = new StringBuilder(s);
                        isMatch = true;
                        for (int i = 0; i < str.length(); i++) {
                            int index = stringBuilder.indexOf(String.valueOf(str.charAt(i)));
                            if (index != -1) {
                                stringBuilder.deleteCharAt(index);
                            } else {
                                isMatch = false;
                                break;
                            }
                        }
                        if (isMatch) {
                            list.add(str);
                            break;
                        }
                    }
                }
            }
            if (!isMatch) {
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
            }
        }
        return result;
    }

    // 对s进行排序
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String string = String.valueOf(chars);
            if (map.containsKey(string)) {
                map.get(string).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(string, list);
            }
        }
        return new ArrayList<>(map.values());
    }

}
