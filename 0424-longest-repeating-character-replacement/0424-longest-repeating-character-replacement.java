class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];

        int left = 0;
        int maxFreq = 0;
        int answer = 0;

        for (int right = 0; right < s.length(); right++) {
            int current = s.charAt(right) - 'A';
            count[current]++;

            maxFreq = Math.max(maxFreq, count[current]);

            while ((right - left + 1) - maxFreq > k) {
                int remove = s.charAt(left) - 'A';

                count[remove]--;
                left++;
            }
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}