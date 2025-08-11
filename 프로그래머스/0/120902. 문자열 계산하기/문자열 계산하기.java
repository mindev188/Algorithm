class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String[] arr = my_string.split(" ");

        int tmp = 0;
        answer = Integer.parseInt(arr[0]);
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && i % 2 == 0) {
                tmp = Integer.parseInt(arr[i]);

                switch (arr[i - 1]) {
                    case "-":
                        answer -= tmp;
                        break;
                    default:
                        answer += tmp;
                        break;
                }
            }
        }
        return answer;
    }
}