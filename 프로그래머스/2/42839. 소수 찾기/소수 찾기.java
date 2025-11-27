import java.util.HashSet;

class Solution {
    HashSet<String> set;

    public int solution(String numbers) {
        int answer = 0;

        set = new HashSet<String>();
        boolean isVisited[] = new boolean[numbers.length()];
        generateAllNumbers(numbers, "", isVisited);

        for (String number : set) {
            int num = Integer.parseInt(number);

            boolean isPrime = true;
            if (num < 2) break;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) answer++;

        }
        return answer;
    }

    private void generateAllNumbers(String numbers, String current, boolean[] isVisited) {

        if ("0".equals(current)) current = "";
        
        if (!"".equals(current) && !"1".equals(current)) set.add(current);

        for (int i = 0; i < numbers.length(); i++) {
            if (!isVisited[i]) {
                isVisited[i]= true;
                generateAllNumbers(numbers, current + numbers.charAt(i), isVisited);

                isVisited[i]= false;
            }
        }
    }
}