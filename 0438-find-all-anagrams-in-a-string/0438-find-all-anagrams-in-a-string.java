class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] targetCount = new int[26];
        int[] windowCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            targetCount[p.charAt(i) - 'a']++;
        }

        int left = 0;
        List<Integer> answer = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            windowCount[s.charAt(right) - 'a']++;

            if (right - left + 1 > p.length()) {
                windowCount[s.charAt(left++) - 'a']--;
            }

            if (Arrays.equals(windowCount, targetCount)) {
                answer.add(left);
            }
        }

        return answer;
    }
}