import java.util.ArrayList;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i < participant.length ; i++) {
            list.add(participant[i]);
        }
        for(int i = 0; i < completion.length; i++) {
            for(int j = 0; j < list.size(); j++) {
                if(list.get(j).equals(completion[i])){
                    list.remove(j);
                }
            }
        }
        answer = list.get(0);
        return answer;
    }
}