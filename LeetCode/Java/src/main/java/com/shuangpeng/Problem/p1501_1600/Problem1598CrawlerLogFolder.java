package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1598CrawlerLogFolder（文件夹操作日志搜集器）
 * @Date 2022/9/9 10:04 AM
 * @Version 1.0
 */
public class Problem1598CrawlerLogFolder {

    public int minOperations(String[] logs) {
        int depth = 0;
        for (String l : logs) {
            if (l.equals("./")) {
                continue;
            } else if (l.equals("../")) {
                depth = Math.max(0, depth - 1);
            } else {
                depth++;
            }
        }
        return depth;
    }
}
