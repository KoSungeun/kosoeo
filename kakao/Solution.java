import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List<Music> musicList = new ArrayList()<>;
        Map<String, Integer> genresPlays = new HashMap<>();
        for(int i=0; i < genres.length; i++) {
            genresPlays.put(genres[i], genresPlays.getOrDefault(genres[i], 0) + plays[i]);
            musicList.add(new Music(i, genres[i], plays[i]));
            
        }


        
        Map<String, Integer> genresSort = new LinkedHashMap<>();
        

        genresPlays.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEachOrdered(e -> genresSort.put(e.getKey(), e.getValue()));

        return answer;
    }
}



class genres {

}

class Music {
    private int num;
    private int plays;
    
    public Music(int num, String genres, int plays) {
        this.num = num;
        this.genres = genres;
        this.plays = plays;
    }

    public int getNum(){
        return this.num;
    }

    public String getGeres() {
        return this.genres;
    }

    public int getPlays() {
        return this.plays;
    }
}