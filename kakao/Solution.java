import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        List<Music> musicList = new ArrayList<>();
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

        musicList = musicList.stream().sorted((a, b) -> b.getPlays() - a.getPlays()).collect(Collectors.toList());

        
        List<Integer> best = new ArrayList<>();
        for(String genresRank : genresSort.keySet()) {
            int count = 0;
            Iterator<Music> musicIt = musicList.iterator();
            while(musicIt.hasNext()) {
                Music music = musicIt.next();
                if(music.getGeres().equals(genresRank)) {
                    if(count < 2) {
                        best.add(music.getNum());
                    }
                    System.out.println(music.getNum());
                    musicIt.remove();
                    count++;
                }
            }    
        }
        ;
        int[] answer = new int[best.size()];
        for(int i = 0; i < best.size(); i++) {
            answer[i] = best.get(i);
        }
      ;
        return answer;
    }
}


class Music {
    private int num;
    private int plays;
    private String genres;
    
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