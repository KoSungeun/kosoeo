import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Music {
    int musicNum;
    String genres;
    String plays;
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = new int[genres.length];
        
        for(int i = 0; answer.length; i++) {
            answer[i] = 1;
        }
        Map<String, Integer> genresMap = new HashMap<>();


        for(int i = 0; i < genres.length; i++) {
            genresMap.put(genres[i], genresMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<Integer> sortMap = new ArrayList<>(genresMap.keySet());
        Collections.sort(list, (c1, c2) -> genresMap.get(c2) - genresMap.get(c1));


        return answer;
    }
}