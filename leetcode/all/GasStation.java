package leetcode.all;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] fuelDelta = new int[n];
        int netfuelLeft = 0;

        for(int i = 0; i < n; i++) {
            fuelDelta[i] = gas[i] - cost[i];

            netfuelLeft += fuelDelta[i];
        }

        if (netfuelLeft < 0) {
            return -1;
        }

        int fuel = 0, startStation = 0;

        for (int i = 0; i < n; i++) {
            fuel += fuelDelta[i];

            if (fuel < 0) {
                startStation = i+1;
                fuel = 0;
            }
        }

        return startStation % n;
    }
    public static void main(String[] args) {
        GasStation solution = new GasStation();

        System.out.println(solution.canCompleteCircuit(
            new int[] {31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30},
            new int[] {36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35}
        ));
    }
}
