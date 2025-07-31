class Solution {
    public int solution(int[] array, int n) {
        int answer = array[0];
        int minDistance = Math.abs(n - array[0]);
        
        for (int num : array) {
            int distance = Math.abs(n - num);
            if (distance < minDistance) {
                answer = num;
                minDistance = distance;
            } else if (distance == minDistance) {
                if (num < answer) 
                    answer = num;
            }
        }
        return answer;
    }
}