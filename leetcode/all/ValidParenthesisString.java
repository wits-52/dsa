package leetcode.all;

import java.util.Stack;

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        Stack<Character> stack = new Stack<>();
        int stars = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ')') {
                if (stack.isEmpty() || stack.peek() == ')') {
                    return false;
                }
                stack.pop();
            } else if (c == '(') {
                stack.push('(');
            } else {
                stars++;
                stack.push('(');
            }
        }
        if (stack.isEmpty() || stack.size() <= 2*stars ) return true;

        return false;

    }

    public static void main(String[] args) {
        ValidParenthesisString solution = new ValidParenthesisString();

        // System.out.println(solution.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"));
        // System.out.println(solution.checkValidString("(((((*)))**"));
        System.out.println(solution.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));

    }
}
