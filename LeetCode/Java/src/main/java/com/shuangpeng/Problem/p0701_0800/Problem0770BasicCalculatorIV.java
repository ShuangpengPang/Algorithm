package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

public class Problem0770BasicCalculatorIV {

    class Poly {
        Map<String, Integer> polyMap;

        Poly() {
            polyMap = new HashMap<>();
        }

        void update(String key, int coefficient) {
            polyMap.put(key, polyMap.getOrDefault(key, 0) + coefficient);
        }

        Poly add(Poly other) {
            Poly poly = new Poly();
            for (String key : polyMap.keySet()) {
                poly.update(key, polyMap.get(key));
            }
            for (String key : other.polyMap.keySet()) {
                poly.update(key, other.polyMap.get(key));
            }
            return poly;
        }

        Poly sub(Poly other) {
            Poly poly = new Poly();
            for (String key : polyMap.keySet()) {
                poly.update(key, polyMap.get(key));
            }
            for (String key : other.polyMap.keySet()) {
                poly.update(key, -other.polyMap.get(key));
            }
            return poly;
        }

        Poly mul(Poly other) {
            Poly ans = new Poly();
            for (String key1 : polyMap.keySet()) {
                List<String> list1 = keyToList(key1);
                for (String key2 : other.polyMap.keySet()) {
                    List<String> list2 = keyToList(key2);
                    String key = merge(list1, list2);
                    ans.update(key, polyMap.get(key1) * other.polyMap.get(key2));
                }
            }
            return ans;
        }

        List<String> keyToList(String key) {
            List<String> ans = new ArrayList<>();
            if (key.equals("")) {
                ans.add("");
            } else {
                String[] strings = key.split("\\*");
                for (String s : strings) {
                    ans.add(s);
                }
            }
            return ans;
        }

        String merge(List<String> list1, List<String> list2) {
            List<String> list = new ArrayList<>();
            for (String s : list1) {
                if (!s.isEmpty()) {
                    list.add(s);
                }
            }
            for (String s : list2) {
                if (!s.isEmpty()) {
                    list.add(s);
                }
            }
            Collections.sort(list);
            int n = list.size();
            if (n == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(0));
            for (int i = 1; i < n; ++i) {
                sb.append('*').append(list.get(i));
            }
            return sb.toString();
        }

        List<String> toList() {
            List<String> keys = new ArrayList<>(polyMap.keySet());
            keys.sort((a, b) -> {
                int count1 = a.isEmpty() ? 0 : a.split("\\*").length;
                int count2 = b.isEmpty() ? 0 : b.split("\\*").length;
                if (count1 != count2) {
                    return count2 - count1;
                }
                return a.compareTo(b);
            });
            List<String> ans = new ArrayList<>();
            for (String key : keys) {
                int coefficient = polyMap.get(key);
                if (coefficient != 0) {
                    if (key.isEmpty()) {
                        ans.add("" + coefficient);
                    } else {
                        ans.add("" + coefficient + "*" + key);
                    }
                }
            }
            return ans;
        }
    }

    private Poly combine(Poly x, Poly y, char sign) {
        if (sign == '+') {
            return x.add(y);
        } else if (sign == '-') {
            return x.sub(y);
        } else if (sign == '*') {
            return x.mul(y);
        }
        return null;
    }

    public List<String> basicCalculatorIV(String expression, String[] evalVars, int[] evalInts) {
        Map<String, Integer> map = new HashMap<>();
        int length = evalVars.length;
        for (int i = 0; i < length; ++i) {
            map.put(evalVars[i], evalInts[i]);
        }
        Poly poly = parse(expression, map);
        return poly.toList();
    }

    private Poly parse(String s, Map<String, Integer> map) {
        int n = s.length();
        List<Poly> polyStack = new LinkedList<>();
        List<Character> symbolStack = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = i;
                int count = 1;
                while (count != 0) {
                    ++j;
                    char ch = s.charAt(j);
                    if (ch == '(') {
                        ++count;
                    } else if (ch == ')') {
                        --count;
                    }
                }
                polyStack.add(parse(s.substring(i + 1, j), map));
                i = j;
            } else if (Character.isDigit(c)) {
                int j = i;
                int value = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    value = value * 10 + s.charAt(j) - '0';
                    ++j;
                }
                Poly poly = new Poly();
                poly.update("", value);
                polyStack.add(poly);
                i = j;
            } else if (Character.isLetter(c)) {
                int j = i;
                StringBuilder sb = new StringBuilder();
                while (j < n && Character.isLetter(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    ++j;
                }
                String variable = sb.toString();
                Poly poly = new Poly();
                if (map.containsKey(variable)) {
                    poly.update("", map.get(variable));
                } else {
                    poly.update(variable, 1);
                }
                polyStack.add(poly);
                i = j;
            } else if (c == '+' || c == '-' || c == '*') {
                symbolStack.add(c);
            }
        }
        if (polyStack.isEmpty()) {
            return new Poly();
        }
        for (int i = symbolStack.size() - 1; i >= 0; --i) {
            if (symbolStack.get(i) == '*') {
                symbolStack.remove(i);
                polyStack.set(i, combine(polyStack.get(i), polyStack.remove(i + 1), '*'));
            }
        }
        Poly ans = polyStack.get(0);
        int size = symbolStack.size();
        for (int i = 0; i < size; ++i) {
            ans = combine(ans, polyStack.get(i + 1), symbolStack.get(i));
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0770BasicCalculatorIV a = new Problem0770BasicCalculatorIV();
//        List<String> ans = a.basicCalculatorIV("(e + 8) * (e - 8)", new String[0], new int[0]);
//        int i = 1;
//    }
}

