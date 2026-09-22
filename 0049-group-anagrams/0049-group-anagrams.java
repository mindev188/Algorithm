class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sortStr = String.valueOf(chars);
            List<String> list = map.getOrDefault(sortStr, new ArrayList<>());
            if (list.isEmpty()) {
                map.put(sortStr, list);
            }
            list.add(strs[i]);
        }

        List<List<String>> answer = new ArrayList<>();
        Iterator<List<String>> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            answer.add(iterator.next());
        }

        return answer;
    }
}