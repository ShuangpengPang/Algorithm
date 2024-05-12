package com.shuangpeng.lcr;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR120FindRepeatDocument（寻找文件副本）
 * @date 2024/5/12 8:03 PM
 */
public class LCR120FindRepeatDocument {

    public int findRepeatDocument0(int[] documents) {
        Set<Integer> set = new HashSet<>();
        for (int id : documents) {
            if (!set.add(id)) {
                return id;
            }
        }
        return 0;
    }

    public int findRepeatDocument(int[] documents) {
        for (int n = documents.length, i = 0; i < n; i++) {
            int id = documents[i];
            if (id != i) {
                while (id != i && id != documents[id]) {
                    int tmp = documents[id];
                    documents[id] = id;
                    id = tmp;
                }
                if (id != i) {
                    return id;
                }
                documents[i] = i;
            }
        }
        return 0;
    }
}
