class Solution {
    public String decodeString(String s) {

        int repeat = 0;
        StringBuilder current = new StringBuilder();
        Deque<String> previousStrings = new ArrayDeque<>();
        Deque<Integer> repeatCounts = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                repeat = repeat * 10 + (ch - '0');
            } else if (ch == '[') {
                previousStrings.push(current.toString());
                repeatCounts.push(repeat);

                current = new StringBuilder();
                repeat = 0;
            } else if (ch == ']') {
                String previous = previousStrings.pop();
                int count = repeatCounts.pop();

                String decoded = current.toString().repeat(count);

                current = new StringBuilder(previous);
                current.append(decoded);
            } else {
                current.append(ch);
            }
        }
        return current.toString();
    }
}