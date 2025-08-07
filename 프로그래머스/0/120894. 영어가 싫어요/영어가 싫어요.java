import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public long solution(String numbers) {
        long answer = 0;
        HashMap<String, String> numberMap = new HashMap<String, String>();
        numberMap.put("zero", "0");
        numberMap.put("one", "1");
        numberMap.put("two", "2");
        numberMap.put("three", "3");
        numberMap.put("four", "4");
        numberMap.put("five", "5");
        numberMap.put("six", "6");
        numberMap.put("seven", "7");
        numberMap.put("eight", "8");
        numberMap.put("nine", "9");

        Iterator<String> iterator = numberMap.keySet().iterator();
        while (iterator.hasNext()) {
            String numberKey = iterator.next();
            if (numbers.contains(numberKey)) {
                numbers = numbers.replaceAll(numberKey, numberMap.get(numberKey));
            };
        }
        return Long.parseLong(numbers);
    }
}