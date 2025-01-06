import java.util.List;

public class Sort {
    public static void bubbleSort(int[] nums) {
        int n = nums.length;

        for (int i = n - 1; i >= 1 ; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void  selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = nums[i];
            int minIndex = i;
            int j;
            for (j = i; j < n; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            swap(nums, i, j);
        }
    }

    public static void  quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int x = nums[lo];
        int i = lo - 1, j = hi + 1;
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) swap(nums, i, j);
        }
        quickSort(nums, lo, j);
        quickSort(nums, j + 1, hi);
    }
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void processList(List<? extends Number> list) {
        for (Number num : list) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {




    }
}
