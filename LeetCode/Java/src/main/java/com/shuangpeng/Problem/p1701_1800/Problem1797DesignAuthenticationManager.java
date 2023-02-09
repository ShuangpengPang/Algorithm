package com.shuangpeng.Problem.p1701_1800;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1797DesignAuthenticationManager（设计一个验证系统）
 * @date 2023/2/9 10:09 AM
 */
public class Problem1797DesignAuthenticationManager {
}

class AuthenticationManager {

    int timeToLive;
    Deque<Pair<String, Integer>> q;
    Map<String, Integer> map;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        q = new ArrayDeque<>();
        map = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        q.addLast(new Pair(tokenId, currentTime + timeToLive));
        map.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        while (!q.isEmpty() && q.peekFirst().getValue() <= currentTime) {
            String id = q.pollFirst().getKey();
            if (map.getOrDefault(id, Integer.MAX_VALUE) <= currentTime) {
                map.remove(id);
            }
        }
        if (map.containsKey(tokenId)) {
            q.addLast(new Pair(tokenId, currentTime + timeToLive));
            map.put(tokenId, currentTime + timeToLive);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        while (!q.isEmpty() && q.peekFirst().getValue() <= currentTime) {
            String tokenId = q.pollFirst().getKey();
            if (map.getOrDefault(tokenId, Integer.MAX_VALUE) <= currentTime) {
                map.remove(tokenId);
            }
        }
        return map.size();
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */
