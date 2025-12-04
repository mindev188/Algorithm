class Solution {
    public int solution(String word) {
        int answer = 0;

        int[] weight = new int[5];
        int weightSize = weight.length;
        for (int i = 0; i < weightSize; i++) { // 0 ~ 3
            int num = 1;
            for (int j = 4 - i; j >= 1; j--) {
                num += Math.pow(5, j);
            }
            weight[i] = num;
        }

        for (int i = 0; i < word.length(); i++) {
            char a = word.charAt(i);

            int index = 0;
            switch(a) {
                case'E': index = 1;
                    break;
                case'I': index = 2;
                    break;
                case'O': index = 3;
                    break;
                case'U': index = 4;
                    break;
                default: index = 0;
            }

            answer += 1 + index * weight[i];
        }
        return answer;
    }
}