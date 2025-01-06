import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        String s = "(]";
        System.out.println(isValid(s));

    }
    public static boolean isValid(String st) {

        char[] chars = st.toCharArray();
        Stack<Character> s = new Stack<>();
        for (char ch : chars) {
            if (ch == '[' || ch == '(' || ch == '{') {
                s.push(ch);
            } else {
                if (s.isEmpty()) return false;
                if ((ch == ']' && s.peek() == '[') || (ch == ')' && s.peek() == '(') || (ch == '}' && s.peek() == '{')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }
}