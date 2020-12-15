package com.shuangpeng.Problem;

public class Problem0044WildcardMatching {

    public boolean isMatch0(String s, String p) {
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
        StringBuilder builder = new StringBuilder();
        boolean isStart = false;
        int count = 0;
        int i = 1;
        while (i < pLength) {
            char ch = p.charAt(i);
            if (!isStart && ch == '?') {
                count++;
            } else if (ch != '?' && ch != '*') {
                builder.append(ch);
                isStart = true;
            } else if (isStart && ch == '?') {
                builder.append(ch);
            } else if (isStart) {
                break;
            }
            i++;
        }
        String target = builder.toString();
        int length = target.length();
        if (count + length > sLength) {
            return false;
        }
        if (target.isEmpty()) {
            return true;
        }
        int k = 0;
        int start = count;
        for (int j = start; j < sLength; j++) {
            if (target.charAt(k) == '?' || s.charAt(j) == target.charAt(k)) {
                k++;
            } else {
                k = 0;
                j = start;
                start++;
            }
            if (k == length) {
                return recurse(s.substring(j + 1), p.substring(i));
            }
        }
        return false;
    }


    public boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    public boolean isMatch2(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }

        if (pRight == 0) {
            return sRight == 0;
        }

        int sIndex = 0, pIndex = 0;
        int sRecord = -1, pRecord = -1;

        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }

        return allStars(p, pIndex, pRight);
    }

    public boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

    public boolean isMatch3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sLength = s.length();
        int pLength = p.length();
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pLength; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= pLength; j++) {
                if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[sLength][pLength];
    }

    public boolean isMatch4(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sLength = s.length();
        int pLength = p.length();
        int sIndex = 0;
        int pIndex = 0;
        int sRecord = -1;
        int pRecord = -1;
        while (sIndex <= sLength && pIndex <= pLength) {
            if (sIndex < sLength && pIndex < pLength && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
                sIndex++;
                pIndex++;
            } else if (pIndex < pLength && p.charAt(pIndex) == '*') {
                sRecord = sIndex;
                pRecord = pIndex;
                pIndex++;
            } else {
                if (sIndex == sLength && pIndex == pLength) {
                    return true;
                }
                if (sIndex == sLength) {
                    break;
                }
                if (pRecord != -1) {
                    pIndex = pRecord + 1;
                    sIndex = sRecord + 1;
                    sRecord++;
                } else {
                    return false;
                }
            }
        }
        return p.substring(pIndex).replace("*", "").isEmpty();
    }

//    "bb"
//            "*?bb"

//    public static void main(String[] args) {
//        Problem0044WildcardMatching a = new Problem0044WildcardMatching();
//        String s = "bb";
//        String p = "*?bb";
//        a.isMatch(s, p);
//    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sLength = s.length();
        int pLength = p.length();
        while (pLength > 0 && p.charAt(pLength - 1) != '*') {
            if (sLength > 0 && (s.charAt(sLength - 1) == p.charAt(pLength - 1) || p.charAt(pLength - 1) == '?')) {
                sLength--;
                pLength--;
            } else {
                return false;
            }
        }
        if (pLength == 0) {
            return sLength == 0;
        }
        int sIndex = 0;
        int pIndex = 0;
        int sRecord = -1;
        int pRecord = -1;
        while (sIndex < sLength && pIndex < pLength) {
            if (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex)) {
                pIndex++;
                sIndex++;
            } else if (p.charAt(pIndex) == '*') {
                pIndex++;
                pRecord = pIndex;
                sRecord = sIndex;
            } else if (pRecord != -1 && sRecord + 1 < sLength) {
                sRecord++;
                pIndex = pRecord;
                sIndex = sRecord;
            } else {
                return false;
            }
        }
        for (int i = pIndex; i < pLength; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
