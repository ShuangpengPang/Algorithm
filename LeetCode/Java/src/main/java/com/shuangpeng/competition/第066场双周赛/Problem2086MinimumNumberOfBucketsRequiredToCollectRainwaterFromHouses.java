package com.shuangpeng.competition.第066场双周赛;

public class Problem2086MinimumNumberOfBucketsRequiredToCollectRainwaterFromHouses {

    // 比赛时写法
    public int minimumBuckets0(String street) {
        int n = street.length();
        if (n == 1) {
            return street.charAt(0) == '.' ? 0 : -1;
        }
        int pre = -2;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            char c = street.charAt(i);
            if (i == 0 && c == 'H' && street.charAt(i + 1) == 'H') {
                return -1;
            }
            if (i == n - 1 && c == 'H' && street.charAt(i - 1) == 'H') {
                return -1;
            }
            if (i == 0) {
                if (c == 'H') {
                    ++ans;
                    pre = 1;
                }
            } else if (i == n - 1) {
                if (c == 'H') {
                    if (pre != n - 2) {
                        ++ans;
                        pre = n - 2;
                    }
                }
            } else if (c == 'H') {
                char c1 = street.charAt(i - 1), c2 = street.charAt(i + 1);
                if (c1 == 'H' && c2 == 'H') {
                    return -1;
                } else if (c1 == '.') {
                    if (pre == i - 1) {
                        continue;
                    }
                    if (c2 == '.') {
                        ++ans;
                        pre = i + 1;
                    } else {
                        ++ans;
                        pre = i - 1;
                    }
                } else {
                    ++ans;
                    pre = i + 1;
                }
            }
        }
        return ans;
    }

    public int minimumBuckets1(String street) {
        int n = street.length();
        int preIndex = -2;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            char c = street.charAt(i);
            if (c == 'H') {
                if (i == 0) {
                    if (i + 1 >= n || street.charAt(i + 1) == 'H') {
                        return -1;
                    }
                    preIndex = i + 1;
                    ++ans;
                } else if (i == n - 1) {
                    if (i - 1 < 0 || street.charAt(i - 1) == 'H') {
                        return -1;
                    }
                    if (preIndex != i - 1) {
                        preIndex = i - 1;
                        ++ans;
                    }
                } else {
                    char c1 = street.charAt(i - 1), c2 = street.charAt(i + 1);
                    if (c1 == 'H' && c2 == 'H') {
                        return -1;
                    } else if (c2 == 'H') {
                        if (preIndex != i - 1) {
                            preIndex = i - 1;
                            ++ans;
                        }
                    } else if (c1 == 'H') {
                        preIndex = i + 1;
                        ++ans;
                    } else if (preIndex != i - 1) {
                        preIndex = i + 1;
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }

    public int minimumBuckets(String street) {
        int n = street.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            char c = street.charAt(i);
            if (c == 'H') {
                if (i + 1 < n && street.charAt(i + 1) == '.') {
                    i += 2;
                    ++ans;
                } else if (i - 1 >= 0 && street.charAt(i - 1) == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
}
