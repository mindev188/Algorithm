class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int left = sequence.length - 1;
        int right = sequence.length - 1;
        int total = sequence[sequence.length - 1];
        while (left > 0 && total != k) {
            if (total > k) {
                total -= sequence[right--];
            } else if (total < k) {
                total += sequence[--left];
            }
        }

        while (left > 0 && sequence[left - 1] == sequence[right]) {
            left -= 1;
            right -= 1;
        }

        return new int[] {left, right};
    }
}