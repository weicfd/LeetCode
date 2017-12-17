package leetcode.stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by tangmh on 17/12/2.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

        // 4.1.1
//        System.out.println(tc.isValid("()"));
//        System.out.println(tc.isValid("()[]{}"));
//        System.out.println(tc.isValid("]"));
//        System.out.println(tc.isValid("([)]"));

        // 4.1.2
//        System.out.println(tc.longestValidParentheses_dp(""));
//        System.out.println(tc.longestValidParentheses_dp("()((((((()))"));

        // 4.1.3
//        System.out.println(tc.largestRectangleArea(new int[]{2,1,5,6,2,3}));

        // 4.1.4
        System.out.println(tc.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(tc.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c :
                s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else {
                char target;
                switch (c) {
                    case ')':
                        target = '(';
                        break;
                    case '}':
                        target = '{';
                        break;
                    case ']':
                        target = '[';
                        break;
                    default:
                        return false;
                }
                if (!stack.empty() && stack.peek() == target) stack.pop();
                else return false;
            }
        }
        return stack.empty();
    }

    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     * <p>
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * <p>
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        /**
         * 1. 遇到( 位置i入栈
         * 2. 遇到)
         *    1) 若为无效) 记录断点
         *    2) 若是有效) 出栈
         *       i. 完整表达(栈空) 上个断点到此为新的完整表达
         *       ii. 非完整表达(栈中还有'(') 上个匹配位置到此为完整表达
         */
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();
        int len = 0, last = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            } else {
                if (stack.empty()) {
                    last = i;
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        len = Math.max(len, i - last);
                    } else {
                        len = Math.max(len, i - stack.peek());
                    }
                }
            }
        }
        return len;
    }

    public int longestValidParentheses_dp(String s) {
        // 动态规划: 设计 dp[i]为从i到最后的有效串的最大长度,有效串的开头为i,结束为i+dp[i]-1
        // 一般情况: ( 有效长a ) 有效长b  -- a+b+2
        //          ) 其他 -- 0
        //          ( 有效长a -- 0(无效)
        // 每次更新maxlen
        int[] dp = new int[s.length()];
        int len = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                if (i + 1 < s.length() && dp[i + 1] + i + 1 < s.length() && s.charAt(dp[i + 1] + i + 1) == ')') {
                    dp[i] = 2 + dp[i + 1];
                    if (dp[i + 1] + i + 2 < s.length()) dp[i] += dp[dp[i + 1] + i + 2];
                }
            } else {
                dp[i] = 0;
            }
            len = len < dp[i] ? dp[i] : len;
        }
        return len;
    }

    /**
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
     * find the area of largest rectangle in the histogram.
     *
     * @param heights
     * @return
     */

    public int largestRectangleArea_easy(int[] heights) {
        // 简单版本, 会超时的, 左右扫描
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0, n = heights.length;

        for (int i = 0; i < n; i++) {
            int left = i - 1, right = i + 1, h = heights[i];
            while (left >= 0 && heights[left] >= h) left--;
            while (right < n && heights[right] >= h) right++;

            int area = h * (right - left - 1);

            maxArea = maxArea < area ? area : maxArea;
        }

        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        // 栈的版本
        // 困了我好几天的破算法
        Stack<Integer> stack = new Stack<>();

        int[] arr = Arrays.copyOf(heights, heights.length + 1);
        arr[heights.length] = 0;
        heights = arr;

        int n = heights.length, maxArea = 0;
        for (int i = 0; i < n; ) {
            if (stack.empty() || heights[i] > heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int temp = stack.peek();
                stack.pop();
                int area;
                if (stack.empty()) area = i * heights[temp];
                else area = (i - 1 - stack.peek()) * heights[temp];
                maxArea = maxArea < area ? area : maxArea;
            }
        }
        return maxArea;
    }

    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * <p>
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * <p>
     * Some examples:
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<String> stack = new Stack<>();

        for (String token :
                tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                // token is operator -> pop the first 2 nums to calc, push the ans back to stack
                int op2 = Integer.parseInt(stack.pop()), op1 = Integer.parseInt(stack.pop()), ans = 0;
                char op = token.charAt(0);
                switch (op) {
                    case '+':
                        ans = op1 + op2;
                        break;
                    case '-':
                        ans = op1 - op2;
                        break;
                    case '*':
                        ans = op1 * op2;
                        break;
                    case '/':
                        ans = op1 / op2;
                        break;
                    default:
                        break;
                }
                stack.push(String.valueOf(ans));
            } else {
                stack.push(String.valueOf(token));
            }
        }
        return Integer.parseInt(stack.peek());
    }


}
