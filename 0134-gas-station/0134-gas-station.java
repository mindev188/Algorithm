
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int total = 0;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            int value = gas[i] - cost[i];

            total += value;
            tank += value;
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }

        if (total < 0) return -1;

        return start;
    }
}