package leetcode.daily;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        boolean allNines = true;

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                allNines = false;
                break;
            }
        }
        if (allNines) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        } else {
            for (int i = digits.length - 1; i >=0; i--) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                } else {
                    digits[i]++;
                    break;
                }
            }
        }
        return digits;
    }
}
