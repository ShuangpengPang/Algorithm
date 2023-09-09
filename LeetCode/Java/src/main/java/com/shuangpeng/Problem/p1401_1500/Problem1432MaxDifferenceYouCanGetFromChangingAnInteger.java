package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1432MaxDifferenceYouCanGetFromChangingAnInteger（改变一个整数能得到的最大差值）
 * @date 2023/8/24 6:43 PM
 */
public class Problem1432MaxDifferenceYouCanGetFromChangingAnInteger {

    public int maxDiff(int num) {
        String s = Integer.toString(num);
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        return getNum(arr, num, 9) - (arr[0] == 1 ? getNum(arr, num, 0) : replace(arr, arr[0], 1));
    }

    private int getNum(int[] arr, int value, int t) {
        if (t != 0 && arr[0] != t) {
            return replace(arr, arr[0], t);
        }
        int s = -1;
        for (int num : arr) {
            if (num != arr[0] && num != t) {
                s = num;
                break;
            }
        }
        return s == -1 ? value : replace(arr, s, t);
    }

    private int replace(int[] arr, int s, int t) {
        int ans = 0;
        for (int num : arr) {
            ans = ans * 10 + (num == s ? t : num);
        }
        return ans;
    }
}

class Problem1432MaxDifferenceYouCanGetFromChangingAnInteger0 {

    public int maxDiff(int num) {
        char[] cs = Integer.toString(num).toCharArray();
        int s = cs[0] - '0', t = 1;
        if (cs[0] == '1') {
            for (char c : cs) {
                if (c != '0' && c != '1') {
                    s = c - '0';
                    t = 0;
                    break;
                }
            }
        }
        int m = cs[0] - '0';
        for (char c : cs) {
            if (c != '9') {
                m = c - '0';
                break;
            }
        }
        return (m == '9' ? num : getNumber(cs, m, 9)) - getNumber(cs, s, t);
    }


    private int getNumber(char[] cs, int s, int t) {
        int num = 0;
        for (char c : cs) {
            int d = c - '0';
            num = num * 10 + (d == s ? t : d);
        }
        return num;
    }
}

class Problem1432MaxDifferenceYouCanGetFromChangingAnInteger1 {

    public int maxDiff(int num) {
        char[] cs1 = Integer.toString(num).toCharArray(), cs2 = cs1.clone();
        if (cs1[0] != '1') {
            replace(cs1, cs1[0], '1');
        } else {
            for (char c : cs1) {
                if (c != '0' && c != '1') {
                    replace(cs1, c, '0');
                    break;
                }
            }
        }
        for (char c : cs2) {
            if (c != '9') {
                replace(cs2, c, '9');
                break;
            }
        }
        return Integer.parseInt(new String(cs2)) - Integer.parseInt(new String(cs1));
    }

    private void replace(char[] cs, char s, char t) {
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            if (cs[i] == s) {
                cs[i] = t;
            }
        }
    }
}
