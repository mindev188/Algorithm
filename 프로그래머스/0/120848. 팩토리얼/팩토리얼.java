class Solution {
    public int solution(int n) {
        int index = 1;
        int val = 1;
        while(val <= n) {
            val *= ++index;
        }
        return index - 1;
    }
}