class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            windowCount[s2.charAt(right) - 'a']++;

            if (right - left + 1 > s1.length()) {
                windowCount[s2.charAt(left) - 'a']--;
                left++;
            }
            
            if (Arrays.equals(windowCount, s1Count)) return true;
        }

        return false;
    }
}