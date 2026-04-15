package leetcode.all;

import java.util.Stack;

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        Stack<Integer> openBracketIndexes = new Stack<>(), starIndexes = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ')') {
                if (!openBracketIndexes.isEmpty()) {
                    openBracketIndexes.pop();
                } else if (!starIndexes.isEmpty()) {
                    starIndexes.pop();
                } else {
                    return false;
                }
            } else if (c == '(') {
                openBracketIndexes.push(i);
            } else {
                starIndexes.push(i);
            }
        }

        if (openBracketIndexes.isEmpty()) return true;

        while (!openBracketIndexes.isEmpty() && !starIndexes.isEmpty()) {
            if (openBracketIndexes.peek() < starIndexes.peek()) {
                openBracketIndexes.pop();
                starIndexes.pop();
            } else {
                return false;
            }
        }

        return openBracketIndexes.isEmpty();

    }

    public static void main(String[] args) {
        ValidParenthesisString solution = new ValidParenthesisString();

        System.out.println(solution.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"));
        System.out.println(solution.checkValidString("(((((*)))**"));
        System.out.println(solution.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));

    }
}
