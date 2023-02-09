package com.shuangpeng.Problem.p1701_1800;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1797DesignAuthenticationManager（设计一个验证系统）
 * @date 2023/2/9 10:09 AM
 */
public class Problem1797DesignAuthenticationManager {

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
}

class Problem1797DesignAuthenticationManager0 {

    class AuthenticationManager {

        Map<String, Node> map;
        Node head;
        int timeToLive;

        public AuthenticationManager(int timeToLive) {
            this.map = new HashMap<>();
            this.timeToLive = timeToLive;
            head = new Node("", -1);
            head.prev = head.next = head;
        }

        public void generate(String tokenId, int currentTime) {
            Node tail = head.prev;
            Node node = new Node(tokenId, currentTime + timeToLive);
            tail.next = node;
            node.prev = tail;
            node.next = head;
            head.prev = node;
            map.put(tokenId, node);
        }

        public void renew(String tokenId, int currentTime) {
            if (map.containsKey(tokenId) && map.get(tokenId).expireTime > currentTime) {
                Node node = map.get(tokenId), prev = node.prev, next = node.next;
                prev.next = next;
                next.prev = prev;
                node.expireTime = currentTime + timeToLive;
                Node tail = head.prev;
                tail.next = node;
                node.prev = tail;
                head.prev = node;
                node.next = head;
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            Node node = head.next;
            while (node != head && node.expireTime <= currentTime) {
                map.remove(node.tokenId);
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node = node.next;
            }
            return map.size();
        }
    }

    class Node {
        String tokenId;
        int expireTime;
        Node prev, next;

        Node(String tokenId, int expireTime) {
            this(tokenId, expireTime, null, null);
        }

        Node(String tokenId, int expireTime, Node prev, Node next) {
            this.tokenId = tokenId;
            this.expireTime = expireTime;
            this.prev = prev;
            this.next = next;
        }
    }
}

class Problem1797DesignAuthenticationManager1 {

    class AuthenticationManager {

        int timeToLive;
        LinkedHashMap<String, Integer> map;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
            this.map = new LinkedHashMap<>();
        }

        public void generate(String tokenId, int currentTime) {
            map.put(tokenId, currentTime + timeToLive);
        }

        public void renew(String tokenId, int currentTime) {
            if (map.containsKey(tokenId) && map.get(tokenId) > currentTime) {
                map.put(tokenId, currentTime + timeToLive);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                if (((Map.Entry<String, Integer>) iterator.next()).getValue() <= currentTime) {
                    iterator.remove();
                }
            }
            return map.size();
        }
    }
}
