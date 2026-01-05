package utils;

public final class ArrayUtil {
    public static int max(int[] arr) throws Exception {
        if (arr.length == 0) {
            throw new Exception("Cannot find max for an empty array");
        }

        int max = arr[0];

        for (int a: arr) {
            if (a > max) {
                max = a;
            }
        }

        return max;
    }
    public static boolean equals(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}
