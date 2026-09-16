class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isCloseParentheses(ch)) {
                if (stack.isEmpty() || !isSameType(stack.pop(), ch)) return false;
            } else {
                stack.push(ch);
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    private boolean isCloseParentheses(char ch) {
        if (ch == ')' || ch == ']' || ch == '}') {
            return true;
        }
        return false;
    }

    private boolean isSameType(char openCh, char closeCh) {
        if (openCh == '(' && closeCh == ')') return true;
        if (openCh == '[' && closeCh == ']') return true;
        if (openCh == '{' && closeCh == '}') return true;
        return false;
    }
}