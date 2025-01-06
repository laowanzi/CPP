import java.util.Arrays;
import java.util.Scanner;
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input1 = in.nextLine();
        String input2 = in.nextLine();
        String operator = in.nextLine();
        int[] nums1 = getArray(input1);
        int[] nums2 = getArray(input2);
        long[] res;
        // 减号的话，nums2全部取相反数
        if (operator.equals("-")) {
            for (int i = 0; i < nums2.length; i++) {
                nums2[i] *= -1;
            }
        }
        if (operator.equals("*")) {
            res = new long[nums1.length + nums2.length - 1];
            int k = nums2.length - 1;
            for (int i = 0; i < nums2.length; i++) {
                int[] temp = new int[nums1.length];
                int x = nums2[k--];
                for (int j = 0; j < nums1.length; j++) {
                    temp[j] = nums1[j] * x;
                }
                addTwoNums(res, temp, i);
            }
        } else {
            res = new long[Math.max(nums1.length, nums2.length)];
            addTwoNums(res, nums1, 0);
            addTwoNums(res, nums2, 0);
        }
        int start;
        for (start = 0; start < res.length; start++) {
            if (res[start] != 0) {
                break;
            }
        }
        if (start == res.length) {
            System.out.println("[0]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = start; i < res.length; i++) {
            sb.append(res[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        System.out.println(sb);
    }
    public static int[] getArray(String s) {
        String[] strs = s.replace("[", "").replace("]", "").split(" ");
        int n = strs.length;
        int[] nums = new int[n];
        int k = 0;
        for (String str : strs) {
            nums[k++] = Integer.parseInt(str);
        }
        return nums;
    }

    public static void addTwoNums(long[] nums1, int[] nums2, int skip) {
        int i = nums1.length - 1 - skip;
        int j = nums2.length - 1;
        while (j >= 0) {
            nums1[i--] += nums2[j--];
        }
    }
}
