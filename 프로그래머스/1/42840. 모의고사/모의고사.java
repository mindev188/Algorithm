import java.util.*;
class Solution {
    int[] a = {1, 2, 3, 4, 5};
    int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
    int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

    public int[] solution(int[] answers) {
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == a[i % a.length]) aCount++;
            if (answers[i] == b[i % b.length]) bCount++;
            if (answers[i] == c[i % c.length]) cCount++;
        }

        int maxCount = Math.max(aCount, Math.max(bCount, cCount));
        List<Integer> list = new ArrayList<>();
        if (maxCount == aCount) list.add(1);
        if (maxCount == bCount) list.add(2);
        if (maxCount == cCount) list.add(3);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}