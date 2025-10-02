package GreedyAlgorithm;

public class GasStation {
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5}, cost = {3,4,5,1,2};

        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int remTank = 0;
        int tank = 0;

        for (int i = 0; i < gas.length; i++) {
            int Gain = gas[i] - cost[i];
            tank += Gain;

            if (remTank + Gain < 0) {
                start = i + 1;
                remTank = 0;
            } else {
                remTank += Gain;
            }
        }

        if (tank < 0) {
            return -1;
        } else {
            return start;
        }
    }
}
