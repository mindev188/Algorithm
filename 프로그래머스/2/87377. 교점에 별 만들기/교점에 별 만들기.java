import java.util.*;
class Solution {
    public String[] solution(int[][] line) {
        List<long[]> points = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long a = line[i][0];
                long b = line[i][1];
                long e = line[i][2];

                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];

                long denominator = a * d - c * b;
                if (denominator == 0L) continue;

                long xNumerator = f * b - e * d;
                long yNumerator = c * e - f * a;

                if (xNumerator % denominator != 0 || yNumerator % denominator != 0) {
                    continue;
                }

                long x = xNumerator / denominator;
                long y = yNumerator / denominator;

                points.add(new long[]{x, y});

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }

        int width = (int) (maxX - minX + 1);
        int heigth = (int) (maxY - minY + 1);
        char[][] board = new char[heigth][width];

        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        for (long[] arr : points) {
            int x = (int) (arr[0] - minX);
            int y = (int) (maxY - arr[1]);

            board[y][x] = '*';
        }

        String[] answer = new String[board.length];
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (char c : board[i]) {
                sb.append(c);
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}