package com.shuangpeng;

public class BinarySearch {

    // 最右索引（左闭右开）
    public static int binarySearchMaxRightOpen(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target && left < mid) {
                left = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left >= array.length || array[left] != target) {
            return -1;
        }
        return left;
    }

    public static int binarySearchMaxRightClosed(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                left = mid + 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            }
        }
        left--;
        if (left >= array.length || left < 0 || array[left] != target) {
            return -1;
        }
        return left;
    }

    public static int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int leftBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            }
        }
        if (left >= array.length) {
            return -1;
        }
        return array[left] == target ? left : -1;
    }

    public static int rightBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                left = mid + 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            }
        }
        if (left < 1) {
            return -1;
        }
        return array[left - 1] == target ? left - 1 : -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4};
        System.err.println(binarySearchMaxRightOpen(array, 3));
    }
}
