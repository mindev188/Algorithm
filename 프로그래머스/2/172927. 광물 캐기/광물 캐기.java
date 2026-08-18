import java.util.*;
class Solution {
    static final String DIAMOND = "diamond";
    static final String IRON = "iron";
    static final String STONE = "stone";

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int totalPicks = picks[0] + picks[1] + picks[2];
        int maxMinerals = Math.min(minerals.length, totalPicks * 5);

        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < maxMinerals; i += 5) {
            int diamond = 0;
            int iron = 0;
            int stone = 0;

            for (int j = i; j < i + 5 && j < maxMinerals; j++) {
                if (minerals[j].equals("diamond")) {
                    diamond++;
                } else if (minerals[j].equals("iron")) {
                    iron++;
                } else {
                    stone++;
                }
            }

            groups.add(new Group(diamond, iron, stone));
        }

        groups.sort((a, b) -> {
            if (a.diamond != b.diamond) {
                return b.diamond - a.diamond;
            }
            if (a.iron != b.iron) {
                return b.iron - a.iron;
            }
            return b.stone - a.stone;
        });

        for (Group group : groups) {
            if (picks[0] > 0) {
                answer += group.getFatigue(0);
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += group.getFatigue(1);
                picks[1]--;
            } else if (picks[2] > 0) {
                answer += group.getFatigue(2);
                picks[2]--;
            }
        }
        return answer;
    }

    private static class Group {
        int diamond;
        int iron;
        int stone;

        Group(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }

        int getFatigue(int pickType) {
            // 다이야
            if (pickType == 0) {
                return diamond + iron + stone;
            }
            // 철
            if (pickType == 1) {
                return diamond * 5 + iron + stone;
            }
            // 돌
            return diamond * 25 + iron * 5 + stone;
        }
    }
}