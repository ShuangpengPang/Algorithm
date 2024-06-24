package com.shuangpeng.lcr.p101_200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR135CountNumbers（报数）
 * @date 2024/5/14 12:22 PM
 */
public class LCR135CountNumbers {

    public int[] countNumbers0(int cnt) {
        int N = (int) Math.pow(10, cnt) - 1;
        int[] ans = new int[N];
        Arrays.setAll(ans, i -> i + 1);
        return ans;
    }

    public int[] countNumbers(int cnt) {
        int N = (int) Math.pow(10, cnt) - 1;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }
}

class LCR135CountNumbers0 {

    int[] res;
    int nine = 0, count = 0, start, cnt;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public int[] countNumbers(int cnt) {
        this.cnt = cnt;
        res = new int[(int)Math.pow(10, cnt) - 1];
        num = new char[cnt];
        start = cnt - 1;
        dfs(0);
        return res;
    }
    void dfs(int x) {
        if(x == cnt) {
            String s = String.valueOf(num).substring(start);
            if(!s.equals("0")) res[count++] = Integer.parseInt(s);
            if(cnt - start == nine) start--;
            return;
        }
        for(char i : loop) {
            if(i == '9') nine++;
            num[x] = i;
            dfs(x + 1);
        }
        nine--;
    }
}
