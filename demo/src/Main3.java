import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int[] weight = new int[m];
        int[] load = new int[n];
        if (x == 0) y = 0;
        for (int i = 0; i < m; i++) {
            weight[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            load[i] = in.nextInt();
        }
        Arrays.sort(weight);
        Arrays.sort(load);
        int curCar = -1, curProduct = 0;
        int minLoad = weight[0] - y;
        for (int i = 0; i < n; i++) {
            if (load[i] >= minLoad) {
                curCar = i;
                break;
            }
        }
        if (curCar == -1) {
            System.out.println(0);
            return;
        }
        int res = 0;
        while (curCar < n && curProduct < m) {
            if (load[curCar] >= weight[curProduct]) {
                res++;
                curCar++;
                curProduct++;
            } else if (load[curCar] + y >= weight[curProduct]) {
                if (x > 0) {
                    x--;
                    res++;
                    curCar++;
                    curProduct++;
                } else {
                    curCar++;
                }
            } else {
                curCar++;
            }
        }
        System.out.println(res);
    }
    public int maxTaskAssign(int[] weights, int[] cars, int pills, int strength) {
        Arrays.sort(weights);
        Arrays.sort(cars);
        int left = 1, right = Math.min(weights.length, cars.length);
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(weights, cars, pills, strength, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
    public boolean isValid(int[] weights, int[] cars, int pills, int strength, int weightCount) {
        Deque<Integer> deque = new ArrayDeque<>();
        int m = cars.length;
        int index = m - 1;
        for (int i = weightCount - 1; i >= 0; i--) {
            while (index >= m - weightCount && cars[index] + strength >= weights[i]) {
                deque.addFirst(cars[index]);
                index--;
            }
            if (deque.isEmpty()) return false;
            if (deque.peekLast() >= weights[i]) {
                deque.pollLast();
            } else if (pills > 0) {
                deque.pollFirst();
                pills--;
            } else {
                return false;
            }
        }
        return true;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        int res = 0;
        char[] chars = s.toCharArray();
        while (j < s.length()) {
            char ch = chars[j++];
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.get(ch) > 1) {
                char c = chars[i++];
                map.put(c, map.getOrDefault(c, 0) - 1);
            }
            res = Math.max(res, j - i);
        }
        return res;

    }
}
