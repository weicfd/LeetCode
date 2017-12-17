package leetcode.linear.array;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * <p>
 * Note:
 * The solution is guaranteed to be unique.
 * Created by tangmh on 17/9/29.
 */
public class GasStation {
    public static void main(String[] args) {
        GasStation tc = new GasStation();
        int[] gas = {1, 1};
        int[] cost = {1, 1};
        System.out.println(tc.canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit_old(int[] gas, int[] cost) {
        // 我的实现,模拟 O(n2)
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = i; ; j++) {
                // 检测下一个节点是否可达
                temp += gas[i];
                if (temp < cost[i]) break;
                temp -= cost[i];

                int term = (i == 0 ? n - 1 : i - 1);
                if (j == term) {
                    return i;
                } else if (j == n - 1)
                    j = 0;
            }
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 优化到 O(n)
        int rec = -1, sum = 0, total = 0;
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                rec = i;
                sum = 0;
            }
        }
        // 只要总和是正的,则代表一定有解存在. 因此不需要重新检查之前的是否valid

        return total >= 0 ? rec + 1 : -1;
    }
}
