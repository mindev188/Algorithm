import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] chars = s.toCharArray();
        String openStrs = "{([";

        StringBuilder sb;
        for (int i = 0; i < chars.length; i++) {
            Stack<String> stack = new Stack<>();
            boolean isValue = true;
            sb = new StringBuilder();
            sb.append(s.substring(i)).append(s.substring(0,i));
            for (int j = 0; j < sb.length(); j++) {
                String a = String.valueOf(sb.charAt(j));
                if (openStrs.contains(a)) {
                    stack.push(a);
                } else {
                    if (stack.empty() || !isMatch(stack.pop(), a)) {
                        isValue = false;
                        break;
                    }
                }
            }
            if (isValue && stack.empty()) answer++;
        }

        return answer;
    }

    private boolean isMatch(String openStr, String closeStr) {
        if ("[".equals(openStr) && "]".equals(closeStr)) {
           return true;
        } else if ("{".equals(openStr) && "}".equals(closeStr)) {
            return true;
        } else if ("(".equals(openStr) && ")".equals(closeStr)) {
            return true;
        }
        return false;
    };
}
