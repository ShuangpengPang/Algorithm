package com.shuangpeng.competition.第296场周赛;

/**
 * @Description: Problem2296DesignATextEditor（设计一个文本编辑器）
 * @Date 2022/6/11 5:10 PM
 * @Version 1.0
 */
public class Problem2296DesignATextEditor {
    // 比赛时写法
    class TextEditor {

        int pos = 0;
        StringBuilder sb;

        public TextEditor() {
            pos = 0;
            sb = new StringBuilder();
        }

        public void addText(String text) {
            // sb.append(text.toCharArray(), pos, text.length());
            sb.insert(pos, text);
            pos += text.length();
        }

        public int deleteText(int k) {
            int min = Math.min(k, pos);
            sb.delete(pos - min, pos);
            pos -= min;
            return min;
        }

        public String cursorLeft(int k) {
            int min = Math.min(k, pos);
            pos -= min;
            int m = Math.min(pos, 10);
            return sb.substring(pos - m, pos);
        }

        public String cursorRight(int k) {
            int n = sb.length();
            pos = Math.min(pos + k, n);
            int m = Math.min(pos, 10);
            return sb.substring(pos - m, pos);
        }
    }
}

class Problem2296DesignATextEditor0 {
    class TextEditor {

        int pos = 0;
        StringBuilder sb;

        public TextEditor() {
            pos = 0;
            sb = new StringBuilder();
        }

        public void addText(String text) {
            sb.insert(pos, text);
            pos += text.length();
        }

        public int deleteText(int k) {
            int ans = Math.min(pos, k);
            sb.delete(pos - ans, pos);
            pos -= ans;
            return ans;
        }

        public String cursorLeft(int k) {
            pos -= Math.min(k, pos);
            return sb.substring(Math.max(pos - 10, 0), pos);
        }

        public String cursorRight(int k) {
            pos = Math.min(sb.length(), pos + k);
            return sb.substring(Math.max(0, pos - 10), pos);
        }
    }
}

class Problem2296DesignATextEditor1 {

    class TextEditor {
        class ListNode {
            ListNode prev, next;
            char c;

            public ListNode(char c) {
                this.c = c;
            }
        }

        ListNode head, pos;

        public TextEditor() {
            head = new ListNode(' ');
            pos = new ListNode(' ');
            head.next = pos;
            pos.prev = head;
        }

        public void addText(String text) {
            int n = text.length();
            for (int i = 0; i < n; ++i) {
                ListNode node = new ListNode(text.charAt(i));
                node.next = pos;
                node.prev = pos.prev;
                pos.prev.next = node;
                pos.prev = node;
            }
        }

        public int deleteText(int k) {
            int ans = 0;
            ListNode node = pos.prev;
            while (k > 0 && node != head) {
                node = node.prev;
                --k;
                ++ans;
            }
            node.next = pos;
            pos.prev = node;
            return ans;
        }

        public String cursorLeft(int k) {
            while (pos.prev != head && k > 0) {
                pos = pos.prev;
                --k;
            }
            StringBuilder sb = new StringBuilder();
            ListNode node = pos.prev;
            for (int i = 0; i < 10 && node != head; ++i) {
                sb.append(node.c);
                node = node.prev;
            }
            return sb.reverse().toString();
        }

        public String cursorRight(int k) {
            while (k > 0 && pos.next != null) {
                pos = pos.next;
                --k;
            }
            StringBuilder sb = new StringBuilder();
            ListNode node = pos.prev;
            for (int i = 0; i < 10 && node != head; ++i) {
                sb.append(node.c);
                node = node.prev;
            }
            return sb.reverse().toString();
        }
    }
}

class TextEditor {

    class Node {
        Node prev, next;
        char c;

        Node() {
        }

        Node(char c) {
            this.c = c;
        }

        Node addNode(Node node) {
            node.next = this.next;
            this.next.prev = node;
            node.prev = this;
            this.next = node;
            return node;
        }

        void remove() {
            this.next.prev = this.prev;
            this.prev.next = this.next;
        }
    }

    Node head, pos;

    public TextEditor() {
        pos = head = new Node();
        head.prev = head.next = head;
    }

    public void addText(String text) {
        int n = text.length();
        for (int i = 0; i < n; ++i) {
            pos = pos.addNode(new Node(text.charAt(i)));
        }
    }

    public int deleteText(int k) {
        int ans = 0;
        while (k > 0 && pos != head) {
            pos = pos.prev;
            pos.next.remove();
            --k;
            ++ans;
        }
        return ans;
    }

    public String cursorLeft(int k) {
        while (k > 0 && pos != head) {
            --k;
            pos = pos.prev;
        }
        return text();
    }

    public String cursorRight(int k) {
        while (k > 0 && pos.next != head) {
            --k;
            pos = pos.next;
        }
        return text();
    }

    private String text() {
        StringBuilder sb = new StringBuilder();
        Node node = pos;
        for (int i = 0; i < 10 && node != head; ++i) {
            sb.append(node.c);
            node = node.prev;
        }
        return sb.reverse().toString();
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */
