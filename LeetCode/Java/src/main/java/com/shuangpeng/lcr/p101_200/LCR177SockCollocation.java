package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR177SockCollocation（LCR 177. 撞色搭配）
 * @date 2024/8/11 6:21 PM
 */
public class LCR177SockCollocation {

    public int[] sockCollocation(int[] sockets) {
        int xor = 0;
        for (int s : sockets) {
            xor ^= s;
        }
        int lowBit = xor & -xor, zero = 0, one = 0;
        for (int s : sockets) {
            if ((s & lowBit) == 0) {
                zero ^= s;
            } else {
                one ^= s;
            }
        }
        return new int[]{zero, one};
    }
}
