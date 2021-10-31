package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0204CountPrimes {

    static int max = 5000000;
    static int[] primes = new int[max / 10];
    static int count = 0;

    public int countPrimes0(int n) {
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        if (count == 0) {
            primes[count++] = 2;
            primes[count++] = 3;
            for (int i = 5; i < max; i = i + 2) {
                boolean isPrimes = true;
                int sqrt = (int) Math.sqrt(i);
                for (int j = 1; j < count && primes[j] <= sqrt; j++) {
                    if ((i % primes[j]) == 0) {
                        isPrimes = false;
                        break;
                    }
                }
                if (isPrimes) {
                    primes[count++] = i;
                }
            }
        }
        int left = 0;
        int right = count;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (primes[mid] < n) {
                left = mid + 1;
            } else if (primes[mid] > n) {
                right = mid;
            } else if (primes[mid] == n) {
                return mid;
            }
        }
        return left;
    }

    public int countPrimes1(int n) {
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int[] primes = new int[n / 2];
        int count = 0;
        primes[count++] = 2;
        primes[count++] = 3;
        for (int i = 5; i < n; i = i + 2) {
            boolean isPrimes = true;
            int sqrt = (int) Math.sqrt(i);
            for (int j = 1; j < count && primes[j] <= sqrt; j++) {
                if ((i % primes[j]) == 0) {
                    isPrimes = false;
                    break;
                }
            }
            if (isPrimes) {
                primes[count++] = i;
            }
        }
        int left = 0;
        int right = count;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (primes[mid] < n) {
                left = mid + 1;
            } else if (primes[mid] > n) {
                right = mid;
            } else if (primes[mid] == n) {
                return mid;
            }
        }
        return left;
    }

    public int countPrimes2(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] array = new boolean[n];
        array[1] = true;
        int count = 1;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (!array[i]) {
                if (isPrime(i)) {
                    for (int j = i * i; j < n; j += i) {
                        if (!array[j]) {
                            array[j] = true;
                            count++;
                        }
                    }
                }
            }
        }
        return n - 1 - count;
    }

    public boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }
        boolean isPrime = true;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if ((n % i) == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public int countPrimes3(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int answer = 0;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                answer++;
                if (i <= sqrt) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
        return answer;
    }

    public int countPrimes4(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int count = 1;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    if (isPrime[j]) {
                        count++;
                        isPrime[j] = false;
                    }
                }
            }
        }
        return n - 1 - count;
    }

    public int countPrimes5(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }

    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        int[] primes = new int[n / 2];
        int count = 0;
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primes[count++] = i;
            }
            int k = n / i;
            for (int j = 0; primes[j] <= k; j++) {
                if (i * primes[j] < n) {
                    isPrime[i * primes[j]] = false;
                }
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }
        return count;
    }

//    public static void main(String[] args) {
//        Problem0204CountPrimes a = new Problem0204CountPrimes();
//        int i = a.countPrimes(5);
//    }
}
