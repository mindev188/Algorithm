class Solution {
    public String solution(String my_string, int num1, int num2) {
        String answer = "";

        char tmp;
        char[] arr = my_string.toCharArray();

        tmp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = tmp;
        return String.valueOf(arr);
    }
}