import java.util.HashMap;
class Solution {
    public String solution(String letter) {
        String answer = "";
        String[] morse = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashMap<String, Character> morseMap = new HashMap<>();
        for (int i = 0; i < morse.length; i++) {
            morseMap.put(morse[i], (char) (i + 'a') );
        }

        for (String a : letter.split(" ")) {
            answer += morseMap.get(a);
        }
        return answer;
    }
}