package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0502PrintBin（二进制数转字符串）
 * @date 2023/3/2 10:22 AM
 */
public class Question0502PrintBin {

    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < 30 && num > 0; i++) {
            num *= 2;
            if (num >= 1) {
                sb.append("1");
                num -= 1;
            } else {
                sb.append("0");
            }
        }
        return num > 0 ? "ERROR" : sb.toString();
    }
}
