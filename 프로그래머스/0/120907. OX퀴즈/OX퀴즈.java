class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];

        int num1, num2, resultNum, result;

        for (int i = 0; i < quiz.length; i++) {
            String a = quiz[i];
            String[] arr = a.split(" ");
            num1 = Integer.parseInt(arr[0]);
            num2 = Integer.parseInt(arr[2]);
            resultNum = Integer.parseInt(arr[4]);
            result = 0;
            switch (arr[1]) {
                case "+":
                    result = num1 + num2;
                    break;
                default :
                    result = num1 - num2;
                    break;
            }
            answer[i] = resultNum == result ? "O" : "X";
        }
        return answer;
    }
}