class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];
        Arrays.fill(answer, -1);

        for (int sourceIndex = 0; sourceIndex < nums1.length; sourceIndex++) {
            int start = 0;
            for (int targetIndex = 0; targetIndex < nums2.length; targetIndex++) {
                if (nums1[sourceIndex] == nums2[targetIndex]) {
                    start = targetIndex;
                    break;
                }
            }

            for (int targetIndex = start + 1; targetIndex < nums2.length; targetIndex++) {
                if (nums1[sourceIndex] < nums2[targetIndex]) {
                    answer[sourceIndex] = nums2[targetIndex];
                    break;
                }
            }
        }
        return answer;
    }
}