class Solution {
    public int evalRPN(String[] tokens) {
        /**
         * 스택에 전부 집어 놓고
         * 기호가 나오면
         * 그 이전 두 값을 연산 하고 집어 넣음. 또 기호가 나오면.. 반복
         */

        int answer = 0;
        Deque<String> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (isOperator(token) && !stack.isEmpty()) {
                token = calculation(stack.pop(), stack.pop(), token);
            }
            stack.push(token);
        }
        return Integer.parseInt(stack.peek());
    }

    private boolean isOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        }
        return false;
    }

    private String calculation(String a, String b, String operator) {
        switch(operator) {
            case "*":
                return Integer.parseInt(b) * Integer.parseInt(a) + "";
            case "/":
                return Integer.parseInt(b) / Integer.parseInt(a) + "";
            case "-":
                return Integer.parseInt(b) - Integer.parseInt(a) + "";
            case "+" :
                return Integer.parseInt(b) + Integer.parseInt(a) + "";
        }

        return null;
    }
}
