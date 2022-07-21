import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Description: AnotherStringMinimizationProblem
 * @Date 2022/7/20 11:25 PM
 * @Version 1.0
 */
public class AnotherStringMinimizationProblem {

    public static void main0(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int tests = Integer.parseInt(reader.readLine());
            while (tests > 0) {
                String[] strs = reader.readLine().split(" ");
                int n = Integer.parseInt(strs[0]), m = Integer.parseInt(strs[1]);
                char[] chars = new char[m];
                Arrays.fill(chars, 'B');
                strs= reader.readLine().split(" ");
                int[] nums = new int[n];
                for (int i = 0; i < n; i++) {
                    int num = Integer.parseInt(strs[i]) - 1;
                    if (num > (m - 1) / 2) {
                        num = m - 1 - num;
                    }
                    nums[i] = num;
                }
                Arrays.sort(nums);
                for (int i = 0; i < n; i++) {
                    int num = nums[i];
                    if (chars[num] == 'B') {
                        chars[num] = 'A';
                    } else {
                        chars[m - 1 - num] = 'A';
                    }
                }
                System.out.println(new String(chars));
                tests--;
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int tests = Integer.parseInt(reader.readLine());
            while (tests > 0) {
                String[] strs = reader.readLine().split(" ");
                int n = Integer.parseInt(strs[0]), m = Integer.parseInt(strs[1]);
                char[] chars = new char[m];
                Arrays.fill(chars, 'B');
                strs= reader.readLine().split(" ");
                int[] nums = new int[n];
                for (int i = 0; i < n; i++) {
                    nums[i] = Integer.parseInt(strs[i]) - 1;
                }
                Arrays.sort(nums);
                for (int i = 0; i < n; i++) {
                    int min = Math.min(nums[i], m - 1 - nums[i]), max = Math.max(nums[i], m - 1 - nums[i]);
                    if (chars[min] == 'B') {
                        chars[min] = 'A';
                    } else {
                        chars[max] = 'A';
                    }
                }
                System.out.println(new String(chars));
                tests--;
            }
        } catch (IOException e) {
        }
    }
}
