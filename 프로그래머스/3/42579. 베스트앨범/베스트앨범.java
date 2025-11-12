import java.util.*;
import java.util.stream.Collectors;
class Solution {
public int[] solution(String[] genres, int[] plays) {

        /*
         * 1. HashMap에 장르별 총 플레이수를 연산
         * 2. 장르별 우선 순위 선정
         * 3. 장르별로 상위 2개의 곡 선택 (선택 시 동일 점수인 경우 낮은 인덱스)
         */

        HashMap<String, Integer> totalPlayMapToGenres = new HashMap();
        for (int i = 0; i < genres.length; i++) {
           totalPlayMapToGenres.put(genres[i], totalPlayMapToGenres.getOrDefault(genres[i],0) + plays[i]);
        }
        
        List<Map.Entry<String, Integer>> sortedList = totalPlayMapToGenres.entrySet().stream()
                                                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                                        .collect(Collectors.toList());
        
        ArrayList<Integer> list = new ArrayList();
        for (Map.Entry<String, Integer> map : sortedList) {
            String genre = map.getKey();
            int maxPlay = 0;
            int subPlay = 0;
            int maxPlayIndex = -1;
            int subPlayIndex = -1;
            for (int i = 0; i < genres.length; i++) {
                if (!genre.equals(genres[i])) continue;
                if (plays[i] > maxPlay) {
                    subPlay = maxPlay;
                    subPlayIndex = maxPlayIndex;
                    maxPlay = plays[i];
                    maxPlayIndex = i;
                } else if (plays[i] > subPlay) {
                    subPlay = plays[i];
                    subPlayIndex = i;
                }
            }
            list.add(maxPlayIndex);
            if (subPlayIndex > -1) {
                list.add(subPlayIndex);
            }
        }

        return list.stream().mapToInt(a -> a).toArray();

    }
}