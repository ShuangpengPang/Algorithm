package com.shuangpeng.Problem;

public class Problem0044WildcardMatching {

    public static void main(String[] args) {
        String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        Problem0044WildcardMatching a = new Problem0044WildcardMatching();
        a.isMatch(s, p);
    }

    public boolean isMatch(String s, String p) {
        return recurse(s, p);
    }

    public boolean recurse(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sLength = s.length();
        int pLength = p.length();
        if (sLength == 0 && pLength == 0) {
            return true;
        }
        if (pLength == 0 && sLength > 0) {
            return false;
        }
        if (sLength == 0 && !p.replace("*", "").equals("")) {
            return false;
        }
        if (s.equals(p) || p.replace("*", "").equals("")) {
            return true;
        }
        char first = p.charAt(0);
        char last = p.charAt(pLength - 1);
        if (first != '*' && first != '?' && first != s.charAt(0)) {
            return false;
        }
        if (last != '*' && last != '?' && last != s.charAt(sLength - 1)) {
            return false;
        }
        if (first == '?' || first == s.charAt(0)) {
            return recurse(s.substring(1), p.substring(1));
        }
        if (last == '?' || last == s.charAt(sLength - 1)) {
            return recurse(s.substring(0, sLength - 1), p.substring(0, pLength - 1));
        }
        if (p.charAt(1) == '*') {
            return recurse(s, p.substring(1));
        }
        StringBuilder builder = new StringBuilder();
        boolean isStart = false;
        int before = 0;
        int after = 0;
        int i = 1;
        while (i < pLength) {
            char ch = p.charAt(i);
            if (!isStart && ch == '?') {
                before++;
            } else if (ch != '?' && ch != '*') {
                builder.append(ch);
                isStart = true;
            } else if (isStart && ch == '?') {
                after++;
            } else if (isStart) {
                break;
            }
            i++;
        }
        if (before + after > sLength) {
            return false;
        }
        String target = builder.toString();
        if (target.isEmpty()) {
            return true;
        }
        int index = s.indexOf(target, before);
        if (index == -1 || index + target.length() + after > sLength) {
            return false;
        }
        return recurse(s.substring(index + target.length() + after), p.substring(i));
    }
}
