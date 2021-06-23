package com.shuangpeng.Problem;

import java.util.*;

public class Problem1600ThroneInheritance {

    class ThroneInheritance0 {

        class Node {
            String name;
            boolean isDead;
            Node next;

            private Node(String name) {
                this.name = name;
            }
        }

        private Map<String, Node> lastChildMap;
        private Map<String, Node> nodeMap;
        private Node head;

        public ThroneInheritance0(String kingName) {
            head = new Node(kingName);
            lastChildMap = new HashMap<>();
            lastChildMap.put(kingName, head);
            nodeMap = new HashMap<>();
            nodeMap.put(kingName, head);
        }

        public void birth(String parentName, String childName) {
            String name = parentName;
            while (!lastChildMap.get(name).name.equals(name)) {
                name = lastChildMap.get(name).name;
            }
            Node last = lastChildMap.get(name);
            Node child = new Node(childName);
            child.next = last.next;
            last.next = child;
            lastChildMap.put(parentName, child);
            lastChildMap.put(childName, child);
            nodeMap.put(childName, child);
        }

        public void death(String name) {
            nodeMap.get(name).isDead = true;
        }

        public List<String> getInheritanceOrder() {
            List<String> answer = new ArrayList<>();
            Node node = head;
            while (node != null) {
                if (!node.isDead) {
                    answer.add(node.name);
                }
                node = node.next;
            }
            return answer;
        }
    }

    class ThroneInheritance1 {

        private Map<String, List<String>> map;
        private Set<String> deads;
        private String kingName;

        public ThroneInheritance1(String kingName) {
            this.kingName = kingName;
            map = new HashMap<>();
            map.put(kingName, new ArrayList<>());
            deads = new HashSet<>();
        }

        public void birth(String parentName, String childName) {
            map.get(parentName).add(childName);
            map.put(childName, new ArrayList<>());
        }

        public void death(String name) {
            deads.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> answer = new ArrayList<>();
            preorder(kingName, answer);
            return answer;
        }

        private void preorder(String name, List<String> list) {
            if (!deads.contains(name)) {
                list.add(name);
            }
            for (String child : map.get(name)) {
                preorder(child, list);
            }
        }
    }

    class ThroneInheritance {

        class Node {
            String name;
            boolean isDeaded;
            Node lastChild;
            Node next;

            public Node(String name) {
                this.name = name;
            }
        }

        Node head;
        Map<String, Node> map;

        public ThroneInheritance(String kingName) {
            map = new HashMap<>();
            head = new Node(kingName);
            map.put(kingName, head);
        }

        public void birth(String parentName, String childName) {
            String name = parentName;
            Node previousNode = map.get(name);
            while (previousNode.lastChild != null) {
                name = previousNode.lastChild.name;
                previousNode = map.get(name);
            }
            Node node = new Node(childName);
            node.next = previousNode.next;
            previousNode.next = node;
            map.get(parentName).lastChild = node;
            map.put(childName, node);
        }

        public void death(String name) {
            map.get(name).isDeaded = true;
        }

        public List<String> getInheritanceOrder() {
            List<String> answer = new ArrayList<>();
            Node node = head;
            while (node != null) {
                if (!node.isDeaded) {
                    answer.add(node.name);
                }
                node = node.next;
            }
            return answer;
        }
    }
/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
}
