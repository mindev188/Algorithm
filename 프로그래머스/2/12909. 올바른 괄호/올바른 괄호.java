import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (char a : s.toCharArray()) {
            if (a == '(') {
                stack.add(a);
            } else {
                // ')'와 매칭되는 '('가 없으면 flase
                if (stack.empty()) return false;

                // ')'와 매칭되는 '('가 있으면 pop
                stack.pop();
            }
        }

        // 아직까지 남아있는 '('가 있으면 flase
        if (!stack.empty()) return false;

        return true;
    }
}