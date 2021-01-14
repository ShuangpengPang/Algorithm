package com.shuangpeng;

public class HanoiTower {

    public static void main(String[] args) {
//        HanoiTower hanoiTower = new HanoiTower();
//        hanoiTower.printHanoiTower(3, 0);
        hanoiTower(3, 'a', 'b', 'c');
    }

    // a -> c: 0
    // a -> b: 1
    // b -> c: 2
    // b -> a: 3
    // c -> a: 4
    // c -> b: 5
    public void printHanoiTower(int n, int flag) {
        switch (flag) {
            case 0:
                if (n == 1) {
                    System.err.println("a -> c");
                } else {
                    printHanoiTower(n - 1, 1);
                    System.err.println("a -> c");
                    printHanoiTower(n - 1, 2);
                }
                break;
            case 1:
                if (n == 1) {
                    System.err.println("a -> b");
                } else {
                    printHanoiTower(n - 1, 0);
                    System.err.println("a -> b");
                    printHanoiTower(n - 1, 5);
                }
                break;
            case 2:
                if (n == 1) {
                    System.err.println("b -> c");
                } else {
                    printHanoiTower(n - 1, 3);
                    System.err.println("b -> c");
                    printHanoiTower(n - 1, 0);
                }
                break;
            case 3:
                if (n == 1) {
                    System.err.println("b -> a");
                } else {
                    printHanoiTower(n - 1, 2);
                    System.err.println("b -> a");
                    printHanoiTower(n - 1, 4);
                }
                break;
            case 4:
                if (n == 1) {
                    System.err.println("c -> a");
                } else {
                    printHanoiTower(n - 1, 5);
                    System.err.println("c -> a");
                    printHanoiTower(n - 1, 3);
                }
                break;
            case 5:
                if (n == 1) {
                    System.err.println("c -> b");
                } else {
                    printHanoiTower(n - 1, 4);
                    System.err.println("c -> b");
                    printHanoiTower(n - 1, 1);
                }
                break;
        }
    }

    public static void hanoiTower(int n, char a, char b, char c) {
        if (n == 1) {
            System.err.println(a + " -> " + c);
            return;
        }
        hanoiTower(n - 1, a, c, b);
        System.err.println(a + " -> " + c);
        hanoiTower(n - 1, b, a, c);
    }
}
