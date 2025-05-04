package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1717MaximumScoreFromRemovingSubstrings（删除子字符串的最大得分）
 * @date 2025/4/29 13:33
 */
public class Problem1717MaximumScoreFromRemovingSubstrings {

    public int maximumGain(String s, int x, int y) {
        s += 'c';
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int cntA = 0, cntB = 0;
        char a = 'a', b = 'b';
        if (x < y) {
            x = x ^ y;
            y = x ^ y;
            x = x ^ y;
            a = 'b';
            b = 'a';
        }
        for (int i = 0; i < n; i++) {
            if (cs[i] == a) {
                cntA++;
            } else if (cs[i] == b) {
                if (cntA > 0) {
                    ans += x;
                    cntA--;
                } else {
                    cntB++;
                }
            }
            if (cs[i] != a && cs[i] != b || i == n - 1){
                ans += y * Math.min(cntA, cntB);
                cntA = cntB = 0;
            }
        }
        return ans;
    }
}

class Problem1717MaximumScoreFromRemovingSubstrings0 {

    public int maximumGain(String s, int x, int y) {
        return x>y? getMax(s,'a','b',x,y):getMax(s,'b','a',y,x);
    }

    private int getMax(String s, char a,char b,int x,int y){
        int c1 = 0,c2 = 0,ans = 0;
        s=s+"c";
        for (char c : s.toCharArray()) {
            if(c == a){
                c1++;
            }else if(c == b){
                if(c1 == 0){
                    c2++;
                }else {
                    ans+=x;
                    c1--;
                }
            }else {
                ans+=(y*Math.min(c1,c2));
                c1 = 0;c2 = 0;
            }
        }
        return ans;
    }
}
