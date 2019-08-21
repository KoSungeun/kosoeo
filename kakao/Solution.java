
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        return answer;
    }
}

class Music {
    private int num;
    private String genres;
    private int plays;
    
    public Music(int num, String genres, int plays) {
        this.num = num;
        this.genres = genres;
        this.plays = plays;
    }

    public int getNum(){
        return this.num;
    }

    public String geres() {
        return this.genres;
    }

    public int getPlays() {
        return this.plays;
    }
}