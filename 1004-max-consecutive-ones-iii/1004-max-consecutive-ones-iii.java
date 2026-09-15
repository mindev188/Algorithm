class Solution {
    /*
        슬라이딩 윈도우
     */
    public int longestOnes(int[] nums, int k) {
        int answer = 0;

        int left = 0;
        int zeroNum = 0;
        for (int right = 0; right < nums.length; right++) {
            // 다음 값이 0인 경우
            if (nums[right] == 0) {
                // zeroNum이 <= k가 될때까지 뺀다
                zeroNum++;
                while (zeroNum > k) {
                    if ((nums[left]) == 0) {
                        zeroNum--;
                    }
                    left++;
                }
            }

            answer = Math.max(answer, right - left + 1);
        }
        return answer;
    }
}
