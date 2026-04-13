package leetcode.all;

public class CountPrimes {
    public int countPrimes(int n) {
        int primes = 0;
        boolean[] visited = new boolean[n];

        for (int i = 2; i * i <= n; i++) {
            for (int j = i*i; j < n; j+=i) {
                visited[j] = true;
            }
        }

        for (int i = 2; i < n; i++) {
            if (!visited[i]) {
                primes++;
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        CountPrimes solution = new CountPrimes();

        System.out.println(solution.countPrimes(10)); // 4

        System.out.println(solution.countPrimes(0)); // 0
        
        System.out.println(solution.countPrimes(1)); // 0
        
    }
}
