class Solution {
     /**
     * 피자 조각수(slice)와 사람수(n) 가 주어 졌을때 최소 몇판의 피자가 있어야 있어야
     * 모든 사람들이 1개의 피자 조각을 먹을 수 있나?
     * 
     * @param slice
     * @param n
     * @return
     */
    public static int solution(int slice, int n) {
        int answer = n % slice == 0 ? n / slice : n / slice + 1 ;
        return answer;
    }
}