import java.util.HashSet;
class Solution {
    public String solution(String s) {
        String answer = "";
        // 한번만 등장하는 문자들만 뽑아 정렬해서 반환
        // 없을 경우 공백 반환
        HashSet<Character> set = new HashSet<Character>();
        HashSet<Character> duplicateSet = new HashSet<Character>();

        for (char a : s.toCharArray()) {
            if (set.contains(a)) {
                duplicateSet.add(a);
            } else {
                set.add(a);
            }
        }

        StringBuilder sb = new StringBuilder();
        set.stream().sorted().filter(a -> !duplicateSet.contains(a)).forEach(sb::append);

        return sb.toString();
    }
}