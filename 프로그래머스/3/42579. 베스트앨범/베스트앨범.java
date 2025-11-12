import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.ArrayList;
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
        
        HashMap<String, Integer> sortedMap = totalPlayMapToGenres.entrySet().stream()
                                                .sorted(Map.Entry.comparingByValue())
                                                .collect(Collectors.toMap(
                                                    Map.Entry::getKey, Map.Entry::getValue,
                                                    (e1, e2) -> e1,
                                                    HashMap::new));
        
        ArrayList<Integer> list = new ArrayList();
        Iterator iterator = sortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            String genre = String.valueOf(iterator.next());
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