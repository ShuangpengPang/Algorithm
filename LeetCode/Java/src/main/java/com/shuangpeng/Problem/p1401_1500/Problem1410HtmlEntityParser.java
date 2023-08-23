package com.shuangpeng.Problem.p1401_1500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1410HtmlEntityParser（HTML实体解析器）
 * @date 2023/8/23 4:15 PM
 */
public class Problem1410HtmlEntityParser {

    static Map<String, Character> map = new HashMap<String, Character>() {{
        put("&quot;", '\"');
        put("&apos;", '\'');
        put("&amp;", '&');
        put("&gt;", '>');
        put("&lt;", '<');
        put("&frasl;", '/');
    }};

    public String entityParser(String text) {
        StringBuilder sb = new StringBuilder();
        int i = 0, n = text.length();
        while (i < n) {
            boolean find = false;
            for (int j = i + 4; j <= i + 7 && j <= n; j++) {
                String s = text.substring(i, j);
                if (map.containsKey(s)) {
                    sb.append(map.get(s));
                    i = j;
                    find = true;
                    break;
                }
            }
            if (!find) {
                sb.append(text.charAt(i++));
            }
        }
        return sb.toString();
    }
}
