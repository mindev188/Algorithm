class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[] {0, 0};

        /**
         * board 값을 통해 최대 진행 거리 구하기, +-
         *
         * answer[X, Y]
         * keyinput[이동 방향 목록]
         * board[가로, 세로]
         *
         * keyinput for 문으로 반복 처리
         * for () {
         *      각 문자열에 맞게 진행
         *      각 문자열만큼 이동 시 최대,최소 값을 넘어가는 경우 continue 처리
         * }
         */
        int boardTop = board[1]/2, boardBottom = board[1]/2 * -1, boardLeft = board[0]/2 * -1, boardRight = board[0]/2;

        for (String key : keyinput) {
            switch (key) {
                case "left":
                    if (boardLeft < answer[0]) answer[0]--;
                    break;
                case "right":
                    if (boardRight > answer[0]) answer[0]++;
                    break;
                case "up":
                    if (boardTop > answer[1]) answer[1]++;
                    break;
                case "down":
                    if (boardBottom < answer[1]) answer[1]--;
                    break;
            }
        }
        return answer;
    }
}