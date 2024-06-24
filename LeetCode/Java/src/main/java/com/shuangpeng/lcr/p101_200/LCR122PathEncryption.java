package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR122PathEncryption（路径加密）
 * @date 2024/5/12 7:57 PM
 */
public class LCR122PathEncryption {

    public String pathEncryption(String path) {
        char[] cs = path.toCharArray();
        for (int n = cs.length, i = 0; i < n; i++) {
            if (cs[i] == '.') {
                cs[i] = ' ';
            }
        }
        return new String(cs);
    }
}
