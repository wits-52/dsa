package performance_playground;

import utils.PerfUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

public final class ArrayListVersusSetTraversal {
    private void traverseArrayListForLoop(int n) {
        List<Integer> arrayList = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            arrayList.add(i);
        }
        System.out.println("Traversing ArrayList with for loop");
        int sum = 0;
        for (int v : arrayList) sum += v;
        System.out.println(sum);
    }
    private void traverseHashSetWithForLoop(int n) {
        Set<Integer> hashSet = new HashSet<>(n);
        for (int i = 1; i <= n; i++) {
            hashSet.add(i);
        }
        System.out.println("Traversing HashSet with for loop");
        int sum = 0;
        for (int v : hashSet) sum += v;
        System.out.println(sum);
    }

    private void traverseHashSetWithForEachLoop(int n) {
        Set<Integer> hashSet = new HashSet<>(n);
        for (int i = 1; i <= n; i++) {
            hashSet.add(i);
        }
        System.out.println("Traversing HashSet with forEach loop");
        final int[] sum = new int[1];
        hashSet.forEach(v -> sum[0] += v);
        System.out.println(sum[0]);
    }

    private void traverseHashSetWithToArrayLoop(int n) {
        Set<Integer> hashSet = new HashSet<>(n);
        for (int i = 1; i <= n; i++) {
            hashSet.add(i);
        }
        System.out.println("Traversing HashSet with toArray loop");
        int sum = 0;
        for (int v : hashSet.toArray(new Integer[0])) sum += v;
        System.out.println(sum);
    }
    private void traverseArrayWithForLoop(int n) {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i-1] = i;
        }
        System.out.println("Traversing Array with for loop");
        int sum = 0;
        for (int v : arr) sum += v;
        System.out.println(sum);
    }
    public static void main(String[] args) throws Exception {
        // read n from stdin
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        ArrayListVersusSetTraversal solution = new ArrayListVersusSetTraversal();

        PerfUtil.run(() -> {
            solution.traverseArrayListForLoop(n);
        }, true);

        PerfUtil.run(() -> {
            solution.traverseHashSetWithForEachLoop(n);
        }, true);

        PerfUtil.run(() -> {
            solution.traverseHashSetWithForLoop(n);
        }, true);

        PerfUtil.run(() -> {
            solution.traverseHashSetWithToArrayLoop(n);
        }, true);

        PerfUtil.run(() -> {
            solution.traverseArrayWithForLoop(n);
        }, true);
    }
}
